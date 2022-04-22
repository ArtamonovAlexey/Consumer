package ru.artamonov.consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artamonov.consumer.entity.Worker;
import ru.artamonov.consumer.repository.WorkerRepository;
import ru.artamonov.consumer.util.JsonConvertor;

import java.util.List;

@Service
public class WorkerListener {

    @Autowired
    private final WorkerRepository repository;

    public WorkerListener(WorkerRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(id = "Worker", topics = "workerTopic")
    public void listenWorker(ConsumerRecord<String, Worker> consumerRecord) {
        Object objectJson = consumerRecord.value();

        String json = objectJson.toString();
        Worker worker = (Worker) JsonConvertor.convertTo(json, Worker.class);

        System.out.println(worker);
    }

    @KafkaListener(id = "Workers", topics = "workerListTopic", containerFactory = "batchFactory")
    public void listenWorkers(List<ConsumerRecord> consumerRecordList) {
        for (ConsumerRecord consumerRecord : consumerRecordList) {

            Object objectJson = consumerRecord.value();

            String json = objectJson.toString();

            List<String> jsonList = JsonConvertor.getObjectsAsString(json);

            for (String jsn : jsonList) {
                save((Worker) JsonConvertor.convertTo(jsn, Worker.class));
            }
        }
    }

    @Transactional
    public void save(Worker worker) {
        repository.save(worker);
    }

    @Transactional
    public void update(Worker newWorker) {
        repository.save(newWorker);
    }

    @Transactional
    public void delete(Worker worker) {
        repository.deleteById(worker.getId());
    }
}
