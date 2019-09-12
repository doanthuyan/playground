package com.axonactive.crowdsniffing;
import java.net.HttpURLConnection;

import io.javalin.Javalin;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
public class MockServer {
	private static DataWizard wizard;
	private static  MockServer server;
	private  MockServer() {
		 
	}
	public static MockServer getInstance() {
		if(null == server) {
			server = new MockServer();
		}
		return server;
	}
	public static void main(String[] args) {
		
        Javalin app = Javalin.create()
        		//.enableStaticFiles("/public")
        		
        		.start(7000);
        
        //addBooks();
        app.get("/", ctx -> ctx.result(wizard.generateIndexStream()).status(HttpURLConnection.HTTP_OK));
        app.get("/sniffers", ctx -> ctx.result(wizard.generateSnifferStream()).status(HttpURLConnection.HTTP_OK));
        app.get("/pollutants", ctx -> ctx.result(wizard.generatePollutantStream()).status(HttpURLConnection.HTTP_OK));
        app.get("/sniffer/:code/pollutantvalues/daily", ctx -> ctx.result(wizard.generateDailyData(ctx.pathParam("code"))).status(HttpURLConnection.HTTP_OK));
        MockServer mocServer = MockServer.getInstance();
        mocServer.loadMockData();
        
        
    }
    public void loadMockData() {
    	wizard = DataWizard.getInstance();
		wizard.printSampleData();
		//System.out.println(wizard.generateDailyData("LL0-UKV-NHT-GHY"));
    }
}
	

