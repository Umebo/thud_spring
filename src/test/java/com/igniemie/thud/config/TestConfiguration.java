package com.igniemie.thud.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.igniemie.thud.controllers",
        "com.igniemie.thud.service",
        "com.igniemie.thud.session"
})
public class TestConfiguration {

}
