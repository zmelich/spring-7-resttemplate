package guru.springframework.spring7resttemplate.config;



import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.boot.restclient.autoconfigure.RestTemplateBuilderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

/*
Created by Zsolt Melich (BT - IVR team)
*/
@Configuration
public class RestTemplateBuilderConfig {

    @Value("${rest.template.rootUrl}")
    String rootUrl;

    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer){

        assert rootUrl != null;

        RestTemplateBuilder builder = configurer.configure(new RestTemplateBuilder());
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(rootUrl);

        return builder.uriTemplateHandler(defaultUriBuilderFactory);
        }

}
