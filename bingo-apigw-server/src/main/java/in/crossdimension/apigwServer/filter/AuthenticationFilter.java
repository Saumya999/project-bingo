package in.crossdimension.apigwServer.filter;

import in.crossdimension.apigwServer.utils.JwtUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private PathValidator pathValidator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtility utility;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (pathValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Header missing");
                }
                String header = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (header != null && header.startsWith("Bearer ")) {
                    header = header.substring(7);
                }

                try {
                    utility.validateToken(header);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
