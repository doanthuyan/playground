package com.axonactive.crowdsniffing.data.mockData;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString(includeFieldNames=true)
public class MockSnifferData {
	private String code;
	private ArrayList<MockDailyData> dailyVals;
	
	public MockSnifferData() {
		dailyVals = new ArrayList<MockDailyData>();
	}
	public void addDailyValue(MockDailyData data) {
		dailyVals.add(data);
	}
}
