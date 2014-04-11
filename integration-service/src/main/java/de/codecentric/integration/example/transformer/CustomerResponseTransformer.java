package de.codecentric.integration.example.transformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.example.xmlns.customers.GetCustomerResponse;

public class CustomerResponseTransformer extends AbstractTransformer {

	protected static final Log logger = LogFactory.getLog(CustomerResponseTransformer.class);

	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
		GetCustomerResponse backendResponse = (GetCustomerResponse) src;
		//GetExampleResponse response = new GetExampleResponse();
		//response.setName(backendResponse.getName());
		return "test";//return response;
	}

}
