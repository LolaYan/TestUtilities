import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/** Establish a SSL connection to a host and port, writes a byte and
 * prints the response. See
 * http://confluence.atlassian.com/display/JIRA/Connecting+to+SSL+services
 */
public class SSLPoke {

	public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException {
		// TODO Auto-generated method stub
		//args[0] = "lottonz.catchsoftware.net";
		//args[1] = "443";
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("lottonz.catchsoftware.net", 443);

		InputStream in = sslsocket.getInputStream();
		OutputStream out = sslsocket.getOutputStream();

		// Write a test byte to get a reaction :)
		out.write(1);

		while (in.available() > 0) {
			System.out.print(in.read());
		}
		System.out.println("Successfully connected");
	}

}
