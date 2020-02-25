package com.salesforce.commercecloud.sample.rest.contract;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesforce.commercecloud.sample.api.generated.GreetingsApiController;
import com.salesforce.commercecloud.sample.service.GreetingService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

/**
 * Base Class for Consumer Driven Contract Testing
 * https://spring.io/guides/gs/contract-rest/
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class BaseClass {
 
  @Autowired
  GreetingsApiController controller;

  @MockBean
  GreetingService greetingService;
  
  @Before
  public void setup() {
   RestAssuredMockMvc.standaloneSetup(controller);

    Mockito.when(greetingService.sayHello("en_US"))
      .thenReturn("Hello World!");
  }
}
