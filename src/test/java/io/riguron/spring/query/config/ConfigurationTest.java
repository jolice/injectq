package io.riguron.spring.query.config;

import io.riguron.spring.query.SomeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("config")
public class ConfigurationTest {

    @Autowired
    private SomeRepository someRepository;

    @Test
    public void doTest() {
        assertEquals(someRepository.getConstructorQuery(), "select 'scripts/constructor';");
        assertEquals(someRepository.getFieldQuery(), "select 'scripts/field';");
        assertEquals(someRepository.getSetterQuery(), "select 'scripts/setter';");
    }
}
