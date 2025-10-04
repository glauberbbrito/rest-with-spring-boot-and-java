package br.com.gbb.rest_with_spring_boot_and_java.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ObjectMapperConfg {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // ✅ Suporte para LocalDate, LocalDateTime etc.
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        SimpleFilterProvider filters = new SimpleFilterProvider()
                .addFilter("PersonFilter", SimpleBeanPropertyFilter.serializeAllExcept("sensitiveData"));
        mapper.setFilterProvider(filters);

        return mapper;
    }

}
