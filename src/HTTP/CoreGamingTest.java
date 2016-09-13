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

public class CoreGamingTest extends RestHttpRequestBase{

	public static String email = "test4288437test@SoapuiTest.com";
	public static String password = "password1";


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
		// PUT HTTP Demo
		urlStr = "http://192.168.100.196/api/core/v1/accounts/"+uid;
		requestBody = getUpdateUserProfileRequestBody("Auckland","Auckland City","64 223190877","password1");
		putHeader("Content-Type", "application/json");
		putHeader("USER_ID",uid);
		putHeader("ESI_SID",sid);
		executePUTRequest();
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