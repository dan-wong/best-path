import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AStarTest {
	public AStarTest() {
	}

	@Test
	public void testAStar_BestGreedyPathFunction() {
		int[][] rawGraph = {
				{3, 6, 3, 7, 2, 5},
				{1, 4, 2, 4, 1, 4},
				{7, 9, 5, 6, 9, 2},
				{10, 8, 4, 3, 10, 5}};

		BestPath bestPath = new BestPath(rawGraph);
		assertEquals("Total Elevation Change: 7.0\n" +
				"[1,1]\n" +
				"[2,2]\n" +
				"[3,1]\n" +
				"[4,2]\n" +
				"[5,1]\n" +
				"[6,2]", bestPath.getBestPath());
	}
}