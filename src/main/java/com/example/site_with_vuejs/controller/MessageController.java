package com.example.site_with_vuejs.controller;

import com.example.site_with_vuejs.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int index = 4;

    private List<HashMap<String, String>> messages = new ArrayList<>(List.of(
            new HashMap<>(Map.of("id", "1", "text", "message1")) ,
            new HashMap<>(Map.of("id", "2", "text", "message2")),
            new HashMap<>(Map.of("id", "3", "text", "message3"))
    ));

    @GetMapping
    public List<HashMap<String, String>> list() {
        return messages;
    }

    @GetMapping("/{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getMessageById(id);

    }

    private HashMap<String, String> getMessageById(String id) {
        return messages.stream().filter(map -> map.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(index++));
        messages.add(new HashMap<>(message));
        return message;
    }

    @PutMapping("/{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        HashMap<String, String> messageById = getMessageById(id);

        messageById.putAll(message);
        messageById.put("id", id);

        return messageById;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        messages.remove(getMessageById(id));
    }
}
