package spring.task.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    /**
     * restTemplate could be configured for different type of message for ex by edit the 'messageConverters'
     * or adding an interceptors
     * it supports wide variety of configuration
     * for the sake on simplicity we used the default config
     * @return
     */
    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
