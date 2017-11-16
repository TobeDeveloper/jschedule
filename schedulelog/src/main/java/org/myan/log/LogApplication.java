package org.myan.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * Created by myan on 11/16/2017.
 * Intellij IDEA
 * log collect application
 */
@SpringBootApplication
@EnableJms
public class LogApplication {

    public static void main(String args[]) {
        SpringApplication.run(LogApplication.class, args);
    }
}
