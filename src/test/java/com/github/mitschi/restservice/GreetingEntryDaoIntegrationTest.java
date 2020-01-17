package com.github.mitschi.restservice;

import com.github.mitschi.restservice.dao.GreetingEntryDao;
import com.github.mitschi.restservice.entity.GreetingEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


//GreetingIntegrationTest Tests whether the integration of our Spring JPA/Dao mechanism with the database is working
@DataJpaTest
public class GreetingEntryDaoIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GreetingEntryDao greetingEntryDao;

    GreetingEntry expected;

    @BeforeEach
    public void setUp() {
        // given
        expected = new GreetingEntry(null,"test1");
        entityManager.persist(expected);
        entityManager.flush();
    }

    @Test
    public void whenFindByNameTest1_thenReturnTest1GreetingEntry() {
        GreetingEntry actual = greetingEntryDao.findByName("test1");

        assertEquals(expected,actual);
    }

    @Test
    public void whenFindByNameTest2_thenReturnNull() {
        GreetingEntry actual = greetingEntryDao.findByName("test2");

        assertNull(actual);
    }
}
