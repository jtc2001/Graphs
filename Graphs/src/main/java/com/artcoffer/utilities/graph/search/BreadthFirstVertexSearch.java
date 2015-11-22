package com.artcoffer.utilities.graph.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.artcoffer.utilities.graph.Edge;
import com.artcoffer.utilities.graph.Graph;
import com.artcoffer.utilities.graph.Vertex;

/**
 * Simple implementation of a BreadthFirstSearch returning all known paths to
 * given Vertex.
 * 
 * @param <T>
 */
public class BreadthFirstVertexSearch<T> implements PathSearch<T> {

	@Override
	public Optional<Path<T>> findFirst(Graph<T> graph, Vertex<T> startPoint, Vertex<T> endPoint) {
		Optional<Path<T>> path = findVertex(graph, startPoint, endPoint, null);
		if (path.isPresent()) {
			Optional<Path<T>> initialPath = new SimpleBacktrackingPathFinder().findDirectPath(startPoint, path.get());
			return initialPath;
		}
		return Optional.empty();
	}
	
	@Override
	public List<Optional<Path<T>>> findPaths(Graph<T> graph, Vertex<T> startPoint, Vertex<T> endPoint, int numberOfPaths) {
		List<Optional<Path<T>>> paths = new ArrayList<>();
		Set<Vertex<T>> excludes = new HashSet<>();
		paths.add(findPaths(graph, startPoint, endPoint, excludes, numberOfPaths, paths));
		return paths;
	}
	
	private Optional<Path<T>> findPaths(Graph<T> graph, Vertex<T> startPoint, Vertex<T> endPoint, Set<Vertex<T>> excludes, int numberOfPaths, List<Optional<Path<T>>> pathList){
		Optional<Path<T>> initialPath = Optional.empty();
		if(numberOfPaths > 0){
			Optional<Path<T>> path = findVertex(graph, startPoint, endPoint, excludes);
			if (path.isPresent()) {
				initialPath = new SimpleBacktrackingPathFinder().findDirectPath(startPoint, path.get());
				excludes.add(initialPath.get().getPath().get(1));
				findPaths(graph, startPoint, endPoint, excludes, --numberOfPaths, pathList);
			}
		}
		if(initialPath.isPresent()){
			pathList.add(initialPath);
		}
		return Optional.empty();
	}
	
	private Optional<Path<T>> findVertex(Graph<T> graph, Vertex<T> startPoint, Vertex<T> endPoint, Set<Vertex<T>> excludes) {
		LinkedList<Vertex<T>> visiting = new LinkedList<>();
		Path<T> path = new Path<>();
		Set<Vertex<T>> visited = new HashSet<>(graph.getVerticies().size());
		
		if(excludes != null){
			visited.addAll(excludes);
		}
		
		path.addVertex(startPoint);

		// Start with all known edges of starting point
		Set<Edge<T>> knownEdges = startPoint.getEdges();

		for (Edge<T> knownEdge : knownEdges) {
			knownEdge.getToVertex().setParentVertex(startPoint);
			visiting.offer(knownEdge.getToVertex());
		}

		while (hasMoreVerticiesToVisit(visiting)) {
			Vertex<T> current = visiting.poll();

			if (!visited.contains(current)) {
				// keep track of visited verticies
				visited.add(current);
				path.addVertex(current);

				for (Edge<T> otherEdge : current.getEdges()) {
					if (!visited.contains(otherEdge.getToVertex())) {
						otherEdge.getToVertex().setParentVertex(current);
						visiting.offer(otherEdge.getToVertex());
					}
				}

				if (current.equals(endPoint)) {
					return Optional.of(path);
				}
			}
		}

		return Optional.empty();
	}

	private boolean hasMoreVerticiesToVisit(LinkedList<Vertex<T>> visiting) {
		return !visiting.isEmpty();
	}

	
}
