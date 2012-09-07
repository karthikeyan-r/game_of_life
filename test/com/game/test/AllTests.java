package com.game.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Comprehensive class, with test cases for entire algorithm of Game of Life
 * 
 * @author Karthikeyan
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ GameUtilityTest.class, GraphTest.class, GameofLifeTest.class })
public class AllTests {

}
