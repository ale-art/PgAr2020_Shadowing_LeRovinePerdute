package path;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graph {

	private Set<Node> nodes = new HashSet<>();

	public void addNode(Node nodeA) {

		nodes.add(nodeA);
		// last=nodeA;
	}

	public Collection<Node> getGraph() {
		return nodes;
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

	public Iterator<Node> iterator() {
		return nodes.iterator();
	}

	private void rangeCheck(int id) {

		if (id > nodes.size() - 1 || id < 0)
			throw new IndexOutOfBoundsException("The id doesn't exist");

		if (nodes.isEmpty())
			throw new NullPointerException("The Collection is empty");
	}
}