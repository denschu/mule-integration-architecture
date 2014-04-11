package de.codecentric.backend.example;

import com.example.xmlns.customers.GetCustomerRequest;
import com.example.xmlns.customers.GetCustomerResponse;


public class ExampleBackendMock {

	public GetCustomerResponse getBackendExample(GetCustomerRequest request) throws Exception {
		GetCustomerResponse response = new GetCustomerResponse();
		response.setName("Hello World!");
		return response;
	}

}
