package com.axonactive.crowdsniffing;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import com.axonactive.crowdsniffing.data.mockData.MockCrowdSniffingData;
import com.axonactive.crowdsniffing.enity.Pollutant;
import com.axonactive.crowdsniffing.enity.PollutantValue;
import com.axonactive.crowdsniffing.enity.PollutantValuePackage;
import com.axonactive.crowdsniffing.enity.Sniffer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class DataWizard {
	private static final String INDEX_RESOURCE = "data/index.html";
	private static final String SNIFFER_RESOURCE = "data/sniffers.json";
	private static final String POLLUTANT_RESOURCE = "data/pollutants.json";
	
	private static final String MOCK_DATA_RESOURCE = "data/mockData/mockData.json";
	
	
	private static final String  PM_SENSOR = "Nova";
	
	
	private static DataWizard wizard;
	
	private ArrayList<Sniffer> snifferList;
	private ArrayList<Pollutant> pollutantList;
	private MockCrowdSniffingData mockCrowdSniffingData;
	
	private ObjectMapper mapper;
	
	private DataWizard() {
		snifferList = new ArrayList<Sniffer>();
		pollutantList = new ArrayList<Pollutant>();
		mapper = new ObjectMapper();
		loadSampleData();
	}
	
	public ArrayList<Sniffer> getSnifferList() {
		return snifferList;
	}

	public ArrayList<Pollutant> getPollutantList() {
		return pollutantList;
	}

	public static DataWizard getInstance() {
		if(null == wizard) {
			wizard = new DataWizard();
		}
		return wizard;
	}
	private void loadSampleData() {
		try {
			InputStream inputStream ;
			Reader reader;
			
			//load Sniffers
			inputStream = getClass().getResourceAsStream(SNIFFER_RESOURCE); 
			//URL url = getClass().getResource(SNIFFER_RESOURCE); 
			//System.out.println(url.toString());
			//inputStream =  url.openStream();
			reader = new InputStreamReader(inputStream, "UTF-8");
			snifferList = mapper.readValue(reader, new TypeReference<ArrayList<Sniffer>>() {});
			
			//load Pollutants
			inputStream = getClass().getResourceAsStream(POLLUTANT_RESOURCE); 
			reader = new InputStreamReader(inputStream, "UTF-8");
			pollutantList = mapper.readValue(reader, new TypeReference<ArrayList<Pollutant>>() {});
			
			//pollutant values
			inputStream = getClass().getResourceAsStream(MOCK_DATA_RESOURCE); 
			reader = new InputStreamReader(inputStream, "UTF-8");
			mockCrowdSniffingData = mapper.readValue(reader, new TypeReference<MockCrowdSniffingData>() {});
			
			

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private Sniffer findSnifferByCode(String snifferCode){
		Optional<Sniffer> obj = snifferList.stream().filter(s -> s.getCode().equals(snifferCode)).findFirst();
		return obj.isPresent()?null:obj.get();
	}
	public void printSampleData() {
		snifferList.forEach((n) -> System.out.println(n.toString()));
		pollutantList.forEach((n) -> System.out.println(n.toString()));
	}
	public String generateDailyData(String snifferCode) {
		Sniffer s = findSnifferByCode(snifferCode);
		if(null == s) {
			return "Cannot find Sniffer "+ snifferCode;
		}else {
			PollutantValuePackage respPackage = new PollutantValuePackage();
			respPackage.setSniffer(s);
			if(s.getStatus().equalsIgnoreCase("OPERATING")) {
				respPackage.setValueList(createAverageDailyPM25());
			}
			try {
				return mapper.writeValueAsString(respPackage);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				return "Internal Server error";
			}
		}
	}
	private ArrayList<PollutantValue> createAverageDailyPM25() {
		Calendar c = Calendar.getInstance();
		Date current = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 1);
		Date start = c.getTime();
		
		double[] valArray = new Random().doubles(Constant.DAILY_DATA_SIZE, Constant.PM2_5_VAL1, Constant.PM2_5_VAL3).toArray();
		int count = 0;
		ArrayList<PollutantValue> valList = new ArrayList<PollutantValue>();
		while(start.before(current)) {
			valList.add(PollutantValue.pollutantValueBuilder().data(valArray[count]).dateTime(start).sensorStr(PM_SENSOR).pvCode("PM2.5").build());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY)+1);
			start=c.getTime();
			count ++;
		}
		return valList;
	}
	public InputStream generateIndexStream(){
		return getClass().getResourceAsStream(INDEX_RESOURCE); 
	}
	public InputStream generateSnifferStream(){
		return getClass().getResourceAsStream(SNIFFER_RESOURCE); 
	}
	public InputStream generatePollutantStream(){
		return getClass().getResourceAsStream(POLLUTANT_RESOURCE); 
	}
}
