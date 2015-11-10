package com.artcoffer.utilities.graph.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

import com.artcoffer.utilities.graph.Edge;
import com.artcoffer.utilities.graph.Graph;
import com.artcoffer.utilities.graph.Vertex;

/**
 * Simple implementation of a BreadthFirstSearch returning all known paths to given Vertex.
 * @param <T>
 */
public class BreadthFirstVertexSearch<T> implements PathSearch<T>{

	@Override
	public Optional<Path<T>> findFirst(Graph<T> graph, Vertex<T> startPoint, Vertex<T> endPoint) {
		LinkedList<Vertex<T>> visiting = new LinkedList<>();
		Path<T> path = new Path<>();
		Set<Vertex<T>> visited = new HashSet<>(graph.getVerticies().size());
		
		path.addVertex(startPoint);
		
		//Start with all known edges of starting point
		Set<Edge<T>> knownEdges = startPoint.getEdges();
		
		for(Edge<T> knownEdge : knownEdges){
			visiting.offer(knownEdge.getToVertex());
		}
		
		while(!visiting.isEmpty()){
			Vertex<T> current = visiting.poll();
			
			if(!visited.contains(current)){
				//keep track of visited verticies
				visited.add(current);
				path.addVertex(current);
				
				for(Edge<T> otherEdge : current.getEdges()){
					if(!visited.contains(otherEdge.getToVertex())){
						visiting.offer(otherEdge.getToVertex());
					}
				}
				
				if(current.equals(endPoint)){
					return Optional.of(path);
				}
			}
		}
		
		return Optional.empty();
	}
}
