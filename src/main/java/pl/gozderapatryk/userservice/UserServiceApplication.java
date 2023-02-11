package pl.gozderapatryk.userservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import pl.gozderapatryk.userservice.utils.Misc;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class UserServiceApplication {

    public static void main(String[] args) {
        var env = new SpringApplication(UserServiceApplication.class).run(args).getEnvironment();
        log.info(Misc.getHelloMessage(env));
    }
}