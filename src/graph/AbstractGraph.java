package graph;

import list.DLinkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractGraph<T> implements IGraph<T> {
	// Create a list of nodes
	public List<VertexNode<T>> nodeList;
	// Constructor
	public AbstractGraph() {
		nodeList = new DLinkedList<>();
	}
	/// Utilities
	protected VertexNode<T> getVertexNode(T vertex) {
		ListIterator<VertexNode<T>> it = nodeList.listIterator();
		while(it.hasNext()){
			VertexNode<T> node = it.next();
			if(node.vertex.equals(vertex)) return node;
		}
		return null;
	}

	///
	@Override
	public void add(T vertex) {
		this.nodeList.add(new VertexNode(vertex));
	}

	@Override
	public abstract void remove(T vertex) throws VertexNotFoundException;

	@Override
	public boolean contains(T vertex) {
		VertexNode<T> node = getVertexNode(vertex);
		return node != null;
	}

	@Override
	public void connect(T from, T to) throws VertexNotFoundException {
		connect(from, to, 0);
	}

	@Override
	public abstract void connect(T from, T to, float weight) throws VertexNotFoundException;

	@Override
	public abstract void disconnect(T from, T to) throws VertexNotFoundException, EdgeNotFoundException;

	@Override
	public float weight(T from, T to) throws VertexNotFoundException, EdgeNotFoundException {
		VertexNode<T> nodeF = getVertexNode(from);
		VertexNode<T> nodeT = getVertexNode(to);
		if (nodeF == null)
			throw new VertexNotFoundException(from);
		if (nodeT == null)
			throw new VertexNotFoundException(to);

		Edge<T> edge = nodeF.getEdge(nodeT);
		if (edge == null) {
			String msg = String.format("E(from:%s, to:%s)", from, to);
			throw new EdgeNotFoundException(msg);
		}
		return edge.weight;
	}

	@Override
	public List getOutwardEdges(T from) throws VertexNotFoundException {
		VertexNode<T> node = getVertexNode(from);
		if (node == null)
			throw new VertexNotFoundException(from);
		return node.getOutwardEdges();
	}

	@Override
	public List getInwardEdges(T to) throws VertexNotFoundException {
		List<T> list = new DLinkedList<>();

		Iterator<VertexNode<T>> nodeIt = nodeList.iterator();
		while (nodeIt.hasNext()) {
			VertexNode<T> node = nodeIt.next();
			Iterator<Edge<T>> edgeIt = node.adList.iterator();
			while (edgeIt.hasNext()) {
				Edge<T> edge = edgeIt.next();
				if (edge.to.vertex.equals(to))
					list.add(edge.from.vertex);
			}
		}
		return list;
	}

	@Override
	public Iterator<T> iterator() {
		return new GraphIterator<T>(this, this.nodeList.iterator());
	}

	@Override
	public int size() {
		return this.nodeList.size();
	}

	@Override
	public int inDegree(T vertex) throws VertexNotFoundException {
		VertexNode node = getVertexNode(vertex);
		if (node == null)
			throw new VertexNotFoundException(vertex);
		return node.inDegree();
	}

	@Override
	public int outDegree(T vertex) throws VertexNotFoundException {
		VertexNode<T> node = getVertexNode(vertex);
		if (node == null)
			throw new VertexNotFoundException(vertex);
		return node.outDegree();
	}

	@Override
	public void println() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		String desc = String.format("%s\n", new String(new char[80]).replace('\0', '='));
		desc += "Vertices:\n";
		Iterator<VertexNode<T>> nodeIt = this.nodeList.iterator();
		while (nodeIt.hasNext()) {
			VertexNode<T> node = nodeIt.next();
			desc += "  " + node.toString() + "\n";
		}
		desc += String.format("%s\n", new String(new char[40]).replace('\0', '-'));
		desc += "Edges:\n";

		nodeIt = this.nodeList.iterator();
		while (nodeIt.hasNext()) {
			VertexNode<T> node = nodeIt.next();
			Iterator<Edge<T>> edgeIt = node.adList.iterator();
			while (edgeIt.hasNext()) {
				Edge<T> edge = edgeIt.next();
				String line = String.format("E(%s,%s, %6.2f)", node.vertex, edge.to.vertex, edge.weight);
				desc += "  " + line + "\n";
			}
		}
		desc += String.format("%s\n", new String(new char[80]).replace('\0', '='));
		return desc;
	}

}

/////////////////////////////////////////////////////////////////////////
///// Utility classes
/////////////////////////////////////////////////////////////////////////
class VertexNode<T> {
	T vertex;
	int inDegree, outDegree;
	List<Edge<T>> adList;

	public VertexNode(T data) {
		this.vertex = data;
		this.inDegree = this.outDegree = 0;
		// adjacency list, this list has a set of edges to other vertices that this one connects
		this.adList = new DLinkedList<>();
	}

	public void connect(VertexNode<T> to) {
		connect(to, 0);
	}

	public void connect(VertexNode<T> to, float weight) {
		Edge<T> edge = getEdge(to);
		if(edge == null){
			edge = new Edge(this, to, weight);
			this.adList.add(edge);
			edge.from.outDegree += 1;
			edge.to.inDegree += 1;
		} else {
			edge.weight = weight;
		}
	}

	// This function returns all the edges pointing outwards values that the adjacency list contains
	public List<T> getOutwardEdges() {
		List<T> vertexList = new DLinkedList<>();
		Iterator<Edge<T>> iterator = this.adList.iterator();
		while (iterator.hasNext()) {
			T to = iterator.next().to.vertex;
			vertexList.add(to);
		}
		return vertexList;
	}

	// This function return the specific edge that links from this vertex to the parameter vertex
	Edge<T> getEdge(VertexNode<T> to){
		Iterator<Edge<T>> adListIterator = this.adList.iterator();
		while(adListIterator.hasNext()){
			Edge<T> curEdge = adListIterator.next();
			if(curEdge.equals(new Edge<>(this, to)))
				return curEdge;
		}
		return null;
	}

	public void removeTo(VertexNode<T> to) {
		ListIterator<Edge<T>> adListIterator = adList.listIterator();
		while(adListIterator.hasNext()) {
			Edge<T> curEdge = adListIterator.next();
			if (curEdge.to.vertex.equals(to.vertex)) {
				adListIterator.remove();
				curEdge.from.outDegree--;
				curEdge.to.inDegree--;
				break;
			}
		}
	}

	// Return the number of edges pointed towards this vertex
	public int inDegree() {
		return this.inDegree;
	}

	// Return the number of edges pointed outwards this vertex
	public int outDegree() {
		return this.outDegree;
	}

	public String toString() {
		String desc = String.format("V(%s, in:%4d, out:%4d)", this.vertex, this.inDegree, this.outDegree);
		return desc;
	}
}

class Edge<T> {
	VertexNode<T> from;
	VertexNode<T> to;
	float weight;

	// Edge with weight
	public Edge(VertexNode<T> from, VertexNode<T> to, float weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	// Edge without weight
	public Edge(VertexNode<T> from, VertexNode<T> to) {
		this.from = from;
		this.to = to;
		this.weight = 0;
	}

	// Check to see if 2 edges are equal
	public boolean equals(Object newEdge) {
		Edge<T> edge = (Edge<T>) newEdge;
		boolean fromEquality = this.from.vertex.equals(edge.from.vertex);
		boolean toEquality = this.to.vertex.equals(edge.to.vertex);
		return fromEquality && toEquality;
	}

	// Print out the nodes that this edge connects
	public String toString() {
		String desc = String.format("E(from:%s, to:%s)", this.from, this.to);
		return desc;
	}
}

// Graph Iterator Class
class GraphIterator<T> implements Iterator<T> {
	IGraph<T> graph;
	Iterator<VertexNode<T>> nodeIt;
	VertexNode<T> node;
	boolean afterMove = false;

	GraphIterator(IGraph<T> graph, Iterator<VertexNode<T>> nodeIt) {
		this.graph = graph;
		this.nodeIt = nodeIt;
		node = null;
	}

	@Override
	public boolean hasNext() {
		return this.nodeIt.hasNext();
	}

	@Override
	public T next() {
		this.node = this.nodeIt.next();
		afterMove = true;
		return node.vertex;
	}

	@Override
	public void remove() {
		if (afterMove) {
			this.graph.remove(node.vertex);
			afterMove = false;
		}
	}
}// End of GraphIterator
