package ua.edu.ztu.nadiiarubantseva.restapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ztu.nadiiarubantseva.restapi.common.ApiResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse, ?>> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(ApiResponse.success(authService.login(loginRequest)));
    }
}
