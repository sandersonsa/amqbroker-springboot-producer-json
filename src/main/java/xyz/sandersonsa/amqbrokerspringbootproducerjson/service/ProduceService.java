package xyz.sandersonsa.amqbrokerspringbootproducerjson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
 
import lombok.extern.slf4j.Slf4j;
 
@Component
@Slf4j
public class ProduceService {
 
    @Autowired
    private JmsTemplate jmsTemplate;
 
    public void sendTo(String destination, String message) {
        log.info(" ### Enviando Msg :: {} - Destination :: {}", message, destination);
        jmsTemplate.convertAndSend(destination, message);
    }

    public void sendTo(String destination, Object message) {
        log.info(" ### Enviando Msg :: {} - Destination :: {}", message, destination);
        jmsTemplate.convertAndSend(destination, message);
    }
}
