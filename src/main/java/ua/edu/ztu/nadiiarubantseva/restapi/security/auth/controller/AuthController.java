package ua.edu.ztu.nadiiarubantseva.restapi.security.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ztu.nadiiarubantseva.restapi.common.ApiResponse;
import ua.edu.ztu.nadiiarubantseva.restapi.security.auth.service.AuthService;
import ua.edu.ztu.nadiiarubantseva.restapi.security.auth.dto.LoginRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.security.auth.dto.LoginResponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse, ?>> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(ApiResponse.success(authService.login(loginRequest)));
    }
}
