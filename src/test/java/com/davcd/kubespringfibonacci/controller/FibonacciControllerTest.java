package com.davcd.kubespringfibonacci.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FibonacciControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void shouldReturnResponseOK_whenNIsValid() throws Exception {
		MvcResult result = mockMvc.perform(
						get("/fib")
								.queryParam("n", "1"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(TEXT_PLAIN))
				.andReturn();
		assertThat(result.getResponse().getContentAsByteArray()).isNotEmpty();
	}
	
	@ParameterizedTest
	@CsvSource({
			",Invalid request",
			"string,Invalid request",
			"-1,Parameter 'n' must be positive"
	})
	void shouldReturnResponseBadRequest_whenNIsMissing(String nValue, String expectedResponse) throws Exception {
		MvcResult result = mockMvc.perform(
						get("/fib")
								.queryParam("n", nValue))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentTypeCompatibleWith(TEXT_PLAIN))
				.andReturn();
		assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedResponse);
	}
	
}
