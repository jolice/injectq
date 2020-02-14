package io.riguron.spring.query.basic;

import io.riguron.spring.query.SomeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BasicFunctionalityTest {

    @Autowired
    private SomeRepository someRepository;

    @Test
    public void doTest() {
       assertEquals(someRepository.getConstructorQuery(), "select 'constructor';");
       assertEquals(someRepository.getFieldQuery(), "select 'field';");
       assertEquals(someRepository.getSetterQuery(), "select 'setter';");
    }

}
