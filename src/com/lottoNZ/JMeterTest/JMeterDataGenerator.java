package com.lottoNZ.JMeterTest;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.json.simple.JSONObject;

import BankAccountUtils.BankAccountGenerator;
import BankAccountUtils.DataGenerator;

public class JMeterDataGenerator extends AbstractJavaSamplerClient {
	@Override
	public void setupTest(JavaSamplerContext context) {
		// TODO Auto-generated method stub

		super.setupTest(context);
	}

	@Override
	public Arguments getDefaultParameters() {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		SampleResult result = new SampleResult();
		boolean success = true;
		result.sampleStart();

		// Write your test code here.
		String AccountNumber = BankAccountGenerator.getBankAccount();
		String PhoneNumber = DataGenerator.GetPhoneNumber();
        String Email = DataGenerator.generateEmail();
        
		JSONObject obj = new JSONObject();
		obj.put("AccountNumber", AccountNumber);
		obj.put("PhoneNumber", PhoneNumber);
		obj.put("Email", Email);
		
		String res = obj.toString();
		
		result.sampleEnd(); // stop stopwatch
        result.setSuccessful( true );
        result.setResponseMessage(res);
        byte[] response = res.getBytes();
		result.setResponseData(response);
        result.setResponseCodeOK(); // 200 code     
        
		
		return result;
	}

}
