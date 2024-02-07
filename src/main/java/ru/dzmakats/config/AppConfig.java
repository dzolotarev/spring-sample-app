package ru.dzmakats.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Denis Zolotarev on 07.02.2024
 */

@Configuration
@ComponentScan("ru.dzmakats")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
