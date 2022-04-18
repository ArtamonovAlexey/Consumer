package ru.artamonov.consumer.listener;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.artamonov.consumer.entity.Worker;

import java.util.Iterator;
//import ru.artamonov.consumer.repository.WorkerRepository;

@Service
public class KafkaConsumerListener {

//    private final WorkerRepository repository;
//
//    @Autowired
//    public KafkaConsumerListener(WorkerRepository repository) {
//        this.repository = repository;
//    }

//    @KafkaListener(
//            topics = "server.worker"
////            , containerFactory = "workerKafkaListenerContainerFactory"
//            , groupId = "workerConsumers"
//    )
//    public void consume(@Payload Worker worker
////            , @Headers MessageHeaders headers
//    ) {
////        Object object = consumerRecord.value();
//
////        System.out.println(consumerRecord);
//
//        System.out.println(worker);
////        System.out.println(headers);
//
////        try {
////            WorkerKafka workerKafka = (WorkerKafka) object;
////
////            System.out.println(workerKafka);
////        } catch (Exception e) {
////            System.err.println("Ошибка принадлежности объекта к классу наследников KafkaEntity");
////        }
//    }


    @KafkaListener(topics = "msg2")
    public void messageListener(ConsumerRecord consumerRecord) throws ParseException {
        Object obj = new JSONParser().parse(consumerRecord.value().toString());

// Кастим obj в JSONObject
        JSONObject jo = (JSONObject) obj;
// Достаём firstName and lastName®

//        System.out.println(jo);

//        Integer id = (Integer) jo.get("id");
//        String name = (String) jo.get("name");
//
//        System.out.println(Long.valueOf(id));
//        System.out.println(name);
        Worker worker = new Worker(Long.valueOf((Integer) jo.get("id")), (String) jo.get("name"));

//        Long id =
//
//        String name = (String) jo.get("firstName");
//        String lastName = (String) jo.get("lastName");
//        System.out.println("fio: " + firstName + " " + lastName);
//// Достаем массив номеров
//        JSONArray phoneNumbersArr = (JSONArray) jo.get("phoneNumbers");
//        Iterator phonesItr = phoneNumbersArr.iterator();
//        System.out.println("phoneNumbers:");
//// Выводим в цикле данные массива
//        while (phonesItr.hasNext()) {
//            JSONObject test = (JSONObject) phonesItr.next();
//            System.out.println("- type: " + test.get("type") + ", phone: " + test.get("number"));
//        }

        System.out.println(worker);

//        System.out.println(consumerRecord.key());
//        System.out.println(consumerRecord.value());
    }

//    @Transactional
//    public void save(Object object) {
//        repository.save(object);
//    }
}
