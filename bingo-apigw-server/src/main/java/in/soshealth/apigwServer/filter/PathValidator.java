package in.soshealth.apigwServer.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class PathValidator {

    private static final List<String> openAPIEndpoints = List.of("/bingo/auth/v1/register","/bingo/auth/v1/token", "/bingo/auth/v1/validate", "/eureka");

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> openAPIEndpoints
                                .stream()
                                .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
