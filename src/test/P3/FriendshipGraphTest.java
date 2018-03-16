package test.P3;
import static org.junit.Assert.*;

import org.junit.Test;

import P3.FriendshipGraph;
import P3.Person;

public class FriendshipGraphTest {
	
	
	
	
	@Test
	public void testGaddVertex() {	
	FriendshipGraph G = new FriendshipGraph();
	Person ra = new Person("Ra");
	G.addVertex(ra);
	}
	@Test
	public void testaddedge() {
		FriendshipGraph G = new FriendshipGraph();
		Person b = new Person("b");
		Person a = new Person("a");
		G.addVertex(a);
		G.addVertex(b);
		G.addedge(a,b);
	}
	@Test
	public void testGetDistance() {
	FriendshipGraph G = new FriendshipGraph();
    Person rachel = new Person("Rachel");
	Person ross = new Person("ross");
	Person ben = new Person("ben");
	Person kramer = new Person("Kramer");
	G.addVertex(rachel);
	G.addVertex(ross);
	G.addVertex(ben);
	G.addVertex(kramer);
	G.addedge(rachel, ross);
	G.addedge(ross, rachel);
	G.addedge(ben, ross);
	G.addedge(ross, ben);
	assertEquals(1, G.getDistance(rachel, ross));
	assertEquals(2, G.getDistance(rachel, ben));
	assertEquals(0, G.getDistance(rachel, rachel));
	assertEquals(-1, G.getDistance(rachel, kramer));

	}

}
