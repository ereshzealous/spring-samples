package com.eresh.wiremock.controller.ws;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created By Gorantla, Eresh on 05/Dec/2019
 **/
@Getter
@Setter
@NoArgsConstructor
public class RestResponse {
	private String result = "Success";
	private String errorCode;
	private String errorMessage;
}
