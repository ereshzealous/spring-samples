package com.eresh.wiremock.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Created By Gorantla, Eresh on 04/Dec/2019
 **/

public class CustomObjectMapper extends ObjectMapper {

	public CustomObjectMapper() {
		super();
        /*setSerializationInclusion(JsonInclude.Include.NON_NULL);
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY);*/
		configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		registerModule(new JavaTimeModule());
	}
}
