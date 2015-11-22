package com.artcoffer.utilities.graph.search;

import java.util.Optional;

import com.artcoffer.utilities.graph.Vertex;

public interface BacktrackingPathFinder {

	/**
	 * Takes a full path and backtracks to find the direct path that lead to it.
	 * @param fullPath
	 * @return
	 */
	public <T> Optional<Path<T>> findDirectPath(Vertex<T> startPoint, Path<T> fullPath);
	
}
