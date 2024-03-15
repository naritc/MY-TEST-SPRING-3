package my.tiran.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.tiran.user.common.constant.ActiveType;
import my.tiran.user.common.constant.ErrorType;
import my.tiran.user.common.constant.RoleType;
import my.tiran.user.common.constant.TokenType;
import my.tiran.user.common.exception.BuException;
import my.tiran.user.common.util.DateParserUtil;
import my.tiran.user.common.util.JwtUtil;
import my.tiran.user.model.bean.MyUserDetail;
import my.tiran.user.model.dto.RegisterUser;
import my.tiran.user.model.dto.RequestAuthentication;
import my.tiran.user.model.dto.ResponseAuthentication;
import my.tiran.user.model.entity.MyUserEntity;
import my.tiran.user.model.entity.MyUserRoleEntity;
import my.tiran.user.model.entity.RoleEntity;
import my.tiran.user.model.entity.TokenEntity;
import my.tiran.user.model.mapper.MyUserMapper;
import my.tiran.user.repository.MyUserRepository;
import my.tiran.user.repository.MyUserRolesRepository;
import my.tiran.user.repository.RoleRepository;
import my.tiran.user.repository.TokenRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final MyUserRepository myUserRepository;
  private final RoleRepository roleRepository;
  private final MyUserRolesRepository myUserRolesRepository;
  private final TokenRepository tokenRepository;

  private final MyUserMapper myUserMapper;

  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  private boolean isExistingUser(String username, String email) {
    Optional<MyUserEntity> existUser = myUserRepository.findByUsernameOrEmail(username, email);
    return existUser.isPresent();
  }

  @Transactional
  public ResponseAuthentication register(@Valid RegisterUser req) throws BuException {
    if (isExistingUser(req.getUsername(), req.getEmail())) throw new BuException(ErrorType.B0001);

    MyUserEntity regis = myUserMapper.toMyUserEntity(req);
    RoleEntity roleUser = roleRepository.findByRoleType(RoleType.USER);

    MyUserRoleEntity roles = MyUserRoleEntity.builder()
            .myUser(regis)
            .myRole(roleUser)
            .createdDatetime(DateParserUtil.now())
            .build();
    myUserRolesRepository.save(roles);

    regis.setActive(ActiveType.ACTIVE);
    regis.setCreatedDatetime(DateParserUtil.now());

    MyUserDetail myUserDetail = new MyUserDetail(regis);
    String jwtToken = JwtUtil.generateToken(myUserDetail);
    String refreshToken = JwtUtil.generateRefreshToken(myUserDetail);
    this.saveUserToken(regis, jwtToken);
    return ResponseAuthentication.builder()
          .accessToken(jwtToken)
          .refreshToken(refreshToken)
          .build();
  }

  public ResponseAuthentication authenticate(@Valid RequestAuthentication req) throws BuException {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
    );
    MyUserEntity userEn = myUserRepository.findByUsername(req.getUsername())
        .orElseThrow(() -> new BuException(ErrorType.A0001));


    MyUserDetail myUserDetail = new MyUserDetail(userEn);
    String jwtToken = JwtUtil.generateToken(myUserDetail);
    String refreshToken = JwtUtil.generateRefreshToken(myUserDetail);
    this.revokeAllUserTokens(userEn);
    this.saveUserToken(userEn, jwtToken);
    return ResponseAuthentication.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
  }

  private void saveUserToken(MyUserEntity user, String jwtToken) {
    TokenEntity token = TokenEntity.builder()
        .myUser(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(MyUserEntity myUser) {
    List<TokenEntity> validUserTokens = tokenRepository.findAllValidTokenByUser(myUser.getMyUserId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException, BuException {
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    String refreshToken;
    String username;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    username = JwtUtil.extractUsername(refreshToken);
    if (username != null) {
      MyUserEntity myUser = this.myUserRepository.findByUsernameAndActive(username)
              .orElseThrow(() -> new BuException(ErrorType.A0001));
      
      MyUserDetail myUserDetail = new MyUserDetail(myUser);
      
      if (JwtUtil.isTokenValid(refreshToken, myUserDetail)) {
        String accessToken = JwtUtil.generateToken(myUserDetail);
        this.revokeAllUserTokens(myUser);
        this.saveUserToken(myUser, accessToken);
        ResponseAuthentication authResponse = ResponseAuthentication.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
