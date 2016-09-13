package oauth;


import java.io.IOException; 
import java.io.InputStream; 
import java.util.Map; 
import net.oauth.OAuth; 
import net.oauth.OAuthMessage; 
import net.oauth.OAuthProblemException; 
import net.oauth.http.HttpMessage; 
import net.oauth.http.HttpResponseMessage; 
 
/**
 * An HTTP response, encapsulated as an OAuthMessage. 
 *  
 * @author John Kristian 
 * @hide 
 */ 
final class OAuthResponseMessage extends OAuthMessage 
{ 
    OAuthResponseMessage(HttpResponseMessage http) throws IOException 
    { 
        super(http.method, http.url.toExternalForm(), null); 
        this.http = http; 
        getHeaders().addAll(http.headers); 
        for (Map.Entry<String, String> header : http.headers) { 
            if ("WWW-Authenticate".equalsIgnoreCase(header.getKey())) { 
                for (OAuth.Parameter parameter : decodeAuthorization(header.getValue())) { 
                    if (!"realm".equalsIgnoreCase(parameter.getKey())) { 
                        addParameter(parameter); 
                    } 
                } 
            } 
        } 
    } 
 
    private final HttpMessage http; 
 
    @Override 
    public InputStream getBodyAsStream() throws IOException 
    { 
        return http.getBody(); 
    } 
 
    @Override 
    public String getBodyEncoding() 
    { 
        return http.getContentCharset(); 
    } 
 
    @Override 
    protected void completeParameters() throws IOException 
    { 
        super.completeParameters(); 
        String body = readBodyAsString(); 
        if (body != null) { 
            addParameters(OAuth.decodeForm(body.trim())); 
        } 
    } 
 
    @Override 
    protected void dump(Map<String, Object> into) throws IOException 
    { 
        super.dump(into); 
        http.dump(into); 
    } 
 
    @Override 
    public void requireParameters(String... names) throws OAuthProblemException, IOException { 
        try { 
            super.requireParameters(names); 
        } catch (OAuthProblemException problem) { 
            problem.getParameters().putAll(getDump()); 
            throw problem; 
        } 
    } 
 
}
