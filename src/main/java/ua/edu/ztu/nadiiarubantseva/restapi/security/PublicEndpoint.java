package ua.edu.ztu.nadiiarubantseva.restapi.security;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PublicEndpoint {

    LOGIN("/api/v**/auth/login"),
    ERROR("/error");

    private final String endpoint;

    PublicEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }


    public static String[] getPublicEndpoints() {
        return Arrays.stream(values())
                .map(PublicEndpoint::getEndpoint)
                .toArray(String[]::new);
    }
}