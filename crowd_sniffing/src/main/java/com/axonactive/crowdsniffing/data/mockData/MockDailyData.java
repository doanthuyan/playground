package com.axonactive.crowdsniffing.data.mockData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(includeFieldNames=true)
public class MockDailyData {

	private String pm25Vals, pm10Vals,humVals, tempVals;
}
