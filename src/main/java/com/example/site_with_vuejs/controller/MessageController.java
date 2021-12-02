package com.example.site_with_vuejs.controller;

import com.example.site_with_vuejs.domain.Message;
import com.example.site_with_vuejs.domain.User;
import com.example.site_with_vuejs.domain.Views;
import com.example.site_with_vuejs.dto.MessagePageDto;
import com.example.site_with_vuejs.service.MessageService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("message")
public class MessageController {
    public static final int MESSAGES_PER_PAGE = 3;

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @JsonView(Views.FullMessage.class) //выводятся только поля, помеченные этой аннотацией, см. Message
    public MessagePageDto list(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = MESSAGES_PER_PAGE, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return messageService.findForUser(pageable, user);
    }

    @GetMapping("/{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    @JsonView(Views.FullMessage.class)
//    @JsonView(Views.IdName.class) //через веб сокет мэппер не получает эту аннотацию!!!!!!!!!!!!
    public Message create(
            @RequestBody Message message,
            @AuthenticationPrincipal User user
            ) throws IOException {
        return messageService.create(message, user);
    }

    @PutMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) throws IOException {
        return messageService.update(messageFromDb, message);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageService.delete(message);
    }

//    //получение сообщений через вебсокеты. На этот мапинг приходят сообщения
//    @MessageMapping("/changeMessage")
//    //в какой топик мы складываем ответ. Топик - это канал, куда будут прилетать ответы от сервера и на который подписываются пользователи
//    @SendTo("/topic/activity")
//    public Message change(Message message) {
//        return messageRepo.save(message);
//    }
}
