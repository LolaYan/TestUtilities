package HTTP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import javax.net.ssl.HttpsURLConnection;

public class RESTHttpGETRequest {

	public static String urlStr = "http://192.168.100.196/api/core/v1/sessions";

	public static String email = "test4288437test@SoapuiTest.com";
	public static String password = "password1";

	protected final static Map<String, String> customHeaders = new HashMap<String, String>();

	public static URL url;
	public static HttpURLConnection urlConnection;
	public static DataOutputStream outStream;
	public static DataInputStream inStream;
	public static String requestBody;
	public static String responseBody;

	public static void main(String[] args) throws Exception {

		try {
			// GET HTTP Demo
			CoreGameGetVersion();
			parseJson(responseBody).get("version");
			
			// POST HTTP Demo
			CoreGameLogin();
			String uid = parseJson(responseBody).get("userId").toString();
			String sid = parseJson(responseBody).get("sessionId").toString();
			System.out.println("\r\n sessionId: " + sid);
			
			//PUT HTTP Demo
			UpdateUserProfile(uid,sid);
			parseJson(responseBody).get("lastLogon");

		} catch (Exception ex) {
			System.out.println("Exception cought:\n" + ex.toString());
		}
	}

	public static void CoreGameGetVersion() throws IOException {
		// GET HTTP Demo
		urlStr = "http://192.168.100.196/api/core/v1/systems/versions";
		executeGETRequest();
	}

	public static void CoreGameLogin() throws IOException {
		// POST HTTP Demo
		urlStr = "http://192.168.100.196/api/core/v1/sessions";
		requestBody = getLoginRequestBody(email,
				password);
		putHeader("Content-Type", "application/json");
		executePOSTRequest();
	}

	public static void UpdateUserProfile(String uid, String sid) throws IOException {
		// POST HTTP Demo
		urlStr = "http://192.168.100.196/api/core/v1/accounts/"+uid;
		requestBody = getUpdateUserProfileRequestBody("Auckland","Auckland City","64 223190877","password1");
		putHeader("Content-Type", "application/json");
		putHeader("USER_ID",uid);
		putHeader("ESI_SID",sid);
		executePUTRequest();
	}
	
	public static void executeGETRequest() throws IOException {
		try {
			createConnection();
			requestSetup();
			setRequestMethod("GET");
			receiveResponse();
			readResponse();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void executePOSTRequest() throws IOException {
		// Create connection
		createConnection();
		setRequestMethod("POST");
		requestSetup();
		// setting head
		requestHeaderSet();
		// Create I/O streams
		createIOStream();
		// Send request
		sendPostRequest();
		// Get Response
		receiveResponse();
		// Read Response
		readResponse();
		closeStream();
	}
	public static void executePUTRequest() throws IOException {
		// Create connection
		createConnection();
		setRequestMethod("PUT");
		requestSetup();
		// setting head
		requestHeaderSet();
		// Create I/O streams
		createIOStream();
		// Send request
		sendPostRequest();
		// Get Response
		receiveResponse();
		// Read Response
		readResponse();
		closeStream();
	}
	public static void createConnection() throws IOException {
		URL url = new URL(urlStr);
		urlConnection = (HttpURLConnection) url.openConnection();
		System.out.println("\r\nConnect API: " + url);
	}

	public static void setRequestMethod(String method) throws IOException {
		urlConnection.setRequestMethod(method);
	}

	public static void createIOStream() throws IOException {
		// Create I/O streams
		outStream = new DataOutputStream(urlConnection.getOutputStream());

	}

	public static void sendPostRequest() throws IOException {
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

	public static void putHeader(String name, String value) {
		customHeaders.put(name, value);
	}

	public static void requestHeaderSet() {
		// Get a set of the entries
		Set set = customHeaders.entrySet();
		// Get an iterator
		Iterator i = set.iterator();
		// Display elements
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			urlConnection.setRequestProperty(me.getKey().toString(),
					(String) me.getValue());
			System.out.print("Setting header " + me.getKey() + ": "
					+ me.getValue());
		}
	}

	public static void requestSetup() throws IOException {
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);
		urlConnection.setUseCaches(false);

	}

	public static void closeStream() throws IOException {
		// Close I/O streams
		inStream.close();
		outStream.close();
	}

	public static void readResponse() throws IOException {
		// - For debugging purposes only!
		responseBody = "";
		String buffer;
		System.out.println("Read Response: ");
		while ((buffer = inStream.readLine()) != null) {
			System.out.println(buffer);
			responseBody = responseBody + buffer;
		}
		//System.out.println(responseBody);
	}

	public static String getResponseCode() throws IOException {
		int Responsecode = urlConnection.getResponseCode();
		String ResponseCode = String.valueOf(Responsecode);
		return ResponseCode;
	}

	public static String getResponseMessage() throws IOException {

		String ResponseMessage = urlConnection.getResponseMessage();
		return ResponseMessage;
	}

	public static String getLoginRequestBody(String email, String pwd) {
		JSONObject obj = new JSONObject();
		obj.put("userName", email);
		obj.put("password", pwd);
		String res = obj.toString();
		System.out.print(res);
		return res;
	}
	
	public static String getUpdateUserProfileRequestBody(String tlaArea, String tlaLocation,String contactPhone, String password) {
		JSONObject obj = new JSONObject();
		obj.put("tlaArea", tlaArea);
		obj.put("tlaLocation", tlaLocation);
		obj.put("contactPhone", contactPhone);
		obj.put("password", password);
		String res = obj.toString();
		System.out.print(res);
		return res;
	}
	public static JSONObject parseJson(String str) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(str);
		return jsonObject;
	}
}