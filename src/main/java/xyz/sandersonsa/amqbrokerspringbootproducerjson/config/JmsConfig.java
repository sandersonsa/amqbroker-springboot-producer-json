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
// // import org.springframework.amqp.core.AmqpTemplate;
// // amqpTemplate.convertAndSend("javainuseExchange", "javainuse", emp);

@Configuration
@EnableJms
public class JmsConfig {

    private static final String dateFormat = "yyyy-MM-dd";
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

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

    // @Bean
    // @Primary
    // public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    //     System.out.println("Config is starting.");
    //     ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    //     objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    //     return objectMapper;
    // }

    // @Bean
    // public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
    //     return new Jackson2ObjectMapperBuilder().serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)))
    //         .serializationInclusion(JsonInclude.Include.NON_NULL);
    // }

    // @Bean
    // public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
    //     return builder -> {
    //         builder.simpleDateFormat(dateTimeFormat);
    //         builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
    //         builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
    //     };
    // }

    // @Bean
	// public ObjectMapper objectMapper() {

	// 	ObjectMapper objectMapper = new ObjectMapper();
	// 	// objectMapper.findAndRegisterModules();
    //     objectMapper.registerModule(new JavaTimeModule());
    //     objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	// 	return objectMapper;
		
	// 	// return JsonMapper.builder()
    //     // .findAndAddModules()
    //     // .build();
	// }

}
