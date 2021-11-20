package com.example.site_with_vuejs.util;
import com.example.site_with_vuejs.dto.EventType;
import com.example.site_with_vuejs.dto.ObjectType;
import com.example.site_with_vuejs.dto.WsEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
public class WsSender {
    //отвечает за отправку сообщений, через очереди сообщений в спринге
    private final SimpMessagingTemplate template;
    //(де)/сериализует объект, определяет какие поля нужно отразить для аннотаций @JsonView
    private final ObjectMapper mapper;

    public WsSender(SimpMessagingTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    //простое навешивание аннотации @JsonView на методы add, upd, del не поможет если мы используем сокеты, поэтому
    //делаем это вручную .writerWithView(view);
    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view) {
        ObjectWriter writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(view);

        return (EventType eventType, T payload) -> {
            String value = null;

            try {
                value = writer.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            template.convertAndSend(
                    "/topic/activity",
                    new WsEventDto(objectType, eventType, value)
            );
        };
    }
}