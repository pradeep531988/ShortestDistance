package com.dsp.shortestdistance.model;

import java.io.Serializable;

public class GraphResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GraphResponse(String srcNode, String destNode, String shortestPathRoute, Integer weight) {
		super();
		this.srcNode = srcNode;
		this.destNode = destNode;
		this.shortestPathRoute = shortestPathRoute;
		this.weight = weight;
	}
	
	private String srcNode;
	private String destNode;
	private String shortestPathRoute;
	private Integer weight;
	public String getSrcNode() {
		return srcNode;
	}
	public void setSrcNode(String srcNode) {
		this.srcNode = srcNode;
	}
	public String getDestNode() {
		return destNode;
	}
	public void setDestNode(String destNode) {
		this.destNode = destNode;
	}
	public String getShortestPathRoute() {
		return shortestPathRoute;
	}
	public void setShortestPathRoute(String shortestPathRoute) {
		this.shortestPathRoute = shortestPathRoute;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
