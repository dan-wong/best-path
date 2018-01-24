import org.junit.Test;

public class AStarTest {
	public AStarTest() {
	}

	@Test
	public void testAStar() {
		int[][] rawGraph = {
				{3, 6, 3, 7, 2, 5},
				{1, 4, 2, 4, 1, 4},
				{7, 9, 5, 6, 9, 2},
				{10, 8, 4, 3, 10, 5}};

		BestPath bestPath = new BestPath(rawGraph);
		bestPath.printBestPath();
	}
}