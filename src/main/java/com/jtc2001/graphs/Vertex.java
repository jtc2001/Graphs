package com.jtc2001.graphs;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
	
	private Set<Edge> edges = new HashSet<>();

	public void addEdge(Edge edge){
		this.edges.add(edge);
	}
	
	@Override
	public int hashCode(){
		return this.edges.hashCode();
	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof Vertex)){
			return false;
		}
		Vertex otherVertex = (Vertex) other;
		return this.edges.equals(otherVertex.edges);
	}
	
}
