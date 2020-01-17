package com.github.mitschi.restservice;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.github.mitschi.restservice.api.GreetingService;
import com.github.mitschi.restservice.entity.GreetingEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greetings")
    public List<Greeting> greetings() {
        logger.info("GET REQUEST LIST");
        List<Greeting> allStoredGreetings = greetingService.getAllStoredGreetings();
        return allStoredGreetings;
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        logger.info("GET REQUEST: "+name);

        greetingService.storeGreeting(name);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @PostMapping("/greeting")
    public Greeting greeting(@RequestBody Greeting greeting) {
        logger.info("POST REQUEST: "+greeting.getContent());
        String greetingName = greeting.getContent().replace("I am ","");
        greetingService.storeGreeting(greetingName);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, greetingName));
    }
}