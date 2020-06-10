package path;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

	private int id;

	private List<Node> shortestPath = new LinkedList<>();

	private Double distance = (double) Integer.MAX_VALUE;

	private Map<Node, Double> adjacentNodes = new HashMap<>();

	public void addDestination(Node destination, Double distance) {
		adjacentNodes.put(destination, distance);
	}

	public Node(int name) {
		this.id = name;
	}

	public int getId() {

		return id;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDistance() {
		return distance;
	}

	public List<Node> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}

	/**
	 * @return the adjacentNodes
	 */
	public Map<Node, Double> getAdjacentNodes() {
		return adjacentNodes;
	}

}
