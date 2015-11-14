package com.artcoffer.utilities.graph;

public class Edge<T> {
	
	private Vertex<T> fromVertex;
	
	private Vertex<T> toVertex;
	
	public Edge(Vertex<T> fromVertex, Vertex<T> toVertex){
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
	}
	
	public Vertex<T> getFromVertex(){
		return this.fromVertex;
	}
	
	public Vertex<T> getToVertex(){
		return this.toVertex;
	}
	
	@Override
	public String toString(){
		return "Edge[" + this.fromVertex.toString() + "->" + this.toVertex.toString() + "]";
	}
	
}
