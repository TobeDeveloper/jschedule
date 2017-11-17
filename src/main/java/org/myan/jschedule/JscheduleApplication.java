package org.myan.jschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class JscheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JscheduleApplication.class, args);
    }
}
