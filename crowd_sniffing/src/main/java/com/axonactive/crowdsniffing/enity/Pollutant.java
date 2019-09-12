package com.axonactive.crowdsniffing.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(includeFieldNames=true)
public class Pollutant {

	private String code, name, unit, description;
	
	@JsonIgnore
	private int id;

}
