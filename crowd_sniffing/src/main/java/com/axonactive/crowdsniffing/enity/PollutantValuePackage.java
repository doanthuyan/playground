package com.axonactive.crowdsniffing.enity;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(includeFieldNames=true)
public class PollutantValuePackage {
	private Sniffer sniffer;
	private ArrayList<PollutantValue> valueList;
	
}
