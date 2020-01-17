package com.github.mitschi.restservice.dao;


import com.github.mitschi.restservice.entity.GreetingEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingEntryDao extends JpaRepository<GreetingEntry, Long> {

    GreetingEntry findByName(String name);
}
