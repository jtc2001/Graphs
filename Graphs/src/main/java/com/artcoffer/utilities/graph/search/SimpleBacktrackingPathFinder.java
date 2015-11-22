package com.artcoffer.utilities.graph.search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Consumer;

import com.artcoffer.utilities.graph.Vertex;

public class SimpleBacktrackingPathFinder implements BacktrackingPathFinder{

	@Override
	public <T> Optional<Path<T>> findDirectPath(Vertex<T> startPoint, Path<T> fullPath) {
		LinkedList<Vertex<T>> pathList = new LinkedList<>((LinkedList<Vertex<T>>) fullPath.getPath());

		Vertex<T> target = pathList.getLast();
		Path<T> actualPath = new Path<>();
		Stack<Vertex<T>> actualPathStack = new Stack<>();

		actualPathStack.add(target);
		// If there is no parent, we are already there return.
		if (target.getParentVertex() == null) {
			actualPath.addVertex(target);
			return Optional.of(actualPath);
		}

		Vertex<T> nextParent = target.getParentVertex();
		while (nextParent.getParentVertex() != null && !nextParent.equals(startPoint)) {
			actualPathStack.add(nextParent);
			nextParent = nextParent.getParentVertex();
		}

		// Finally add the starting node in and return
		actualPathStack.add(nextParent);
		Collections.reverse(actualPathStack);
		actualPathStack.stream().forEach(new Consumer<Vertex<T>>() {
			@Override
			public void accept(Vertex<T> t) {
				actualPath.addVertex(t);
			}
		});
		return Optional.of(actualPath);
	}

}
