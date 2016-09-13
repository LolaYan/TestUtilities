package oauth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import com.google.api.client.auth.oauth.OAuthAuthorizeTemporaryTokenUrl;
import com.google.api.client.auth.oauth.OAuthCredentialsResponse;
import com.google.api.client.auth.oauth.OAuthGetTemporaryToken;
import com.google.api.client.auth.oauth.OAuthHmacSigner;
//import com.google.api.client.extensions.appengine.http.UrlFetchTransport;

public class requestToken {
	String RequestTokenUrl = "";
	  static final String AUTH_CALLBACK_SERVLET_PATH = "/oauth2callback";
	  //static final UrlFetchTransport HTTP_TRANSPORT = new UrlFetchTransport();
	  private static final String CONSUMER_KEY = "NN2UXHFSN9BKVUJ0VQT6YF31ZNWSHU";
	  private static final String CONSUMER_SECRET = "AJBXRTVGBZ4YOPRI8QAWBWPYA50RX6";
	  private static final String API_ENDPOINT_URL = "https://api.xero.com/api.xro/2.0/";
	  private static final String TOKEN_SERVER_URL = "https://api.xero.com/oauth/RequestToken";
	  private static final String AUTHENTICATE_URL = "https://api.xero.com/oauth/Authorize";
	  private static final String ACCESS_TOKEN_URL = "https://api.xero.com/oauth/AccessToken";

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		doGet();
	}
	
	public static void doGet() throws IOException{
		

		OAuthHmacSigner signer = new OAuthHmacSigner();
		
		// Get Temporary Token
		OAuthGetTemporaryToken getTemporaryToken = new OAuthGetTemporaryToken(TOKEN_SERVER_URL);
		signer.clientSharedSecret = CONSUMER_KEY;

		getTemporaryToken.signer = signer;
		getTemporaryToken.consumerKey = CONSUMER_SECRET;
		getTemporaryToken.callback = AUTH_CALLBACK_SERVLET_PATH;
		//getTemporaryToken.transport = Utils.HTTP_TRANSPORT;
		OAuthCredentialsResponse temporaryTokenResponse = getTemporaryToken
				.execute();
		System.out.println(temporaryTokenResponse.toString());
/*
		// Store Temporary Token (from getToken request) in Cookie
		String tempToken = temporaryTokenResponse.token;
		Cookie t = new Cookie("temptoken", tempToken);
		resp.addCookie(t);

		// Store Temporary Token Secret (from get Token request) in Cookie
		String tempTokenSecret = temporaryTokenResponse.tokenSecret;
		Cookie s = new Cookie("temptokensecret", tempTokenSecret);
		resp.addCookie(s);

		// Build Authoirze URL
		OAuthAuthorizeTemporaryTokenUrl accessTempToken = new OAuthAuthorizeTemporaryTokenUrl(AUTHENTICATE_URL);
		accessTempToken.temporaryToken = temporaryTokenResponse.token;
		accessTempToken.set("oauth_callback", AUTH_CALLBACK_SERVLET_PATH);
		String authUrl = accessTempToken.build();

		PrintWriter respWriter = resp.getWriter();
		resp.setStatus(200);
		resp.setContentType("text/html");
		respWriter.println("<i>Temporary Token: " + tempToken + "</i><br><br>");
		respWriter.println("<i>Temporary Token Secret: " + tempTokenSecret
				+ "</i><br><br>");
		respWriter.println("<i>Authorize URL: " + authUrl + "</i><br><br>");
		respWriter.println("<a href='" + authUrl
				+ "'>Continue OAuth Flow</a><br><br>");

		// Redirect to Authorize URL in order to get Verifier Code
		// resp.sendRedirect(authUrl);
*/		
	}

}
