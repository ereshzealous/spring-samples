package com.eresh.wiremock.contoller;

import com.eresh.wiremock.controller.WireMockController;
import com.eresh.wiremock.controller.ws.RestResponse;
import com.eresh.wiremock.controller.ws.WSUserDetails;
import com.eresh.wiremock.util.CustomObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created By Gorantla, Eresh on 05/Dec/2019
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestWireMockController {

	@ClassRule
	public static WireMockRule wireMockRule = new WireMockRule(8900);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	Environment environment;

	@Autowired
	WireMockController controller;

	/*@Value("#{systemproperties['server.port']}")
	private Integer port;*/

	ObjectMapper objectMapper = new CustomObjectMapper();

	@Before
	public void init() {
		ReflectionTestUtils.setField(controller, "baseUrl", "http://localhost:8900/");
		wireMockRule.resetMappings();
		wireMockRule.resetScenarios();
		wireMockRule.resetRequests();
	}

	@Test
	public void testSaveUserDetails() throws Exception {
		WSUserDetails expectedResponse = generateWSUserDetails();
		String expectedResponseString = objectMapper.writeValueAsString(expectedResponse);
		wireMockRule.stubFor(WireMock.post(WireMock.urlMatching("/api/user"))
		                             .willReturn(WireMock.aResponse()
		                                                 .withBody(expectedResponseString)
		                                                 .withStatus(HttpStatus.OK.value())
		                                                 .withHeader("Content-Type",
		                                                             "application/json;charset=UTF-8")));
		ResponseEntity<RestResponse> responseEntity = controller.saveUserDetails(null);
		assertNotNull(responseEntity);
		assertTrue(responseEntity.getBody() instanceof WSUserDetails);
		WSUserDetails actualResponse = (WSUserDetails) responseEntity.getBody();
		assertNotNull(actualResponse);
		assertUserDetails(expectedResponse, actualResponse);
	}

	@Test
	public void testSaveUserDetails_Error() throws Exception {
		wireMockRule.stubFor(WireMock.post(WireMock.urlMatching("/api/user"))
		                             .willReturn(WireMock.aResponse()
		                                                 .withStatus(HttpStatus.BAD_REQUEST.value())

		                                                 .withHeader("Content-Type",
		                                                             "application/json;charset=UTF-8")));
		try {
			ResponseEntity<RestResponse> responseEntity = controller.saveUserDetails(null);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST.value(), e.getRawStatusCode());
		}
	}

	@Test
	public void testSaveUserData() throws Exception {
		WSUserDetails expectedResponse = generateWSUserDetails();
		String expectedResponseString = objectMapper.writeValueAsString(expectedResponse);
		wireMockRule.stubFor(WireMock.put(WireMock.urlMatching("/api/user"))
		                             .willReturn(WireMock.aResponse()
		                                                 .withBody(expectedResponseString)
		                                                 .withStatus(HttpStatus.OK.value())
		                                                 .withHeader("Content-Type",
		                                                             "application/json;charset=UTF-8")));
		ResponseEntity<RestResponse> responseEntity = controller.saveUserData(null);
		assertNotNull(responseEntity);
		assertTrue(responseEntity.getBody() instanceof WSUserDetails);
		WSUserDetails actualResponse = (WSUserDetails) responseEntity.getBody();
		assertNotNull(actualResponse);
		assertUserDetails(expectedResponse, actualResponse);
	}

	@Test
	public void testGetUserData() throws Exception {
		WSUserDetails expectedResponse = generateWSUserDetails();
		String expectedResponseString = objectMapper.writeValueAsString(expectedResponse);
		wireMockRule.stubFor(WireMock.get(WireMock.urlMatching("/api/user"))
		                             .willReturn(WireMock.aResponse()
		                                                 .withBody(expectedResponseString)
		                                                 .withStatus(HttpStatus.OK.value())
		                                                 .withHeader("Content-Type",
		                                                             "application/json;charset=UTF-8")));
		ResponseEntity<RestResponse> responseEntity = controller.getUserData(null);
		assertNotNull(responseEntity);
		assertTrue(responseEntity.getBody() instanceof WSUserDetails);
		WSUserDetails actualResponse = (WSUserDetails) responseEntity.getBody();
		assertNotNull(actualResponse);
		assertUserDetails(expectedResponse, actualResponse);
	}

	private WSUserDetails generateWSUserDetails() {
		WSUserDetails details = new WSUserDetails();
		details.setCity(RandomStringUtils.randomAlphabetic(10));
		details.setCountry(RandomStringUtils.randomAlphabetic(10));
		details.setDateOfBirth(LocalDate.now());
		details.setFirstName(RandomStringUtils.randomAlphabetic(10));
		details.setLastName(RandomStringUtils.randomAlphabetic(10));
		details.setId(UUID.randomUUID()
		                  .toString());
		return details;
	}

	private void assertUserDetails(WSUserDetails expected, WSUserDetails actual) {
		assertEquals(expected.getCity(), actual.getCity());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
		assertEquals(expected.getCountry(), actual.getCountry());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
	}
}
