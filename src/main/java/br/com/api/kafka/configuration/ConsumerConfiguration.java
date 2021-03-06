package br.com.api.kafka.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.api.kafka.model.Mensagem;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfiguration {

    private static final String KAFKA_BROKER = "localhost:9092";

    @Bean
    public Map<String, Object> consumerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();

        configurations.put(JsonDeserializer.TRUSTED_PACKAGES, "br.com.api.kafka.model.Mensagem");
        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-sandbox");
        configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return configurations;
    }

    @Bean
    public ConsumerFactory<String, Mensagem> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
            consumerConfigurations(), 
            new StringDeserializer(), 
            new JsonDeserializer<>(Mensagem.class));
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Mensagem> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Mensagem> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}