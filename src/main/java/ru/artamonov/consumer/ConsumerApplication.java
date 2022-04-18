package ru.artamonov.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import ru.artamonov.consumer.kafka.kafkaEntity.WorkerKafka;
//import ru.artamonov.consumer.mapper.WorkerMapper;


@SpringBootApplication
@EnableKafka
@ComponentScan(basePackages = {
        "ru.artamonov.consumer.config",
        "ru.artamonov.consumer.listener",
        "ru.artamonov.consumer.repository"
})
@PropertySource({
        "classpath:kafka.properties"
})
public class ConsumerApplication {

//    private final WorkerMapper workerMapper;

//    @Autowired
//    public ConsumerApplication(WorkerMapper workerMapper) {
//        this.workerMapper = workerMapper;
//    }

//    @KafkaListener(topics = "server.worker")
//    public void consume(ConsumerRecord consumerRecord) {
//        Object object = consumerRecord.value();
//
////        System.out.println(consumerRecord.headers());
//        System.out.println(object);
//
//        try {
//            WorkerKafka workerKafka = (WorkerKafka) object;
//
//            System.out.println(workerKafka);
//        } catch (Exception e) {
//            System.err.println("Ошибка принадлежности объекта к классу наследников KafkaEntity");
//        }
//    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
