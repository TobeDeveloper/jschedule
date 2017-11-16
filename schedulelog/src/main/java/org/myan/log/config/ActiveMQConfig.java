package org.myan.log.config;

import ch.qos.logback.classic.spi.LoggingEventVO;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * Created by myan on 11/16/2017.
 * Intellij IDEA
 */
@Configuration
public class ActiveMQConfig {

    @JmsListener(destination = "jsche-queue")
    public void receiveTopic(ObjectMessage message) {
        try {
            Object messageBody = message.getObject();
            if(messageBody instanceof LoggingEventVO){
                LoggingEventVO log = (LoggingEventVO)messageBody;
                // process for our logging event here.
                String logging = String.format("L:%s, M:%s\n", log.getLevel().levelStr, log.getMessage());
                System.out.println(logging);
            } else{
                System.out.println("Won't process...");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
