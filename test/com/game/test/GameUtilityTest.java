/**
 * 
 */
package com.game.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.game.data.Node;
import com.game.util.GameUtility;

/**
 * @author E470798
 * 
 *         Class holds the test cases for common utility functions used across
 *         game of life.
 */

public class GameUtilityTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.game.util.GameUtility#getContents(java.lang.String)}.
	 */
	@Test
	public void testGetContents() {
		Assert.assertNull(GameUtility.getContents("invalidPath.txt"));
		Assert.assertNotNull(GameUtility.getContents("toadPattern.txt"));
	}

	/**
	 * Test method for
	 * {@link com.game.util.GameUtility#filterNode(java.util.List, int, int)}.
	 */
	@Test
	public void testFilterNode() {
		List<Node> testNodes = new ArrayList<Node>();

		testNodes.add(new Node(0, 0, true, false));
		testNodes.add(new Node(0, 1, true, false));
		testNodes.add(new Node(1, 0, true, false));
		testNodes.add(new Node(1, 1, true, false));
		testNodes.add(new Node(-1, 0, false, false));

		Assert.assertEquals(4, GameUtility.filterNode(testNodes, 2, 2).size());

	}

	@Test
	public void testFilterNode1() throws Exception {
		List<Node> testNodes = new ArrayList<Node>();

		testNodes.add(new Node(0, 0, true, false));
		testNodes.add(new Node(0, 1, true, false));
		testNodes.add(new Node(1, 0, true, false));
		testNodes.add(new Node(1, 1, true, false));
		testNodes.add(new Node(-1, 0, true, false));

		Assert.assertEquals(5, GameUtility.filterNode(testNodes, 2, 2).size());

	}

	@Test
	public void testFilterNode2() {
		Assert.assertNull(GameUtility.filterNode(null, 0, 0));

		Assert.assertNull(GameUtility.filterNode(new ArrayList<Node>(), 0, 0));
	}

	@Test(expected = Exception.class)
	public void testPrintData1() throws Exception {
		GameUtility.printData(null, 2, 1); // Exception

		GameUtility.printData(new ArrayList<Node>(), 2, 1); // Exception
	}

	/**
	 * Test method for
	 * {@link com.game.util.GameUtility#tryParseInt(java.lang.String)}.
	 */
	@Test
	public void testTryParseInt() {
		Assert.assertFalse(GameUtility.tryParseInt(" "));

		Assert.assertFalse(GameUtility.tryParseInt("2As"));

		Assert.assertTrue(GameUtility.tryParseInt("23"));

		Assert.assertFalse(GameUtility.tryParseInt(""));
	}

}
