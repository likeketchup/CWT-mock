package com.example.customersystem;

import com.example.customersystem.controller.CustomerControllerTest;
import com.example.customersystem.service.CustomerServiceTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
	CustomerControllerTest.class, CustomerServiceTest.class
})
class CustomerSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
