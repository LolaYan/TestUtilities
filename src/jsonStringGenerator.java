import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.simple.*;

public class jsonStringGenerator {
	public static void main(String[] args) throws JSONException {
		String urlStr = "http://192.168.100.72:8081/api/core/v1/accounts/status";
		String reqParams = 	getEmailActivationRequestBody("12312");

		
		sendHttpPutReq( urlStr,
				 reqParams);
	}

	public static String getEmailActivationRequestBody(String activationId) throws JSONException{
		JSONObject obj = new JSONObject();
		obj.put("activationId", activationId);

		JSONObject obj2 = new JSONObject();
		obj2.put("language", "en-GB,en-US;q=0.8,en;q=0.6");
		obj2.put("encoding", "gzip,deflate,sdch");
		obj2.put("charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
		obj2.put("clientIP", "202.160.48.160");
		obj2.put(
				"browser",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");

		obj.put("geofilterInfo", obj2);
		String res = obj.toString();
		
		//System.out.print(res);
		return res;
	}
	public static BufferedReader sendHttpPutReq(String urlStr,
			String reqParams) {

		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setUseCaches(false);
			connection.connect();

			String input = reqParams;

			OutputStream os = connection.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(connection.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			connection.disconnect();

			return br;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
