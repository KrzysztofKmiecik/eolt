package com.java26.eolt.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
class EoltControllerTests {

    @Autowired
    private EoltController eoltController;

    @Test
    void contextLoads() {
        assertThat(eoltController).isNotNull();
    }


}
