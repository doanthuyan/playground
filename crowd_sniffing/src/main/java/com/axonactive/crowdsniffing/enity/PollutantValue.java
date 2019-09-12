package com.axonactive.crowdsniffing.enity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @NoArgsConstructor @ToString(includeFieldNames=true)
public class PollutantValue {
	
	private String code;
	private String sensor;
	private int snifferId;
	private double value;
	private Date measureAt;
	@JsonIgnore
	private int id;
	@Builder(builderMethodName = "pollutantValueBuilder")
	public PollutantValue(double data,Date dateTime,String pvCode,String sensorStr) {
		value = data;
		measureAt = dateTime;
		code = pvCode;
		sensor = sensorStr;
	}
}
