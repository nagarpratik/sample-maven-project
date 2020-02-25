package com.salesforce.commercecloud.sample.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Scope of this test is to ensure that the Greeting Service works 
 * in isolation
 */
public class GreetingServiceTest {
  
  private GreetingServiceImpl greetingService;
  
  @Before
  public void init() {
    greetingService = new GreetingServiceImpl();
  }
  
  @Test
  public void validateGreeting() {
    String languageCode = "en_US";
    String greetingString = "Hello World!";
    
    assertEquals(greetingString, greetingService.sayHello(languageCode));
  }
}
