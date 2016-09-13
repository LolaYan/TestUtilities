package com.lottoNZ.JMeterTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;

public class MuleCoreGamingClass extends AbstractJavaSamplerClient implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private final String USER_AGENT = "Mozilla/5.0";
	
	
	// set up default arguments for the JMeter GUI
	@Override
	public Arguments getDefaultParameters() {
		Arguments defaultParameters = new Arguments();
		defaultParameters.addArgument("URL",
				"http://192.168.100.196/api/core/v1/sessions");
		defaultParameters.addArgument("userid", "106560024");
		defaultParameters.addArgument("sessionid", "POIJOY76N8U10HSKJW9ATWJFO7TTHS7I");
		return defaultParameters;
	}

	@Override
	public SampleResult runTest(JavaSamplerContext context) {
		// pull parameters
		String urlString = context.getParameter("URL");
		String userid = context.getParameter("userid");
		String sessionid = context.getParameter("sessionid");
		SampleResult result = new SampleResult();
		result.sampleStart(); // start stopwatch

		try {
			java.net.URL url = new java.net.URL(urlString + "/" + userid);
			java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url
					.openConnection(); // have to cast connection
			connection.setRequestMethod("GET");
			connection.setRequestProperty("USER_ID", userid);
			connection.setRequestProperty("ESI_SID", sessionid);
			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.connect();

			int responseCode = connection.getResponseCode();
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			result.sampleEnd(); // stop stopwatch
			
			
			if(responseCode == 200)
			{
				result.setSuccessful(true);
				result.setResponseCodeOK(); // 200 code
				//result.setResponseMessage("Successfully performed action");
				result.setResponseMessage(response.toString());
		        byte[] response1 = response.toString().getBytes();
				result.setResponseData(response1);
			}
			
		} catch (Exception e) {
			result.sampleEnd(); // stop stopwatch
			result.setSuccessful(false);
			result.setResponseMessage("Exception: " + e);

			// get stack trace as a String to return as document data
			java.io.StringWriter stringWriter = new java.io.StringWriter();
			e.printStackTrace(new java.io.PrintWriter(stringWriter));
			result.setResponseData(stringWriter.toString());
			result.setDataType(org.apache.jmeter.samplers.SampleResult.TEXT);
			result.setResponseCode("500");
		}

		return result;
	}
}
