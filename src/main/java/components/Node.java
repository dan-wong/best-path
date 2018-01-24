package components;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Node {
	private Coordinate _coordinate;
	private double _weight;

	public Node(int x, int y, double weight) {
		_coordinate = new Coordinate(x, y);
		_weight = weight;
	}

	public double getWeight() {
		return _weight;
	}

	public int getX() {
		return _coordinate.getX();
	}

	public int getY() {
		return _coordinate.getY();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Node) {
			Node other = (Node) o;
			return (_weight == other._weight) && _coordinate.equals(other._coordinate);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(11, 15)
				.append(_coordinate)
				.append(_weight)
				.toHashCode();
	}
}
