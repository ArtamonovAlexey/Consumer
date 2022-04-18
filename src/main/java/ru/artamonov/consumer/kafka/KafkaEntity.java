package ru.artamonov.consumer.kafka;

import ru.artamonov.consumer.kafka.enums.CrudEnum;
import ru.artamonov.consumer.kafka.enums.TableEnum;

public interface KafkaEntity {

    CrudEnum getCrud();

    TableEnum getTable();
}
