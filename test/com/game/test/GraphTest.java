package com.game.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.game.data.Graph;
import com.game.data.Node;

/**
 * @author Karthikeyan
 * 
 *         Class holds unit test cases for verifying the graph data structure
 *         which holding & representing the cell world
 * 
 */
public class GraphTest {

	private static Graph graph;

	@AfterClass
	public static void setUpAfterClass() throws Exception {
		graph = null;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		List<Node> testNodes = new ArrayList<Node>();

		testNodes.add(new Node(0, 0, true, false));
		testNodes.add(new Node(0, 1, true, false));
		testNodes.add(new Node(1, 0, false, false));
		testNodes.add(new Node(1, 1, true, false));

		graph = new Graph(testNodes, 2, 2);
	}

	@Test
	public void testSetNeighbors() throws Exception {
		Assert.assertNotNull(graph.createNodesUpdateNegighborsStatus(graph
				.getCellWorldList()));
	}

	@Test
	public void testSetNeighbors1() throws Exception {
		Assert.assertNull(graph
				.createNodesUpdateNegighborsStatus(new ArrayList<Node>()));
	}

	@Test
	public void testSetNeighbors2() throws Exception {
		Assert.assertNull(graph.createNodesUpdateNegighborsStatus(null));
	}

	@Test
	public void testCreateNewNode() {
		Assert.assertNotNull(graph.createNewNode(1, -9));
	}

	@Test
	public void testGetNode() throws Exception {
		Assert.assertNotNull(Graph.searchNode(graph.getCellWorldList(), 0, 0));
	}

	@Test(expected = Exception.class)
	public void testGetNode1() throws Exception {
		Graph.searchNode(null, 0, 2);
	}

	@Test(expected = Exception.class)
	public void testGetNode2() throws Exception {
		Graph.searchNode(new ArrayList<Node>(), 0, 0);
	}

	@Test
	public void testGetNode3() throws Exception {
		Assert.assertNull(Graph.searchNode(graph.getCellWorldList(), 0, -99));
	}

	@Test(expected = Exception.class)
	public void testGetLiveCellCount1() throws Exception {
		graph.getLiveCellCount(null);
	}

	@Test
	public void testGetLiveCellCount2() throws Exception {
		Assert.assertEquals(
				2,
				graph.getLiveCellCount(graph.getCellWorldList().get(0)
						.getNeighboursStatus()));
	}

	@Test
	public void testGetLiveCellCount() throws Exception {
		Assert.assertNotSame(
				5,
				graph.getLiveCellCount(graph.getCellWorldList().get(0)
						.getNeighboursStatus()));
	}

}
