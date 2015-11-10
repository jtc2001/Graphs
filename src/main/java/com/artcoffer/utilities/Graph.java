package com.artcoffer.utilities;

import java.util.HashSet;
import java.util.Set;

public class Graph<T> {
	
	private final Set<Vertex<T>> verticies;
	
	private final Set<Edge<T>> edges;
	
	public Graph(){
		verticies = new HashSet<>();
		edges = new HashSet<>();
	}
	
	public Graph(Set<Vertex<T>> verticies, Set<Edge<T>> edges){
		this.verticies = verticies;
		this.edges = edges;
	}
	
	public Set<Vertex<T>> getVerticies(){
		return verticies;
	}
	
	public Graph<T> addVertex(Vertex<T> vertex){
		this.verticies.add(vertex);
		return this;
	}
	
	public Graph<T> addEdge(Edge<T> edge){
		this.edges.add(edge);
		return this;
	}
	
}
