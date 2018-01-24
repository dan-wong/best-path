import components.Coordinate;
import components.Node;

import java.util.ArrayList;
import java.util.List;

public class BestPath {
	private List<Node> _bestPath;
	private double _weight = Double.POSITIVE_INFINITY;

	public BestPath(int[][] rawGraph) {
		for (int i = 0; i < rawGraph.length; i++) {
			Node start = new Node(1, i+1, rawGraph[i][0]);
			for (int j = 0; j < rawGraph.length; j++) {
				Node finish = new Node(rawGraph[0].length, j+1, rawGraph[j][rawGraph[0].length-1]);
				AStar astar = new AStar(start, finish, rawGraph);

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
		System.out.println(getBestPath());
	}

	public String getBestPath() {
		List<String> pathCoordinates = new ArrayList<>();
		_bestPath.forEach(node -> pathCoordinates.add("[" + node.getX() + "," + node.getY() + "]"));

		StringBuilder sb = new StringBuilder();
		sb.append("Total Elevation Change: ").append(_weight).append("\n");
		for (int i = pathCoordinates.size() - 1; i >= 0; i--) {
			sb.append(pathCoordinates.get(i)).append("\n");
		}

		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
}
