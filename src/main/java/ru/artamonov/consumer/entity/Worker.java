package ru.artamonov.consumer.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Worker {

    @Id
    private Long id;

    private String name;

    public Worker(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
