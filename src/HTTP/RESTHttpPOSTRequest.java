package HTTP;

/*
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;

//import javax.net.ssl.HttpsURLConnection;

public class RESTHttpPOSTRequest {

	public static String urlStr = "http://192.168.100.196/api/core/v1/sessions";

	public static String jsonString = "{\"userName: \"test4288437test@SoapuiTest.com\",\"password\": \"password1\"}";

	public static URL url;
	public static URLConnection urlConnection;
	public static DataOutputStream outStream;
	public static DataInputStream inStream;
	public static String requestBody;

	public static void main(String[] args) throws Exception {

		try {

			// Build request body
			requestBody = getLoginRequestBody("test4288437test@SoapuiTest.com",
					"password1");
			executePOSTRequest();

		} catch (Exception ex) {
			System.out.println("Exception cought:\n" + ex.toString());
		}
	}

	public static void executePOSTRequest() throws IOException {
		// Create connection
		createConnection();

		((HttpURLConnection) urlConnection).setRequestMethod("POST");

		requestSetup();
		requestAddHeader("Content-Type", "application/json");

		// Create I/O streams
		createIOStream();

		// Send request
		sendRequest();

		// Get Response
		receiveResponse();

		// Read Response
		readResponse();
		
		closeStream();
	}

	public static void createConnection() throws IOException {
		// Create connection
		url = new URL(urlStr);
		urlConnection = url.openConnection();
	}

	public static void setRequestMethod(String method) throws IOException {
		((HttpURLConnection) urlConnection).setRequestMethod(method);
	}
	public static void createIOStream() throws IOException {
		// Create I/O streams
		outStream = new DataOutputStream(urlConnection.getOutputStream());

	}

	public static void sendRequest() throws IOException {
		// Send request
		outStream.writeBytes(requestBody);
		outStream.flush();
		outStream.close();

	}

	public static void receiveResponse() throws IOException {
		// Get Response
		inStream = new DataInputStream(urlConnection.getInputStream());

	}

	public static void requestAddHeader(String headerName, String headerValue)
			throws IOException {
		urlConnection.setRequestProperty(headerName, headerValue);

	}
	

	
	public static void requestSetup() throws IOException {
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);
		urlConnection.setUseCaches(false);
		// urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setRequestProperty("Content-Length",
				"" + requestBody.length());

	}

	public static void closeStream() throws IOException {
		// Close I/O streams
		inStream.close();
		outStream.close();
	}

	public static void readResponse() throws IOException {
		// - For debugging purposes only!
		String buffer;
		while ((buffer = inStream.readLine()) != null) {
			System.out.println(buffer);
		}

	}

	public static String getLoginRequestBody(String email, String pwd) {
		JSONObject obj = new JSONObject();
		obj.put("userName", email);
		obj.put("password", pwd);
		String res = obj.toString();
		System.out.print(res);
		return res;
	}

}