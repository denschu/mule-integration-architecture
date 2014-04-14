package de.codecentric.integration.example.transformer;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.example.xmlns.customers.GetCustomerResponse;

public class GetExampleToBackendResponseTest {

	@Test
	public void testTransformMessage() throws Exception {
		
		//Given
		CustomerResponseTransformer transformer = new CustomerResponseTransformer();
		
		//When 
		Object response = transformer.doTransform(new GetCustomerResponse(), null);
		
		//Then
		assertNotNull(response);
	}

}
