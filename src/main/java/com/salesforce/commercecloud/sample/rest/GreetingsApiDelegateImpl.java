package com.salesforce.commercecloud.sample.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.salesforce.commercecloud.sample.service.GreetingService;
import com.salesforce.commercecloud.sample.api.generated.GreetingsApiDelegate;
import com.salesforce.commercecloud.sample.api.generated.GreetingModel;
import com.salesforce.commercecloud.sample.api.generated.GreetingResponse;
import com.salesforce.commercecloud.sample.api.generated.StatusResponse.StatusEnum;

@Component
public class GreetingsApiDelegateImpl implements GreetingsApiDelegate {
  private static final Logger LOGGER = LoggerFactory.getLogger(GreetingsApiDelegateImpl.class);
  
  @Autowired
  private GreetingService greetingService;
  
  @Override
  public ResponseEntity<GreetingResponse>  greetingsLocalesLanguageCodeGet(String languageCode) {
    String greeting = greetingService.sayHello(languageCode);
    
    LOGGER.info("Greeting retrieved from service is:{}", greeting);

    GreetingResponse response = new GreetingResponse();
    response.setStatus(StatusEnum.SUCCESS);
    response.setData(new GreetingModel().greeting(greeting));

    return ResponseEntity.ok(response);
  }
}
