package ru.artamonov.consumer.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "WORKERS")
@Data
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    public Worker() {
    }

    public Worker(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
