package example.kafka_project;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "exampleTopic",groupId = "the-first-listener")
    void listener(String data){
        System.out.println("Listener received: " + data + " \uD83C\uDF89");
    }

}
