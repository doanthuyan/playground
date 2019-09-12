package com.axonactive.crowdsniffing.data.mockData;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter  @ToString(includeFieldNames=true)
public class MockCrowdSniffingData {
	@JsonIgnore
	private ArrayList<Date> dateRange;
	
	private ArrayList<MockSnifferData> snifferVals;
	
	public MockCrowdSniffingData() {
		snifferVals = new ArrayList<MockSnifferData>();
	}
	public void addSnifferData(MockSnifferData data) {
		snifferVals.add(data);
	}
}
