package ru.artamonov.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.artamonov.consumer.entity.Worker;
import ru.artamonov.consumer.kafka.KafkaEntity;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.server}")
    private String kafkaServer;
//
//    @Value("${kafka.group.id}")
//    private String kafkaGroupId;
//
//    @Bean
//    public KafkaListenerContainerFactory<?> batchFactory() {
//        ConcurrentKafkaListenerContainerFactory<Long, KafkaEntity> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setBatchListener(true);
//        factory.setMessageConverter(new BatchMessagingMessageConverter(converter()));
//        return factory;
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory<?> singleFactory() {
//        ConcurrentKafkaListenerContainerFactory<Long, KafkaEntity> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setBatchListener(false);
//        factory.setMessageConverter(new StringJsonMessageConverter());
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<Long, KafkaEntity> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//    }
//
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//
//        return props;
//    }
//
//    @Bean
//    public StringJsonMessageConverter converter() {
//        return new StringJsonMessageConverter();
//    }


    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> clazz) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(clazz));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Worker> workerKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Worker> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(Worker.class));
        return factory;
    }


//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, UserDTO.Address> addresKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, UserDTO.Address> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory(UserDTO.Address.class));
//        return factory;
//    }


//    @Bean
//    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<Long, KafkaEntity> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//
//        return factory;
//    }
}