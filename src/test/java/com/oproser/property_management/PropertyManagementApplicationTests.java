package com.oproser.property_management;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class PropertyManagementApplicationTests {

	@Test
    void contextLoads() throws UnsupportedOperationException {
        Logger log = Logger.getLogger(this.getClass().getName());
        log.info(this.getClass().getName());
	}

}
