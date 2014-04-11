package de.codecentric.integration.example.transformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.DataTypeFactory;

import com.example.xmlns.customers.GetCustomerRequest;

public class CustomerRequestTransformer extends AbstractTransformer {

	protected static final Log logger = LogFactory
			.getLog(CustomerRequestTransformer.class);

	public CustomerRequestTransformer() {
		super();
		//this.registerSourceType(DataTypeFactory.create(GetExampleRequest.class));
		this.setReturnDataType(DataTypeFactory
				.create(GetCustomerRequest.class));
	}

	@Override
	protected Object doTransform(Object arg0, String arg1)
			throws TransformerException {
		//GetExampleRequest request = (GetExampleRequest) arg0;
		GetCustomerRequest backendRequest = new GetCustomerRequest();
		backendRequest.setExampleId("1");
		return backendRequest;
	}

}
