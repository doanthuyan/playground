package crowd_sniffing;

import com.axonactive.crowdsniffing.MockServer;

public class TestDataWizard {
	public static void main(String[] args) {
		MockServer mocServer = MockServer.getInstance();
        mocServer.loadMockData();
        
        
	}
}
