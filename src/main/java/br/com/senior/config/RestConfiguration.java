package br.com.senior.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Bean
    public RestTemplate restTemplate() {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
//                HttpClientBuilder.create().build());
//        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
//        return restTemplate;
	
	return new RestTemplate();
    }

}
