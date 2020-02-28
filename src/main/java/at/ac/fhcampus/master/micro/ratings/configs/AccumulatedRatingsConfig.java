package at.ac.fhcampus.master.micro.ratings.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccumulatedRatingsConfig {

    @Bean
    public Queue accumulatedRatingsWorkerQueue() {
        return new Queue("accumulated-ratings");
    }

}
