package my.tiran.user.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import my.tiran.user.model.bean.MyUserDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUtil {
    @Value("${app.jwt.secret:117a520adbd19eff51100215aa7a7fbf}")
    private static String secret;

    @Value("${app.jwt.expire-min:60}")
    private static long expireMin;

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public static String generateToken(MyUserDetail userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public static String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, expireMin);
    }

    public static String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, expireMin);
    }

    private static String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration
    ) {
        LocalDateTime dateNow = DateParserUtil.now();
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(dateNow.atZone(ZoneId.systemDefault()).toInstant()))
                .expiration(Date.from(dateNow.plusMinutes(expireMin).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getSignInKey())
                .compact();
    }

    public static boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}