package com.dsp.shortestdistance.model;


import java.util.HashSet;
import java.util.Set;

public class Graph {
	private Set<CustomNode> nodes = new HashSet<>();

	public Set<CustomNode> getNodes() {
		return nodes;
	}

	public void setNodes(Set<CustomNode> nodes) {
		this.nodes = nodes;
	}

	public void addNode(CustomNode nodeA) {
		nodes.add(nodeA);
	}
}
