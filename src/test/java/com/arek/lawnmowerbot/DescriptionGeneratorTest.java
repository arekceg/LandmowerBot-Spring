package com.arek.lawnmowerbot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptionGeneratorTest {
    @Autowired
    private DescriptionGenerator descriptionGenerator;

    @Test
    public void contextLoads() throws Exception {
        // when
        String description = descriptionGenerator.makeDescription();

        // then
        assert description != null;
    }

}
