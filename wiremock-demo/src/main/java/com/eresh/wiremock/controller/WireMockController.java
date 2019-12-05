package com.eresh.wiremock.controller;

import com.eresh.wiremock.controller.ws.RestResponse;
import com.eresh.wiremock.controller.ws.WSUserDetails;
import com.eresh.wiremock.util.BaseRestOutboundProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created By Gorantla, Eresh on 04/Dec/2019
 **/
@RestController
@RequestMapping("/api")
public class WireMockController {

	@Autowired
	BaseRestOutboundProcessor baseRestOutboundProcessor;

	@Value("${third.party.base.url}")
	String baseUrl;

	@PostMapping("/user")
	public ResponseEntity<RestResponse> saveUserDetails(@RequestBody WSUserDetails request) {
		Map<String, String> headers = new HashMap<>();
		headers.put("access-key", UUID.randomUUID()
		                              .toString());
		headers.put("secret-key", UUID.randomUUID()
		                              .toString());
		ResponseEntity<WSUserDetails> responseEntity = baseRestOutboundProcessor.post(baseUrl + "api/user", request, WSUserDetails.class,
		                                                                             headers);
		return ResponseEntity.ok(responseEntity != null ? responseEntity.getBody() : null);
	}

	@PutMapping("/user")
	public ResponseEntity<RestResponse> saveUserData(@RequestBody WSUserDetails request) {
		Map<String, String> headers = new HashMap<>();
		headers.put("access-key", UUID.randomUUID()
		                              .toString());
		headers.put("secret-key", UUID.randomUUID()
		                              .toString());
		ResponseEntity<WSUserDetails> responseEntity = baseRestOutboundProcessor.put(baseUrl + "/api/user", request, WSUserDetails.class,
		                                                                            headers);
		return ResponseEntity.ok(responseEntity != null ? responseEntity.getBody() : null);
	}

	@GetMapping("/user")
	public ResponseEntity<RestResponse> getUserData(@RequestBody WSUserDetails request) {
		Map<String, String> headers = new HashMap<>();
		headers.put("access-key", UUID.randomUUID()
		                              .toString());
		headers.put("secret-key", UUID.randomUUID()
		                              .toString());
		ResponseEntity<WSUserDetails> responseEntity = baseRestOutboundProcessor.get(baseUrl + "/api/user", request, WSUserDetails.class,
		                                                                            headers);
		return ResponseEntity.ok(responseEntity != null ? responseEntity.getBody() : null);
	}

	@DeleteMapping("/user")
	public ResponseEntity<RestResponse> deleteUser(@RequestBody WSUserDetails request) {
		Map<String, String> headers = new HashMap<>();
		headers.put("access-key", UUID.randomUUID()
		                              .toString());
		headers.put("secret-key", UUID.randomUUID()
		                              .toString());
		ResponseEntity<RestResponse> responseEntity = baseRestOutboundProcessor.delete(baseUrl + "api/user", request, RestResponse.class,
		                                                                               headers);
		return ResponseEntity.ok(responseEntity != null ? responseEntity.getBody() : null);
	}
}
