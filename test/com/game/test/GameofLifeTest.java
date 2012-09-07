package com.game.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.game.GameofLife;

/**
 * @author Karthikeyan
 * 
 *         Class holds unit test cases for Entire game of life testing
 * 
 */
public class GameofLifeTest {

	private static GameofLife gameofLife;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gameofLife = new GameofLife();
	}

	@AfterClass
	public static void setUpAfterClass() throws Exception {
		gameofLife = null;
	}

	@Test
	public void testGetInitialArrayCreation() throws Exception {
		Assert.assertNotNull(gameofLife.getInitialArray("1	1	" + "1	0")); // 1 1
		// 1 0
	}

	@Test
	public void testGetInitialArray() throws Exception {
		Assert.assertNotNull(gameofLife.getInitialArray("1\t1\n1\t0")); // 1 1
		// 1 0
	}

	@Test
	public void testGetInitialArray1() throws Exception {
		Assert.assertNull(gameofLife.getInitialArray(null)); // null
	}

	@Test(expected = Exception.class)
	public void testGetInitialArray2() throws Exception {
		gameofLife.getInitialArray("kdakjdj sdasdas"); // 0
	}

	@Test(expected = Exception.class)
	public void testGetInitialArray3() throws Exception {
		gameofLife.getInitialArray("kdakjdj\tsdasdas"); // 0 0
	}

	@Test
	public void testNextGeneration() throws Exception {
		Assert.assertFalse(gameofLife.nextGeneration("wadasdsad.txt"));
	}

	@Test
	public void testNextGeneration1() throws Exception {
		Assert.assertFalse(gameofLife.nextGeneration("wadasdsad.txt"));
	}

	@Test
	public void testNextGeneration2() throws Exception {
		Assert.assertFalse(gameofLife.nextGeneration("wadasdsad.txt"));
	}

	@Test
	public void testNextGenerationBlockPattern() throws Exception {
		Assert.assertTrue(gameofLife.nextGeneration("blockPattern.txt"));
	}

	@Test
	public void testNextGenerationBoatPattern() throws Exception {
		Assert.assertTrue(gameofLife.nextGeneration("boatPattern.txt"));
	}

	@Test
	public void testNextGenerationBlinkerPattern() throws Exception {
		Assert.assertTrue(gameofLife.nextGeneration("blinkerPattern.txt"));
	}

	@Test
	public void testNextGenerationToadPattern() throws Exception {
		Assert.assertTrue(gameofLife.nextGeneration("toadPattern.txt"));
	}

	@Test
	public void testNextGenerationTestPattern() throws Exception {
		Assert.assertTrue(gameofLife.nextGeneration("testPattern.txt"));
	}

}
