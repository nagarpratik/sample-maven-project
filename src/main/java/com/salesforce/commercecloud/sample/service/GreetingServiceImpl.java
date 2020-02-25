package com.salesforce.commercecloud.sample.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

  @Override
  public String sayHello(String languageCode) {
    // Just defaulting to English.
    return "Hello World!";
  }
}
