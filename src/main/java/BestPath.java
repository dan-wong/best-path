import components.AStar;
import components.Coordinate;
import components.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestPath {
	private Map<Coordinate, Node> _graph;
	private List<Node> _bestPath;
	private double _weight = Double.POSITIVE_INFINITY;

	public BestPath(int[][] rawGraph) {
		_graph = new HashMap<>();

		for (int i = 0; i < rawGraph.length; i++) {
			for (int j = 0; j < rawGraph[0].length; j++) {
				Node node = new Node(j + 1, i + 1, rawGraph[i][j]);
				_graph.put(new Coordinate(j + 1, i + 1), node);
			}
		}

		for (int i = 0; i < rawGraph.length; i++) {
			Node start = _graph.get(new Coordinate(1, i + 1));
			for (int j = 0; j < rawGraph.length; j++) {
				Node finish = _graph.get(new Coordinate(rawGraph[0].length, j + 1));
				AStar astar = new AStar(start, finish, _graph);

				List<Node> currentPath = astar.reconstructPath(finish);
				double currentPathWeight = astar.getWeight(currentPath);
				if (currentPathWeight < _weight) {
					_weight = currentPathWeight;
					_bestPath = currentPath;
				}
			}
		}
	}

	public void printBestPath() {
		List<String> pathCoordinates = new ArrayList<>();
		_bestPath.forEach(node -> pathCoordinates.add("[" + node.getX() + "," + node.getY() + "]"));

		System.out.println("Total Elevation Change: " + _weight);
		for (int i = pathCoordinates.size() - 1; i >= 0; i--) {
			System.out.println(pathCoordinates.get(i));
		}
	}
}
