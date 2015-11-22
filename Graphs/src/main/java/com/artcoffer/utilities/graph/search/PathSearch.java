package com.artcoffer.utilities.graph.search;

import java.util.List;
import java.util.Optional;

import com.artcoffer.utilities.graph.Graph;
import com.artcoffer.utilities.graph.Vertex;

public interface PathSearch<T> {

	/**
	 * Returns a path from a given Vertex to a given vertex for a given Graph.
	 * 
	 * @param graph
	 * @param startPoint
	 * @param endPoint
	 * @return
	 */
	public Optional<Path<T>> findFirst(Graph<T> graph, Vertex<T> startPoint, Vertex<T> endPoint);
	
	/**
	 * Returns a path from a given Vertex to a given vertex for a given Graph.
	 * 
	 * @param graph
	 * @param startPoint
	 * @param endPoint
	 * @param numberOfPaths
	 * @return
	 */
	public List<Optional<Path<T>>> findPaths(Graph<T> graph, Vertex<T> startPoint, Vertex<T> endPoint, int numberOfPaths);
}
