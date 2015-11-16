package com.artcoffer.utilities.graph.search;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

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
		Optional<Path<T>> path = findVertex(graph, startPoint, endPoint);
		
		if(path.isPresent()){
			Path<T> fullPath = path.get();
			LinkedList<Vertex<T>> pathList = new LinkedList<>((LinkedList<Vertex<T>>) fullPath.getPath());
			
			Vertex<T> target = pathList.getLast();
			Path<T> actualPath = new Path<>();
			Stack<Vertex<T>> actualPathStack = new Stack<>();
			
			actualPathStack.add(target);
			//If there is no parent, we are already there return.
			if(target.getParentVertex() == null){
				actualPath.addVertex(target);
				return Optional.of(actualPath);
			}
			
			Vertex<T> nextParent = target.getParentVertex();
			while(nextParent.getParentVertex() != null){
				actualPathStack.add(nextParent);
				nextParent = nextParent.getParentVertex();
			}
			
			//Finally add the starting node in and return
			actualPathStack.add(nextParent);
			Collections.reverse(actualPathStack);
			actualPathStack.stream().forEach(new Consumer<Vertex<T>>(){
				@Override
				public void accept(Vertex<T> t) {
					actualPath.addVertex(t);
				}});
			return Optional.of(actualPath);
		}
		
		return Optional.empty();
	}

	private Optional<Path<T>> findVertex(Graph<T> graph, Vertex<T> startPoint,
			Vertex<T> endPoint) {
		LinkedList<Vertex<T>> visiting = new LinkedList<>();
		Path<T> path = new Path<>();
		Set<Vertex<T>> visited = new HashSet<>(graph.getVerticies().size());
		
		path.addVertex(startPoint);
		
		//Start with all known edges of starting point
		Set<Edge<T>> knownEdges = startPoint.getEdges();
		
		for(Edge<T> knownEdge : knownEdges){
			knownEdge.getToVertex().setParentVertex(startPoint);
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
						otherEdge.getToVertex().setParentVertex(current);
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
