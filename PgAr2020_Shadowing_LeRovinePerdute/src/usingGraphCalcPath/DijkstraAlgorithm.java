package usingGraphCalcPath;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * <b>Class</b> <br>
 * implementing the Dijkstra Algorithm <br>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm"> the
 *      Dijkstra Algorithm </a> <br>
 *
 */
public class DijkstraAlgorithm {
	/**
	 * <b>Method</b> <br>
	 * calculating the shortest path to the Campo Base source <br>
	 * 
	 * @param graph
	 * @param source
	 * @return a {@code Graph} providing the shortest path between the starting
	 *         {@code Node} and the arrival {@code Node}
	 */
	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {

		source.setDistance(0);

		Set<Node> settledNodes = new HashSet<>();
		Set<Node> unsettledNodes = new HashSet<>();

		unsettledNodes.add(source);

		while (unsettledNodes.size() != 0) {
			// starting with lowest in terms of distance
			Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			// checking its links to other nodes
			for (Entry<Node, Double> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {

				Node adjacentNode = adjacencyPair.getKey();
				Double edgeWeight = adjacencyPair.getValue();

				if (!settledNodes.contains(adjacentNode)) {

					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}

	/**
	 * <b>Method</b> <br>
	 * which skims the provided Set<br>
	 * in order to find the best {@code Node} in terms of distance <br>
	 * 
	 * 
	 * @param unsettledNodes
	 * @return the lowest distance among the {@code Node}
	 */
	private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
		Node lowestDistanceNode = null;
		// At the beginning the minimum distance has to be infinity
		double lowestDistance = Integer.MAX_VALUE;
		// checking the new unsettled linked node and
		// look for the minimum distance
		// among them
		for (Node node : unsettledNodes) {

			double nodeDistance = node.getDistance();

			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}

	/**
	 * <b>Method</B> <br>
	 * combining the previous {@link Node} distance to the following {@link Node}
	 * <br>
	 * 
	 * @param evaluationNode
	 * @param edgeWeight
	 * @param sourceNode
	 */
	private static void calculateMinimumDistance(Node evaluationNode, Double edgeWeight, Node sourceNode) {

		Double sourceDistance = sourceNode.getDistance();

		if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {

			evaluationNode.setDistance(sourceDistance + edgeWeight);
			List<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}
}
