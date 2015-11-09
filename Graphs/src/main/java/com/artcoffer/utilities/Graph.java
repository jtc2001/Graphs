package com.artcoffer.utilities;

import java.util.Set;

public class Graph {
	
	private final Set<Vertex> verticies;
	
	Graph(Set<Vertex> verticies){
		this.verticies = verticies;
	}
	
	public Set<Vertex> getVerticies(){
		return verticies;
	}
	
	public void addVertex(Vertex vertex){
		this.verticies.add(vertex);
	}
	
}
