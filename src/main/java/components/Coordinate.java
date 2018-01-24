package components;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Coordinate {
	private int _x;
	private int _y;

	public Coordinate(int x, int y) {
		_x = x;
		_y = y;
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Coordinate) {
			Coordinate other = (Coordinate) o;
			return _x == other._x && _y == other._y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(1, 3)
				.append(_x)
				.append(_y)
				.toHashCode();
	}
}
