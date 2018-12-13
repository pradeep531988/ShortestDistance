package com.dsp.shortestdistance.model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public final class CustomNode {

	private String name;

	private List<CustomNode> shortestPath = new LinkedList<>();

	private Integer distance = Integer.MAX_VALUE;

	Map<CustomNode, Integer> adjacentNodes = new HashMap<>();

	/* Constructor which accepts name */
	public CustomNode(String name) {
		this.name = name;
	}

	public void addDestination(CustomNode destination, int distance) {
		adjacentNodes.put(destination, distance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CustomNode> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<CustomNode> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Map<CustomNode, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<CustomNode, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

}
