package testproject.sarafan.controller;

import org.springframework.web.bind.annotation.*;
import testproject.sarafan.exceptions.NotFoundException;

import java.util.*;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 4;

    private List<Map<String, String>> messages = new ArrayList<>(){{
        add(new HashMap<>() {{put("id", "1"); put("text", "First message");}});
        add(new HashMap<>() {{put("id", "2"); put("text", "Second message");}});
        add(new HashMap<>() {{put("id", "3"); put("text", "Third message");}});
    }};

    @GetMapping
    public List<Map<String, String>> list(){
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable(name = "id") String id){
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message){
        message.put("id", String.valueOf(counter++));

        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(
            @PathVariable(name = "id") String id,
            @RequestBody Map<String, String> message)
    {
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") String id){
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }
}
