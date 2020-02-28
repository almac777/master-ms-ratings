package at.ac.fhcampus.master.micro.ratings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RatingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatingsApplication.class, args);
    }
}
