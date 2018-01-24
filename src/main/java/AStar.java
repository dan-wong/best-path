package components;

import java.util.*;

public class AStar {
	private Set<Node> _closedSet;
	private Set<Node> _openSet;

	private Map<Node, Node> _cameFrom; //<Destination, Source>
	private Map<Node, Double> _gScore;
	private Map<Node, Double> _fScore;

	private Map<Coordinate, Node> _graph;

	public AStar(Node start, Node goal, Map<Coordinate, Node> graph) {
		//Initialization of global variables;
		_closedSet = new HashSet<>();
		_openSet = new HashSet<>();

		_cameFrom = new HashMap<>();
		_gScore = new HashMap<>();
		_fScore = new HashMap<>();

		_graph = graph;

		//Setting up initial values
		_openSet.add(start);
		for (Node node : _graph.values()) {
			_gScore.put(node, Double.POSITIVE_INFINITY);
			_fScore.put(node, Double.POSITIVE_INFINITY);
		}

		_gScore.put(start, 0.0);
		_fScore.put(start, heuristic_cost_estimate(start, goal));

		while (!_openSet.isEmpty()) {
			Node current = getLowestFScore();
			if (current.equals(goal)) {
				break; //Found best path
			}

			_openSet.remove(current);
			_closedSet.add(current);

			for (Node neighbour : getNeighbours(current)) {
				if (_closedSet.contains(neighbour)) continue;
				if (!_openSet.contains(neighbour)) _openSet.add(neighbour);

				double tentative_gScore = _gScore.get(current) + Math.abs(current.getWeight() - neighbour.getWeight());
				if (tentative_gScore >= _gScore.get(neighbour)) continue;

				_cameFrom.put(neighbour, current);
				_gScore.put(neighbour, tentative_gScore);
				_fScore.put(neighbour, tentative_gScore + heuristic_cost_estimate(neighbour, goal));
			}
		}
	}

	public List<Node> reconstructPath(Node current) {
		List<Node> totalPath = new LinkedList<>();
		totalPath.add(current);

		while (_cameFrom.containsKey(current)) {
			current = _cameFrom.get(current);
			totalPath.add(current);
		}

		return totalPath;
	}

	public double getWeight(List<Node> path) {
		double weight = 0.0;
		for (int i = 0; i < path.size() - 1; i++) {
			weight += Math.abs(path.get(i).getWeight() - path.get(i + 1).getWeight());
		}
		return weight;
	}

	private double heuristic_cost_estimate(Node current, Node goal) {
		//Diagonal distance
		return Math.max(Math.abs(current.getX() - goal.getX()), Math.abs(current.getY() - goal.getY()));
	}

	private Node getLowestFScore() {
		Node minNode = null;
		double min = Double.POSITIVE_INFINITY;

		for (Node node : _openSet) {
			if (minNode == null || min > _fScore.get(node)) {
				minNode = node;
				min = _fScore.get(node);
			}
		}
		return minNode;
	}

	private List<Node> getNeighbours(Node node) {
		int x = node.getX(), y = node.getY();
		List<Node> neighbours = new ArrayList<>();

		neighbours.add(_graph.get(new Coordinate(x - 1, y + 1))); //Up one, left one
		neighbours.add(_graph.get(new Coordinate(x, y + 1))); //Up one
		neighbours.add(_graph.get(new Coordinate(x + 1, y + 1))); //Up one, right one
		neighbours.add(_graph.get(new Coordinate(x + 1, y))); //Right one
		neighbours.add(_graph.get(new Coordinate(x + 1, y - 1))); //Down one, right one
		neighbours.add(_graph.get(new Coordinate(x, y - 1))); //Down one
		neighbours.add(_graph.get(new Coordinate(x - 1, y - 1))); //Down one, left one

		neighbours.removeAll(Collections.singleton(null));

		return neighbours;
	}
}
