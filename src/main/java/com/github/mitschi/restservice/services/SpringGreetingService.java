package com.github.mitschi.restservice.services;

import com.github.mitschi.restservice.Greeting;
import com.github.mitschi.restservice.api.GreetingService;
import com.github.mitschi.restservice.dao.GreetingEntryDao;
import com.github.mitschi.restservice.entity.GreetingEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpringGreetingService implements GreetingService {

    @Autowired
    private GreetingEntryDao greetingEntryDao;

    @Override
    public List<Greeting> getAllStoredGreetings() {
        List<GreetingEntry> all = greetingEntryDao.findAll();

        List<Greeting> greetings = new ArrayList<>();
        for (GreetingEntry greetingEntry : all) {
            greetings.add(new Greeting(greetingEntry.getId(),greetingEntry.getName()));
        }

        return greetings;
    }

    @Transactional
    @Override
    public Greeting storeGreeting(String name) {
        GreetingEntry ge = new GreetingEntry();
        ge.setId(null);
        ge.setName(name);
        ge=greetingEntryDao.save(ge);

        return new Greeting(ge.getId(),ge.getName());
    }

    public GreetingEntryDao getGreetingEntryDao() {
        return greetingEntryDao;
    }

    public void setGreetingEntryDao(GreetingEntryDao greetingEntryDao) {
        this.greetingEntryDao = greetingEntryDao;
    }
}
