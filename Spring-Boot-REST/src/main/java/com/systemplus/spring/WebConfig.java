package com.systemplus.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;


@Configuration
public class WebConfig 
{
    @Bean
    @Primary
    public ObjectMapper customJson(){
        return new Jackson2ObjectMapperBuilder()
           .indentOutput(true)
           .serializationInclusion(JsonInclude.Include.NON_NULL)
           .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
           .build();
}
}