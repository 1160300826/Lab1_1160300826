/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package test.P4.twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import P4.twitter.SocialNetwork;
import P4.twitter.Tweet;

public class SocialNetworkTest {
	private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
	private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
	private static final Instant d3 = Instant.parse("2016-02-17T10:00:50Z");
	private static final Instant d4 = Instant.parse("2016-02-17T10:30:00Z");

	private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much? @t&m", d1);
	private static final Tweet tweet2 = new Tweet(2, "t&m", "rivest talk in 30 minutes @alya", d2);
	private static final Tweet tweet3 = new Tweet(1, "alya", "is it reasonable to talk?", d3);
	private static final Tweet tweet4 = new Tweet(2, "bbdle", "发给克隆技术的发明你考虑对方立刻即将离开水力发电会计科目考虑你", d4);
	Map<String, Set<String>> follows=new HashMap();
	Set<String> friend=new HashSet();
	Set<String> friend2=new HashSet();
	List<String> influencers1=new ArrayList();
	
	
    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4));
        friend.add("t&m");
        friend.add("alya");
        follows.put("alyssa",friend);
        friend2.add("alya");
        follows.put("t&m",friend2);
        
        assertEquals("expected singleton list", follows, followsGraph);
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        influencers1.add("alyssa");
        influencers1.add("t&m");
        assertEquals("expected singleton list", influencers1, influencers);
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
