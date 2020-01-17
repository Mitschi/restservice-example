package com.github.mitschi.restservice;

import com.github.mitschi.restservice.api.GreetingService;
import com.github.mitschi.restservice.entity.GreetingEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


//GreetingServiceIntegrationTest Tests whether the integration of our Service Layer with the Spring JPA/Dao mechanism and the database is working
//@RunWith(SpringRunner.class)
@DataJpaTest
public class GreetingServiceIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GreetingService greetingService;

    private GreetingEntry expected;

    @BeforeEach
    public void setUp() {
        // given
        expected = new GreetingEntry(null,"test1");
        entityManager.persist(expected);
        entityManager.flush();
    }

    @Test
    public void whenGetAllStoredGreetings_thenReturnOneExactlyTest1() {
        List<Greeting> allStoredGreetings = greetingService.getAllStoredGreetings();

        assertEquals(1,allStoredGreetings.size());
        assertEquals(allStoredGreetings.get(0).getContent(),"test1");
    }

    @Test
    public void whenStoreAnotherGreeting_thenReturnTwoNamelyTest1AndTest2() {
        greetingService.storeGreeting("test2");

        List<Greeting> allStoredGreetings = greetingService.getAllStoredGreetings();

        assertEquals(2,allStoredGreetings.size());
        assertEquals(allStoredGreetings.get(0).getContent(),"test1");
        assertEquals(allStoredGreetings.get(1).getContent(),"test2");
    }
}
