package com.sumerge.careertrack.wiki_svc.services;


import com.sumerge.careertrack.wiki_svc.entities.requests.NotificationRequestDTO;
import com.sumerge.careertrack.wiki_svc.entities.responses.ArticleResponseDTO;
import org.json.JSONObject;
import com.sumerge.careertrack.wiki_svc.entities.requests.ArticleRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//TODO REMOVE THIS IT WAS FOR TESTING PURPOSES
@Service
public class ProducerService {
    @Value("${kafka.topic.name}")
    private String topicName;
    private final KafkaTemplate<String,String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(NotificationRequestDTO dto)  {
        JSONObject obj= new JSONObject(dto);
        kafkaTemplate.send(topicName, obj.toString());

    }



}