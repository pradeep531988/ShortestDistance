package com.dsp.shortestdistance.service;

import com.dsp.shortestdistance.model.GraphResponse;
import com.dsp.shortestdistance.model.GraphResponseList;

public interface IDistanceCalculatorService {

	GraphResponseList calcAllDistanceForSource(String sourceNodeName);
	
	GraphResponse calcDistanceForSourceToDestination(String sourceNodeName, String destNodeName);

}
