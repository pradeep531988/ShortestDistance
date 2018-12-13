package com.dsp.shortestdistance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.dsp.shortestdistance.model.GraphResponse;
import com.dsp.shortestdistance.model.GraphResponseList;
import com.dsp.shortestdistance.service.ICRUDService;
import com.dsp.shortestdistance.service.IDistanceCalculatorService;

@RestController
public class HomeController {

	@Autowired
	private IDistanceCalculatorService distanceCalcService;

	@Autowired
	private ICRUDService crudService;
	
	@RequestMapping("/")
	public ModelAndView welcome() {
		return new ModelAndView("index", "nodesList", crudService.getAllNodes());
	}
	
	@RequestMapping("/about")
	public ModelAndView about( ModelAndView mav) {
		return new ModelAndView("about");
	}
	
	@GetMapping(value="/transport/src/{name}")
	public ResponseEntity<GraphResponseList> getAllGraphRoutes(@PathVariable String name) {
		return new ResponseEntity<GraphResponseList>(distanceCalcService.calcAllDistanceForSource(name), HttpStatus.ACCEPTED);
	}

	@GetMapping(value="/transport/src/{srcNode}/dest/{destNode}")
	public ResponseEntity<GraphResponse> getSrcToDest(@PathVariable String srcNode, @PathVariable String destNode) {
		return new ResponseEntity<GraphResponse>(distanceCalcService.calcDistanceForSourceToDestination(srcNode, destNode), HttpStatus.ACCEPTED);
	}
}
