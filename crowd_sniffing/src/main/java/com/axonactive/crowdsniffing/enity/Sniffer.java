package com.axonactive.crowdsniffing.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @NoArgsConstructor @ToString(includeFieldNames=true)
public class Sniffer {
	private String name, code, macAddress, status;
	@JsonProperty("gpsLocation")
	private GPSLocation gpsLocation;
	
	private int id;
	
	

}
