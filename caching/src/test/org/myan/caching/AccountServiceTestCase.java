package org.myan.caching;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by myan on 11/20/2017.
 * Intellij IDEA
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class AccountServiceTestCase {
    private final Logger logger = LoggerFactory.getLogger(AccountServiceTestCase.class);
    @Autowired
    private AccountService service;

    @Before
    public void setUp() {
        BasicConfigurator.configure();
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        service = context.getBean(AccountService.class);
    }
    @Test
    public void testGetAccountByName() {
        assertNotNull(service);
        service.getAccountByName("myan");
        service.getAccountByName("myan");

        service.reload();
        logger.info("Reload done...");
        service.getAccountByName("myan");
        service.getAccountByName("myan");
    }

    @Test
    public void testGetAccountById() {
        service.getAccountById(1);
        service.getAccountById(1);

        service.reloadCacheObject();
        service.getAccountById(1);
        service.getAccountById(1);
    }
}

@Configuration
@ComponentScan(basePackages = {"org.myan.caching"})
@EnableAutoConfiguration
class SpringConfiguration{

}