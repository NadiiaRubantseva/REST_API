package ua.edu.ztu.nadiiarubantseva.restapi.common;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}
