package path;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import city.City;

public class Graph {

	private Set<Node> nodes = new HashSet<>();

	private Node root;

	
	public void addNode(Node nodeA) {

		if (nodes.isEmpty())
			root = nodeA;

		nodes.add(nodeA);
	//	last=nodeA;
	}

	public Collection<Node> getGraph() {
		return nodes;
	}

	public Node getRoot() {
		return root;
	}

	public Node getSpecificNode(int id) {
		rangeCheck(id);

		Iterator<Node> itr = nodes.iterator();

		while (itr.hasNext()) {

			Node _this = itr.next();

			if (_this.getId() == id)
				return _this;
		}
		return null;
	}
	
	private void rangeCheck(int id) {
		
		if (id > nodes.size() - 1 || id < 0)
			throw new IndexOutOfBoundsException("The id doesn't exist");

		if (nodes.isEmpty())
			throw new NullPointerException("The Collection is empty");
	}
}