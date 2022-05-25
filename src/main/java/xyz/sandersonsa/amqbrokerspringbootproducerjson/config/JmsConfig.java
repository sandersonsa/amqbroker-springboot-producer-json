package xyz.sandersonsa.amqbrokerspringbootproducerjson.config;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
// // import org.springframework.amqp.core.AmqpTemplate;
// // amqpTemplate.convertAndSend("javainuseExchange", "javainuse", emp);

@Configuration
@EnableJms
public class JmsConfig {

    @Bean
    public JmsListenerContainerFactory<?> defaultFactory(
            ConnectionFactory connectionFactory,
             DefaultJmsListenerContainerFactoryConfigurer configurer) {
        
       DefaultJmsListenerContainerFactory factory =
                 new DefaultJmsListenerContainerFactory();
       configurer.configure(factory, connectionFactory);
       return factory;
    }
 
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
       MappingJackson2MessageConverter converter =
                 new MappingJackson2MessageConverter();
       converter.setTargetType(MessageType.TEXT);
       converter.setTypeIdPropertyName("_type");
       return converter;
    }

}
