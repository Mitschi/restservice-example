package com.github.mitschi.restservice;

import com.github.mitschi.restservice.dao.GreetingEntryDao;
import com.github.mitschi.restservice.entity.GreetingEntry;
import com.github.mitschi.restservice.services.SpringGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


//@RunWith(MockitoJUnitRunner.class) // we now run the test with the mockito runner (because we use the mockito annotations)
@ExtendWith(MockitoExtension.class)
public class GreetingServiceAddOneUnitTest {

    @Mock
    private GreetingEntryDao mockedGreetingEntryDao;

    //test data
    private GreetingEntry firstEntry = new GreetingEntry(1l,"test1");
    private GreetingEntry secondEntry = new GreetingEntry(null,"test2");
    private GreetingEntry secondEntryWithId = new GreetingEntry(2l,"test2");
    private SpringGreetingService greetingService; // we're testing now the implementation (class) and not the API (interface)

    private GreetingEntry expected;

    @BeforeEach
    public void setUp() {
        //setup mocked dao

        List<GreetingEntry> initialList = new ArrayList<>();
        initialList.add(firstEntry);
        initialList.add(secondEntryWithId);
        when(mockedGreetingEntryDao.findAll()).thenReturn(initialList);
        when(mockedGreetingEntryDao.save(secondEntry)).thenReturn(secondEntryWithId);

        //setup service
        greetingService = new SpringGreetingService();
        greetingService.setGreetingEntryDao(mockedGreetingEntryDao);
    }

    @Test
    public void whenStoreAnotherGreeting_thenReturnTwoNamelyTest1AndTest2() {
        greetingService.storeGreeting("test2");

        verify(mockedGreetingEntryDao).save(secondEntry);

        List<Greeting> allStoredGreetings = greetingService.getAllStoredGreetings();

        assertEquals(2,allStoredGreetings.size());
        assertEquals(allStoredGreetings.get(0).getContent(),"test1");
        assertEquals(allStoredGreetings.get(1).getContent(),"test2");

        //additional assertions/verifications that Mockito allows
        verify(mockedGreetingEntryDao).findAll();
    }
}
