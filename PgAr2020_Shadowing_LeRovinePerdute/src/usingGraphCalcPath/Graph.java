package usingGraphCalcPath;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Graph {

	private Map<Integer, Node> nodes = new HashMap<>();

	public void addNode(Node nodeA) {

		nodes.put(nodeA.getId(), nodeA);
		// last=nodeA;
	}

	public Collection<Node> getGraph() {
		return nodes.values();
	}

	public Node getSpecificNode(int id) {
		rangeCheck(id);

		return nodes.get(id);
	}

	private void rangeCheck(int id) {

		if (id > nodes.size() - 1 || id < 0)
			throw new IndexOutOfBoundsException("The id doesn't exist");

		if (nodes.isEmpty())
			throw new NullPointerException("The Collection is empty");
	}
}