package ua.edu.ztu.nadiiarubantseva.restapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.edu.ztu.nadiiarubantseva.restapi.user.repository.UserRepository;

import java.util.Optional;

import static ua.edu.ztu.nadiiarubantseva.restapi.common.StringUtil.isBlank;

@Component
@RequiredArgsConstructor
public class AuthValidator {

    private final UserRepository userRepository;

    public Optional<AuthErrorCode> validate(LoginRequest request) {
        if (request == null) {
            return Optional.of(AuthErrorCode.INVALID_USER_DATA);
        }
        if (isBlank(request.email())) {
            return Optional.of(AuthErrorCode.INVALID_USER_EMAIL);
        }
        if (isBlank(request.password())) {
            return Optional.of(AuthErrorCode.INVALID_USER_PASSWORD);
        }
        if (!userRepository.existsByUsername(request.email())) {
            return Optional.of(AuthErrorCode.USER_NOT_FOUND);
        }
        return Optional.empty();
    }
}
