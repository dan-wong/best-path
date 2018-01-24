import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AStarTest {
	private long _startTime;

	public AStarTest() {}

	@Before
	public void before() {
		_startTime = System.nanoTime();
	}

	@After
	public void after() {
		System.out.print((System.nanoTime() - _startTime)/1000000 + "ms\n");
	}

	//7ms
	@Test
	public void testAStar_BestGreedyPathFunction() {
		System.out.print("testAStar_BestGreedyPathFunction() - ");
		int[][] rawGraph = {
				{3, 6, 3, 7, 2, 5},
				{1, 4, 2, 4, 1, 4},
				{7, 9, 5, 6, 9, 2},
				{10, 8, 4, 3, 10, 5}};

		BestPath bestPath = new BestPath(rawGraph);
		assertEquals(7.0, bestPath.getBestWeight(), 0);
	}

	//180ms - 30,30,30
	@Test
	public void testAStar_withRandomGraph() {
		System.out.print("testAStar_withRandomGraph() - ");
		int[][] rawGraph = generateGraph(30, 30, 30);

		BestPath bestPath = new BestPath(rawGraph);
		bestPath.getBestPath();
	}

	private int[][] generateGraph(int width, int length, int maxValue) {
		int[][] graph = new int[width][length];

		for (int i=0; i<width; i++) {
			for (int j=0; j<length; j++) {
				graph[i][j] = (int) (Math.random() * maxValue) + 1;
			}
		}

		return graph;
	}
}