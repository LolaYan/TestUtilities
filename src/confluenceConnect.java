import org.swift.common.soap.confluence.ConfluenceSoapService;
import org.swift.common.soap.confluence.ConfluenceSoapServiceServiceLocator;
import org.swift.common.soap.confluence.RemoteServerInfo;

public class confluenceConnect {

	private final ConfluenceSoapServiceServiceLocator fConfluenceSoapServiceGetter = new ConfluenceSoapServiceServiceLocator();
    private ConfluenceSoapService fConfluenceSoapService = null;
    private String fToken = null;
    
    /**
     * Example client using the SOAP interface, get server information
     */
    public confluenceConnect(String server, String user, String pass) throws Exception { // yea, in a constructor
        try {
            String endPoint = "/rpc/soap-axis/confluenceservice-v1";
            fConfluenceSoapServiceGetter.setConfluenceserviceV2EndpointAddress(server + endPoint);
            fConfluenceSoapServiceGetter.setMaintainSession(true);
            fConfluenceSoapService = fConfluenceSoapServiceGetter.getConfluenceserviceV2();
            fToken = fConfluenceSoapService.login(user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//https://lottonz.catchsoftware.net/wiki/rpc/soap-axis/confluenceservice-v1?WSDL
		///40.126.243.210
		String server="https://lottonz.catchsoftware.net/wiki";
		String user="lyan"; 
		String pass="welcome";
		
			confluenceConnect ex = new confluenceConnect(server, user, pass);

            System.out.println("Connected ok.");
            ConfluenceSoapService service = ex.getConfluenceSOAPService();
            String token = ex.getToken();

            RemoteServerInfo info = service.getServerInfo(token);
            System.out.println("Confluence version: " + info.getMajorVersion() + "." + info.getMinorVersion());
            System.out.println("Completed.");
        
		
	}

	
	

    public String getToken() {
        return fToken;
    }

    public ConfluenceSoapService getConfluenceSOAPService() {
        return fConfluenceSoapService;
    }
    
}
