package ua.edu.ztu.nadiiarubantseva.restapi.security.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.edu.ztu.nadiiarubantseva.restapi.common.ApiException;
import ua.edu.ztu.nadiiarubantseva.restapi.security.auth.validator.AuthErrorCode;
import ua.edu.ztu.nadiiarubantseva.restapi.security.jwt.JwtUtil;
import ua.edu.ztu.nadiiarubantseva.restapi.security.auth.dto.LoginRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.security.auth.dto.LoginResponse;
import ua.edu.ztu.nadiiarubantseva.restapi.security.auth.validator.AuthValidator;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthValidator authValidator;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public LoginResponse login(LoginRequest request) {
        Optional<AuthErrorCode> errorCode = authValidator.validate(request);
        if (errorCode.isPresent()) {
            throw new ApiException(errorCode.get());
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        return new LoginResponse(jwtUtil.generateToken(userDetails));
    }
}
