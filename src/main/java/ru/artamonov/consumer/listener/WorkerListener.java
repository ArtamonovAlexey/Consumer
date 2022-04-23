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

    @KafkaListener(id = "Worker", topics = "workerTopic", containerFactory = "singleFactory")
    public void listenWorker(ConsumerRecord<String, Worker> consumerRecord) {
        Object objectJson = consumerRecord.value();

        String json = objectJson.toString();
        Worker worker = (Worker) JsonConvertor.convertTo(json, Worker.class);

        save(worker);
    }

    @KafkaListener(id = "Workers", topics = "workerListTopic", containerFactory = "batchFactory")
    public void listenWorkers(List<ConsumerRecord<String, String>> consumerRecordList) {
        for (ConsumerRecord<String, String> consumerRecord : consumerRecordList) {
            Object objectJson = consumerRecord.value();
            String jsonString = objectJson.toString();

            List<String> jsonList = JsonConvertor.getObjectsAsString(jsonString);
            for (String json : jsonList) {
                save((Worker) JsonConvertor.convertTo(json, Worker.class));
            }
        }
    }

    @Transactional
    public void save(Worker worker) {
        repository.save(worker);
    }
}
