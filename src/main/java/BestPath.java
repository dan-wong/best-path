import components.Node;

import java.util.ArrayList;
import java.util.List;

public class BestPath {
	private List<Node> _bestPath;
	private int[][] _rawGraph;

	private double _weight = Double.POSITIVE_INFINITY;
	private double _aStarWeight = 16.0;

	public BestPath(int[][] rawGraph) {
		_rawGraph = rawGraph;
	}

	private void runAStar() {
		for (int i = 0; i < _rawGraph.length; i++) {
			Node start = new Node(1, i+1, _rawGraph[i][0]);
			AStar astar = new AStar(start, _rawGraph, _aStarWeight);

			List<Node> currentPath = astar.getBestPath();
			double currentPathWeight = astar.getWeight(currentPath);
			if (currentPathWeight < _weight) {
				_weight = currentPathWeight;
				_bestPath = currentPath;
			}
		}
	}

	public void setAStarWeight(double weight) {
		_aStarWeight = weight;
	}

	public void printBestPath() {
		System.out.println(getBestPath());
	}

	public double getBestWeight() {
		if (_bestPath == null) runAStar();
		return _weight;
	}

	public String getBestPath() {
		if (_bestPath == null) runAStar();

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
