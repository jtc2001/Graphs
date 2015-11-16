package com.artcoffer.utilities.graph.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.artcoffer.utilities.graph.Vertex;

/**
 * Represents a LinkedList of vertices which may or may not be ordered. 
 *
 * @param <T>
 */
public class Path<T> {
	
	private final LinkedList<Vertex<T>> path = new LinkedList<>();
	
	public void addVertex(Vertex<T> nextVertex){
		this.path.offer(nextVertex);
	}
	
	public List<Vertex<T>> getPath(){
		return new LinkedList<>(this.path);
	}
	
	public List<T> getValues(){
		return path.stream().map((v)->(v.getValue())).collect(Collectors.toList());
	}

}
