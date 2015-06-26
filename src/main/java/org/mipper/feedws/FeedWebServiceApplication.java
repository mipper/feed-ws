package org.mipper.feedws;

import org.mipper.feedws.controller.FileResourceController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class FeedWebServiceApplication
{

    public static void main ( String[] args )
    {
        SpringApplication.run ( FeedWebServiceApplication.class, args );
    }


    @Bean
    public FileResourceController fileResourceController ()
    {
        return new FileResourceController ( "./src/test/resources/data" );
    }
}
