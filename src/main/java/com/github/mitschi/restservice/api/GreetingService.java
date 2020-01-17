package com.github.mitschi.restservice.api;

import com.github.mitschi.restservice.Greeting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GreetingService {

    public List<Greeting> getAllStoredGreetings();

    public Greeting storeGreeting(String name);
}
