package ru.artamonov.consumer.kafka.kafkaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.artamonov.consumer.entity.Worker;
import ru.artamonov.consumer.kafka.KafkaEntity;
import ru.artamonov.consumer.kafka.enums.CrudEnum;
import ru.artamonov.consumer.kafka.enums.TableEnum;

@Data
public class WorkerKafka implements KafkaEntity {

    private CrudEnum crud;

    private TableEnum table;

    private Long workerId;

    private Worker worker;

    public WorkerKafka(CrudEnum crud, TableEnum table, Long workerId, Worker worker) {
        this.crud = crud;
        this.table = table;
        this.workerId = workerId;
        this.worker = worker;
    }
}
