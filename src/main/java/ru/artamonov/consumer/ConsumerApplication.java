package ru.artamonov.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
//@ComponentScan(basePackages = {
//        "ru.artamonov.consumer.config",
//        "ru.artamonov.consumer.listener",
//        "ru.artamonov.consumer.repository"
//})
@PropertySource({
        "classpath:kafka.properties"
})
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
