package xyz.sandersonsa.amqbrokerspringbootproducerjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class AmqbrokerSpringbootProducerJsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqbrokerSpringbootProducerJsonApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return JsonMapper.builder()
        .findAndAddModules()
        .build();
	}

}
