package xyz.sandersonsa.amqbrokerspringbootproducerjson.config;

import java.time.format.DateTimeFormatter;

import javax.jms.ConnectionFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

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
