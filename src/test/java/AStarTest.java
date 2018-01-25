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

	//0ms
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

	//80ms
	@Test
	public void testAStar_withRandomGraph30() {
		System.out.print("testAStar_withRandomGraph30() - ");
		int[][] rawGraph = generateGraph(30, 30, 30);

		BestPath bestPath = new BestPath(rawGraph);
		bestPath.getBestPath();
	}

	//70ms
	@Test
	public void testAStar_withRandomGraph50() {
		System.out.print("testAStar_withRandomGraph50() - ");
		int[][] rawGraph = generateGraph(50, 50, 50);

		BestPath bestPath = new BestPath(rawGraph);
		bestPath.getBestPath();
	}

	//665ms
	@Test
	public void testAStar_withRandomGraph100() {
		System.out.print("testAStar_withRandomGraph50() - ");
		int[][] rawGraph = generateGraph(100, 100, 100);

		BestPath bestPath = new BestPath(rawGraph);
		bestPath.getBestPath();
	}

	//16.0
	@Test
	public void testWeightAStar() {
		System.out.println("testWeightAStar()");
		int[][] rawGraph = generateGraph(50, 50, 50);

		for (double i=1; i<20; i+=0.5) {
			long startTime = System.nanoTime();
			BestPath bestPath = new BestPath(rawGraph);
			bestPath.setAStarWeight(i);
			bestPath.getBestPath();
			System.out.println("Weight: " + i + " - " + (System.nanoTime() - startTime)/1000000 + "ms");
		}
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