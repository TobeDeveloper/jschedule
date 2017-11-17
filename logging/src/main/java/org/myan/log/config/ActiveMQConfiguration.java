package org.myan.log.config;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEventVO;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * Created by myan on 11/16/2017.
 * Intellij IDEA
 */
@Configuration
@EnableJms
public class ActiveMQConfiguration {
    private final Logger logger = (Logger) LoggerFactory.getLogger(ActiveMQConfiguration.class);

    @JmsListener(destination = "jsche-queue")
    public void receiveTopic(ObjectMessage message) {
        try {
            Object messageBody = message.getObject();
            if (messageBody instanceof LoggingEventVO) {
                LoggingEventVO log = (LoggingEventVO) messageBody;
                // process for our logging event here.
                String logging = String.format("L:%s, M:%s\n", log.getLevel().levelStr, log.getMessage());
                System.out.println(logging);
            }
        } catch (JMSException e) {
            logger.error("Error occurs while transferring log to activeMQ.", e);
        }
    }
}
