package com.axonactive.crowdsniffing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.axonactive.crowdsniffing.data.mockData.MockCrowdSniffingData;
import com.axonactive.crowdsniffing.data.mockData.MockDailyData;
import com.axonactive.crowdsniffing.data.mockData.MockSnifferData;
import com.axonactive.crowdsniffing.enity.Pollutant;
import com.axonactive.crowdsniffing.enity.Sniffer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomData {
	
	private static RandomData randomData;
	private RandomData() {}
	public static void main(String[] args) {
		
		getInstance().mockData();
		
	}
	public static RandomData getInstance() {
		if(null == randomData ) {
			randomData = new RandomData();
		}
		return randomData;
	}
	public void mockData() {
		DataWizard wizard = DataWizard.getInstance();
		ArrayList<Sniffer> snifferList = wizard.getSnifferList();
		ArrayList<Pollutant> pollutantList = wizard.getPollutantList();
		
		ObjectMapper mapper = new ObjectMapper();
		MockCrowdSniffingData mockDB = new MockCrowdSniffingData();
		snifferList.forEach(s -> {
			mockDB.addSnifferData(createSnifferData(s));
			
			
		});
		
		try {
			System.out.println(mapper.writeValueAsString(mockDB));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public MockSnifferData createSnifferData(Sniffer source) {
		MockSnifferData mockSnifferData = new MockSnifferData();
		mockSnifferData.setCode(source.getCode());
		for(int i = 0; i< Constant.MONTH_RECORD_SIZE;i++) {
			mockSnifferData.addDailyValue(createDailyValue());
		}
		return mockSnifferData;
	}
	
	public MockDailyData createDailyValue() {
		
		MockDailyData d = new MockDailyData();
		d.setPm25Vals(toString(randomNormalDailyPM25()));
		d.setPm10Vals(toString(randomNormalDailyPM10()));
		d.setHumVals(toString(randomNormalHum()));
		d.setTempVals(toString(randomNormalTemp()));
		return d;
	}
	public double[] randomNormalDailyPM25() {
		return new Random().doubles(Constant.DAILY_DATA_SIZE, Constant.PM2_5_VAL1, Constant.PM2_5_VAL3).toArray();
	}
	public double[] randomBadDailyPM25() {
		return new Random().doubles(Constant.DAILY_DATA_SIZE, Constant.PM2_5_VAL2, Constant.PM2_5_VAL3).toArray();
	}
	public double[] randomGoodDailyPM25() {
		return new Random().doubles(Constant.DAILY_DATA_SIZE, Constant.PM2_5_VAL1, Constant.PM2_5_VAL2).toArray();
	}
	
	public double[] randomNormalDailyPM10() {
		return new Random().doubles(Constant.DAILY_DATA_SIZE, Constant.PM10_VAL1, Constant.PM10_VAL3).toArray();
	}
	public double[] randomBadDailyPM10() {
		return new Random().doubles(Constant.DAILY_DATA_SIZE, Constant.PM10_VAL2, Constant.PM10_VAL3).toArray();
	}
	public double[] randomGoodDailyPM10() {
		return new Random().doubles(Constant.DAILY_DATA_SIZE, Constant.PM10_VAL1, Constant.PM10_VAL2).toArray();
	}
	
	public double[] randomNormalTemp() {
		return new Random().doubles(Constant.DAILY_DATA_SIZE, Constant.TEMP_VAL3, Constant.TEMP_VAL4).toArray();
	}
	public double[] randomNormalHum() {
		return new Random().doubles(Constant.DAILY_DATA_SIZE, Constant.HUM_VAL2, Constant.HUM_VAL3).toArray();
	}
	public  String toString(double[] dArr) {
		String resStr = "";
		
		for(int i = 0; i < dArr.length;i++) {
			resStr += String.format( "%.2f", dArr[i] ) +",";
		}
		return resStr;
	}
}
