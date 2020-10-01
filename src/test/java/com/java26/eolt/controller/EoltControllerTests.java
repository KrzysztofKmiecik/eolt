package com.java26.eolt;

import com.java26.eolt.Utils.UtilsTests;
import com.java26.eolt.controller.EoltController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class EoltApplicationTests{

@Autowired
private EoltController eoltController;

    @Test
    void contextLoads() {
        assertThat(eoltController).isNotNull();
    }


}
