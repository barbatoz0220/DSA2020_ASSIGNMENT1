/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import list.DLinkedList;

import java.util.*;
//import java.util.Stack;

public class DGraphModel<T> extends AbstractGraph<T>{
    public DGraphModel(){
        super();
    }
    @Override
    public void connect(T from, T to) throws VertexNotFoundException{
        VertexNode<T> nodeF = getVertexNode(from);
        VertexNode<T> nodeT = getVertexNode(to);
        if(nodeF == null) throw new VertexNotFoundException(nodeF);
        if(nodeT == null) throw new VertexNotFoundException(nodeT);
        
        nodeF.connect(nodeT);
    }
    @Override
    public void connect(T from, T to, float weight) throws VertexNotFoundException{
        VertexNode<T> nodeF = getVertexNode(from);
        VertexNode<T> nodeT = getVertexNode(to);
        if(nodeF == null) throw new VertexNotFoundException(from);
        if(nodeT == null) throw new VertexNotFoundException(to);
        
        nodeF.connect(nodeT, weight);
    }
    @Override
    public void disconnect(T from, T to) throws VertexNotFoundException, EdgeNotFoundException{
        VertexNode<T> nodeF = getVertexNode(from);
        VertexNode<T> nodeT = getVertexNode(to);
        if(nodeF == null) throw new VertexNotFoundException(from);
        if(nodeT == null) throw new VertexNotFoundException(to);
        
        Edge<T> edge = nodeF.getEdge(nodeT);
        if(edge == null){
            String msg = String.format("E(from:%s, to:%s)", from, to);
            throw new EdgeNotFoundException(msg);
        }
        nodeF.removeTo(nodeT);
    }

    @Override
    public void remove(T vertex) throws VertexNotFoundException{
        VertexNode<T> nodeA = getVertexNode(vertex);
        if(nodeA == null) throw new VertexNotFoundException(vertex);
        //disconnect all
        for(VertexNode<T> nodeB: this.nodeList){
            Edge<T> edge;
            edge = nodeB.getEdge(nodeA);
            if(edge != null) nodeB.removeTo(nodeA);
            edge = nodeA.getEdge(nodeB);
            if(edge != null) nodeA.removeTo(nodeB);
        }
        //remove
        this.nodeList.remove(nodeA);
    }
}

class Stack<T>{
    private List<T> list;
    public Stack(){
        this.list = new DLinkedList<>();
    }
    public void push(T item){
        this.list.add(0, item);
    }
    public T pop(){
        T item = this.list.get(0);
        this.list.remove(0);
        return item;
    }
    public T peek(){
        T item = this.list.get(0);
        return item;
    }
    public boolean remove(T item){
        return this.list.remove(item);
    }
    public boolean contains(T item){
        return this.list.contains(item);
    }
    public boolean isEmpty(){
        return this.list.isEmpty();
    }
    public int size(){
        return this.list.size();
    }
}

class Queue<T>{
    private List<T> list;
    public Queue(){
        this.list = new DLinkedList<>();
    }
    public void push(T item){
        this.list.add(item);
    }
    public T pop(){
        T item = this.list.get(0);
        this.list.remove(0);
        return item;
    }
    public T peek(){
        T item = this.list.get(0);
        return item;
    }
    public boolean contains(T item){
        return this.list.contains(item);
    }
    public boolean isEmpty(){
        return this.list.isEmpty();
    }
    public int size(){
        return this.list.size();
    }
}
//https://www.quora.com/Can-topological-sorting-be-done-using-BFS

class TopoSorter<T>{
    public static final int DFS = 0;
    public static final int BFS = 1;
    protected DGraphModel<T> graph;
    public TopoSorter(DGraphModel<T> graph){
        this.graph = graph;
    }
    public List<T> sort(){
        return sort(DFS);
    }
    public List<T> sort(int mode){
        if(mode == DFS) return dfsSort();
        else return bfsSort();
    }
    public List<T> bfsSort(){
        List<T> topoOrder = new LinkedList<>();
        HashMap<T, Integer> indegreeMap = vertex2inDegree();
        List<T> list = listOfZeroInDegrees();
        
        Queue<T> queue = new Queue<>();
        for(T item: list) queue.push(item);
        
        /**
		 * Your Code Here
		 */

        return topoOrder;
    }
    public List<T> dfsSort(){
        List<T> topoOrder = new LinkedList<>();
        HashMap<T, Integer> outdegreeMap = vertex2outDegree();
        List<T> list = listOfZeroInDegrees();

        Stack<T> stack = new Stack<>();
        for(T item: list) stack.push(item);

        while(!stack.isEmpty()){
            T vertex = stack.peek();
            int outDegree = outdegreeMap.get(vertex);
            if(outDegree == 0){
                stack.pop();
                topoOrder.add(0, vertex);
            }
            else{
                List<T> neighbors = this.graph.getOutwardEdges(vertex);
                for(T v: neighbors){
                    if(stack.contains(v)){
                        stack.remove(v);
                        stack.push(v);
                    }
                    if(!stack.contains(v) && !topoOrder.contains(v) )
                        stack.push(v);
                    outdegreeMap.put(vertex, outDegree - 1);
                }
            }
        } //while: stack not empty
        return topoOrder;
    }
    
    private HashMap<T, Integer> vertex2inDegree(){
        HashMap<T, Integer> map = new HashMap<>();
        Iterator<T> vertexIt = this.graph.iterator();
        while(vertexIt.hasNext()){
            T vertex = vertexIt.next();
            int inDegree = this.graph.inDegree(vertex);
            map.put(vertex, inDegree);
        }
        return map;
    }
    private HashMap<T, Integer> vertex2outDegree(){
        HashMap<T, Integer> map = new HashMap<>();
        Iterator<T> vertexIt = this.graph.iterator();
        while(vertexIt.hasNext()){
            T vertex = vertexIt.next();
            int outDegree = this.graph.outDegree(vertex);
            map.put(vertex, outDegree);
        }
        return map;
    }
    private List<T> listOfZeroInDegrees(){
        List<T> list = new LinkedList<>();
        Iterator<T> vertexIt = this.graph.iterator();
        while(vertexIt.hasNext()){
            T vertex = vertexIt.next();
            int inDegree = this.graph.inDegree(vertex);
            if(inDegree == 0) list.add(vertex);
        }
        return list;
    }
}

class DGraphAlgorithm<T> implements IFinder<T>{
    DGraphModel graph;
    public DGraphAlgorithm(DGraphModel<T> graph){
        this.graph = graph;
    }
    @Override
    public List<Path> dijkstra(IGraph<T> graph, T start) {
        List<Node> explored = new LinkedList<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>(100, new NodeComparator());
        frontier.add(new Node(null, start, 0));
        while(!frontier.isEmpty()){
            Node node = frontier.poll();
            explored.add(node);
            List<T> children = graph.getOutwardEdges(node.vertex);
            Iterator<T> childrenIt = children.iterator();
            while(childrenIt.hasNext()){
                T child = childrenIt.next();
                float stepCost = graph.weight(node.vertex, child);
                Node childNode = new Node(node, child, node.pathCost + stepCost);
                if(!explored.contains(childNode)){
                    boolean inFrontier = false;
                    Iterator<Node> frontierIt = frontier.iterator();
                    while(frontierIt.hasNext()){
                        Node frontierNode = frontierIt.next();
                        float oldPathCost = frontierNode.pathCost;
                        float newPathCost = childNode.pathCost;
                        if(frontierNode.vertex.equals(child)){
                            inFrontier = true;
                            if(newPathCost < oldPathCost){
                                frontierIt.remove();
                                frontier.add(childNode);
                                break;
                            }
                        }
                    }
                    if(!inFrontier) frontier.add(childNode);
                }//if
            }//while: process each child
        }
        return constructPath(explored);
    }
    private List<Path> constructPath(List<Node> explored){
        List<Path> list = new LinkedList<>();

        Iterator<Node> it = explored.iterator();
        while(it.hasNext()){
            Path<T> path = new Path();
            java.util.Stack<Node> stack = new java.util.Stack<>();

            Node node = it.next();
            path.cost = node.pathCost;
            while(node != null){
                stack.push(node);
                node = node.parent;
            }
            while(!stack.empty()){
                Node item = stack.pop();
                T vertex = item.vertex;
                path.add(vertex);
            }
            list.add(path);
        }
        return list;
    }
    
    //
    class Node{
        Node parent;
        T vertex;
        float pathCost;
        public Node(Node parent, T vertex, float pathCost){
            this.parent = parent;
            this.vertex = vertex;
            this.pathCost = pathCost;
        }
        @Override
        public boolean equals(Object node){
            return this.vertex.equals(((Node)node).vertex);
        }

        @Override
        public int hashCode() {
            int hash = Objects.hashCode(this.vertex);
            return hash;
        }
        @Override
        public String toString(){
            return this.vertex.toString();
        }
    }
    class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            float diff = o1.pathCost - o2.pathCost;
            if(diff < 0) return -1;
            else if(diff > 0) return +1;
            else return 0;
        }

    }
}
