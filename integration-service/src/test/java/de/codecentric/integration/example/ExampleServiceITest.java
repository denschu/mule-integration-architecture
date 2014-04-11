package de.codecentric.integration.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.util.IOUtils;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import com.example.xmlns.customers.BackendCustomersWS;
import com.example.xmlns.customers.GetCustomerRequest;
import com.example.xmlns.customers.GetCustomerResponse;

public class ExampleServiceITest extends FunctionalTestCase {
	
	@Override
	protected String getConfigResources() {
		return "src/main/app/backend-mock.xml,src/main/app/customer-api.xml";
	}

	@Override
	protected int getTimeoutSystemProperty() {
		return 60 * 60; // 1 hour
	}
	
	@Test
	public void testRestApi() throws IOException, SAXException {
		
		// Given
		RestTemplate restTemplate = new RestTemplate();
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreComments(true);
        String expectedResponse = IOUtils.getResourceAsString("src/test/resources/GetBackendExampleResponse.xml", getClass());
        	
		// When
		String response = restTemplate.getForObject("http://localhost:8081/api/customers/1",String.class);
		
		// Then
		assertNotNull(response);
		//assertTrue(XMLUnit.compareXML(response, expectedResponse).similar());
		assertEquals("test",response);
	}
	
	@Test
	public void testBackend() {

		// Given
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(BackendCustomersWS.class); 
		factory.setAddress("http://localhost:9080/example-backend?connector=http-backend");
		BackendCustomersWS client = (BackendCustomersWS) factory.create();
		GetCustomerRequest request = new GetCustomerRequest();
		request.setExampleId("1");

		// When
		GetCustomerResponse response = (GetCustomerResponse)client.getBackendCustomer(request);
		
		// Then
		assertNotNull(response);
	}
	
}
