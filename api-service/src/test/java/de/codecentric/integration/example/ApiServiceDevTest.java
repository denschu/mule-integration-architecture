package de.codecentric.integration.example;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mule.tck.junit4.FunctionalTestCase;
import org.springframework.web.client.RestTemplate;

public class ApiServiceDevTest  extends FunctionalTestCase {

	@Override
	protected String getConfigResources() {
		return "src/main/app/api-service.xml";
	}

	@Override
	protected int getTimeoutSystemProperty() {
		return 60 * 60; // 1 hour
	}
	
	@Test
	public void testClient() throws Exception {
		
		// Given
		RestTemplate restTemplate = new RestTemplate();
        	
		// When
		String response = restTemplate.getForObject("http://localhost/example/v1/customers/1",String.class);
		
		
		// Then
		assertNotNull(response);
		assertThat(response, containsString("helloWorld-v1"));
	}
}
