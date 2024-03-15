package my.tiran.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.tiran.user.common.util.ResponseUtil;
import my.tiran.user.model.dto.RegisterUser;
import my.tiran.user.model.dto.RequestAuthentication;
import my.tiran.user.model.dto.ResponseAuthentication;
import my.tiran.user.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Log4j2
@RequiredArgsConstructor
@Controller
public class AuthApiController implements AuthApi {
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<ResponseAuthentication> userAuthentication(RequestAuthentication requestAuthentication) throws Exception {
        return ResponseUtil.success(
                authenticationService.authenticate(requestAuthentication)
        );
    }

    @Override
    public ResponseEntity<ResponseAuthentication> userRegister(RegisterUser registerUser) throws Exception {
        return ResponseUtil.success(
                authenticationService.register(registerUser)
        );
    }
}
