package com.salesforce.commercecloud.sample.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.salesforce.commercecloud.sample.dto.GreetingResponse;

@RestController
public class GreetingsApiController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingsApiController.class);

	public ResponseEntity<GreetingResponse> greetingsLocalesLanguageCodeGet(String languageCode) {

		GreetingResponse response = new GreetingResponse();

		return ResponseEntity.ok(response);
	}
}
