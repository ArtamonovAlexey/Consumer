package ru.artamonov.consumer.listener;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artamonov.consumer.entity.Worker;
import ru.artamonov.consumer.repository.WorkerRepository;

@Service
public class WorkerListener {

//    @Autowired
//    private final WorkerRepository repository;
//
//    public WorkerListener(WorkerRepository repository) {
//        this.repository = repository;
//    }

    @KafkaListener(topics = "msg2")
    public void listen(ConsumerRecord consumerRecord) throws ParseException {
        Object object = new JSONParser().parse(consumerRecord.value().toString());
        JSONObject jsonObject = (JSONObject) object;

        Worker worker = new Worker(Long.valueOf((Integer) jsonObject.get("id")), (String) jsonObject.get("name"));

        System.out.println(worker);

        String crudName = consumerRecord.key().toString();

//        switch (crudName) {
//            case "CREATE": {
//                save(worker);
//            }
//
//            case "UPDATE": {
//                update(worker);
//            }
//
//            case "DELETE": {
//                delete(worker);
//            }
//
//            default: {
//                System.err.println("Error");
//            }
//        }
    }

//    @Transactional
//    public void save(Worker worker) {
//        repository.saveAndFlush(worker);
//    }
//
//    @Transactional
//    public void update(Worker newWorker) {
//        repository.saveAndFlush(newWorker);
//    }
//
//    @Transactional
//    public void delete(Worker worker) {
//        repository.deleteById(worker.getId());
//    }
}
