package com.artcoffer.utilities.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Vertex<T> {
	
	private Set<Edge<T>> edges = new HashSet<>();
	
	private T value;
	
	public Vertex(T value){
		this.value = value;
	}
	
	public T getValue(){
		return value;
	}
	
	public Set<Edge<T>> getEdges(){
		return Collections.unmodifiableSet(this.edges);
	} 
	
	public void addEdge(Edge<T> edge){
		this.edges.add(edge);
	}
	
	@Override
	public int hashCode(){
		return this.edges.hashCode();
	}
	
	@Override
	public boolean equals(Object other){
		if((other instanceof Vertex<?>)){
			if ( ((Vertex<?>)other).value.equals(value) 
				 && ((Vertex<?>)other).edges.equals(this.edges)){
	            return true;
	        }
			return false;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return this.value.toString();
	}
	
}
