package de.codecentric.integration.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.mule.tck.junit4.FunctionalTestCase;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import com.example.xmlns.customers.CustomersWS;
import com.example.xmlns.customers.GetCustomerRequest;
import com.example.xmlns.customers.GetCustomerResponse;

public class ExampleServiceITest extends FunctionalTestCase {
	
	@Override
	protected String getConfigResources() {
		return "src/main/app/backend-mock.xml,src/main/app/example-api.xml";
	}

	@Override
	protected int getTimeoutSystemProperty() {
		return 60 * 60; // 1 hour
	}
	
	@Test
	public void testRestApi() throws IOException, SAXException {
		
		// Given
		RestTemplate restTemplate = new RestTemplate();
        	
		// When
		String response = restTemplate.getForObject("http://localhost:8082/example/v1/customers/1",String.class);
		
		// Then
		assertNotNull(response);	
		assertThat(response, containsString("helloWorld-v1"));
	}
	
	@Test
	public void testBackend() {

		// Given
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(CustomersWS.class); 
		factory.setAddress("http://localhost:9080/customer-service?connector=http-backend");
		CustomersWS client = (CustomersWS) factory.create();
		GetCustomerRequest request = new GetCustomerRequest();
		request.setExampleId("1");

		// When
		GetCustomerResponse response = (GetCustomerResponse)client.getCustomer(request);
		
		// Then
		assertNotNull(response);
		assertThat(response.getName(), is(equalTo("helloWorld-v1")));
	}
	
}
