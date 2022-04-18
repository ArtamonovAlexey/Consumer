package ru.artamonov.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.artamonov.consumer.entity.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
