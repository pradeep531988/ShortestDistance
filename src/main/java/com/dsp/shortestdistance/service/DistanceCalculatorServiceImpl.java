package com.dsp.shortestdistance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.shortestdistance.model.CustomNode;
import com.dsp.shortestdistance.model.Graph;
import com.dsp.shortestdistance.model.GraphResponse;
import com.dsp.shortestdistance.model.GraphResponseList;
import com.dsp.shortestdistance.model.Node;
import com.dsp.shortestdistance.model.Paths;
import com.dsp.shortestdistance.repositories.NodeRepository;
import com.dsp.shortestdistance.repositories.PathsRepository;

@Service
public class DistanceCalculatorServiceImpl implements IDistanceCalculatorService{


	@Autowired
	private NodeRepository nodeRepo;

	@Autowired
	private PathsRepository pathsRepo;

	@Override
	public GraphResponseList calcAllDistanceForSource(String sourceNodeName) {

		Graph graph = new Graph();
		CustomNode sourceNode = null;

		sourceNode = buildGraphNode(sourceNodeName, graph, sourceNode);	 	 

		//Quick Fix for updating 
		List<GraphResponse> graphResponseList = getGraphReponseList(sourceNodeName, graph, sourceNode);
		GraphResponseList response = new GraphResponseList();
		response.setGraphResponseList(graphResponseList);
		return response;
	}

	@Override
	public GraphResponse calcDistanceForSourceToDestination(String sourceNodeName, String destNodeName) {

		Graph graph = new Graph();
		CustomNode sourceNode = null;
		sourceNode = buildGraphNode(sourceNodeName, graph, sourceNode);	 	 
		//Quick Fix for updating 
		return getGraphReponse(sourceNodeName, destNodeName,graph, sourceNode);
	}


	private CustomNode buildGraphNode(String sourceNodeName, Graph graph, CustomNode sourceNode) {
		List<Node> allNodes = nodeRepo.findAll();
		Map<String, CustomNode> nodeMap = new HashMap();
		for (Node node : allNodes) {
			nodeMap.put(node.getName(), new CustomNode(node.getName()));
		}
		for( Entry<String, CustomNode> custNode : nodeMap.entrySet()) {
			String key = custNode.getKey();
			CustomNode custNodeValue = custNode.getValue();
			List<Paths> adjacentNodes = pathsRepo.findByFromNode(key);
			for (Paths path : adjacentNodes) {//Add the Path values for the Node
				custNodeValue.addDestination(nodeMap.get(path.getToNode()), path.getWeight());
			}
			if ( sourceNodeName.equals(key)) {
				sourceNode = custNodeValue;
			}

			graph.addNode(custNodeValue);
		}
		return sourceNode;
	}

	private List<GraphResponse> getGraphReponseList(String sourceNodeName, Graph graph, CustomNode sourceNode) {
		Graph processGraphRecord = calculateShortestPathFromSource(graph, sourceNode);
		List<GraphResponse> graphResponseList = new ArrayList<>();
		for (CustomNode node: processGraphRecord.getNodes()) {
			StringBuffer shortestPathRoute = new StringBuffer();
			if (node.getShortestPath().size() > 0) {
				for (CustomNode subNodes : node.getShortestPath()) {
					if (shortestPathRoute.length() == 0) {
						shortestPathRoute.append(subNodes.getName());
					} else {
						shortestPathRoute.append("---------->").append(subNodes.getName());
					}
				}
			}
			shortestPathRoute.append("---------->").append(node.getName());
			Integer distance = node.getDistance() == Integer.MAX_VALUE ? new Integer(-1) : node.getDistance();
			graphResponseList.add(new GraphResponse(sourceNodeName,node.getName(), shortestPathRoute.toString() , distance));
		}
		return graphResponseList;
	}


	private GraphResponse getGraphReponse(String sourceNodeName, String destNodeName, Graph graph, CustomNode sourceNode) {
		Graph processGraphRecord = calculateShortestPathFromSource(graph, sourceNode);
		for (CustomNode node: processGraphRecord.getNodes()) {
			StringBuffer shortestPathRoute = new StringBuffer();
			if (node.getShortestPath().size() > 0) {
				for (CustomNode subNodes : node.getShortestPath()) {
					if (shortestPathRoute.length() == 0) {
						shortestPathRoute.append(subNodes.getName());
					} else {
						shortestPathRoute.append("---------->").append(subNodes.getName());
					}
				}
			}
			shortestPathRoute.append("---------->").append(node.getName());
			Integer distance = node.getDistance() == Integer.MAX_VALUE ? new Integer(-1) : node.getDistance();
			if (node.getName().equals(destNodeName)) {
				return new GraphResponse(sourceNodeName, destNodeName, shortestPathRoute.toString() , distance);
			}
		}
		return new GraphResponse(sourceNodeName, destNodeName, "NA", -1);
	}

	private Graph calculateShortestPathFromSource(Graph graph, CustomNode startingSource) {
		startingSource.setDistance(0);

		Set<CustomNode> settledNodes = new HashSet<>();
		Set<CustomNode> unsettledNodes = new HashSet<>();

		unsettledNodes.add(startingSource);

		while (unsettledNodes.size() != 0) {
			CustomNode currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry<CustomNode,Integer> adjacencyPair: 
				currentNode.getAdjacentNodes().entrySet()) {
				CustomNode adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode)) {
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}

	private CustomNode getLowestDistanceNode(Set<CustomNode> unsettledNodes) {
		CustomNode lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (CustomNode node: unsettledNodes) {
			int nodeDistance = node.getDistance();
			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}

	private void calculateMinimumDistance(CustomNode evaluationNode,
			Integer edgeWeigh, CustomNode sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
		if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
			evaluationNode.setDistance(sourceDistance + edgeWeigh);
			LinkedList<CustomNode> shortestPath = new LinkedList<CustomNode>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}


}
