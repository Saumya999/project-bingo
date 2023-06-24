package in.crossdimension.sosheurekadiscoverysever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SoshEurekaDiscoverySeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoshEurekaDiscoverySeverApplication.class, args);
	}

}
