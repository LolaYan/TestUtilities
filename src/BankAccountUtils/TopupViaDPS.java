package BankAccountUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TopupViaDPS {
	/*
	public static void main(String[] args) throws IOException {
		//
		String sid = "000003006207135900b5de87236b0b1f";
		if(args.length > 0)
		{
			sid = args[0];
		}
		DPSCreditRegister(sid);

	}
*/
	public static int DPSCreditRegister(String sid) throws IOException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType
				.parse("multipart/form-data; boundary=---011000010111000001101001");
		RequestBody body = RequestBody
				.create(mediaType,
						"-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"CardNumber\"\r\n\r\n4111111111111111\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"ExpiryMonth\"\r\n\r\n12\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"ExpiryYear\"\r\n\r\n22\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"SessionId\"\r\n\r\n"
								+ sid
								+ "\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"Cvc2\"\r\n\r\n123\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"CardHolderName\"\r\n\r\ntest\r\n-----011000010111000001101001--");
		Request request = new Request.Builder()
				.url("https://sec.paymentexpress.com/pxmi3/pxfusionauth")
				.post(body)
				.addHeader("content-type",
						"multipart/form-data; boundary=---011000010111000001101001")
				.addHeader("cache-control", "no-cache")
				.addHeader("postman-token",
						"56fa25ad-bf20-7bf3-0803-cd6c6dea4812").build();

		Response response = client.newCall(request).execute();
		// System.out.println(response.message());
		// System.out.println(response.body());
		return response.code();
	}
}
