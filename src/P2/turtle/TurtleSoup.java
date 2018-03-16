/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	turtle.forward(sideLength);
    	turtle.turn(90);
    	turtle.forward(sideLength);
    	turtle.turn(90);
    	turtle.forward(sideLength);
    	turtle.turn(90);
    	turtle.forward(sideLength);
    	turtle.turn(90);
    	
    	
        /*throw new RuntimeException("implement me!");*/
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	try {
    		if(sides>2&&Integer.valueOf(sides) instanceof Integer)
    		{
    			double degree;
    			degree=180*(sides-2);
    			degree=degree/sides;
    			return degree;
    			
    		}
    			
    		
    	}
    	catch(Exception e){
    		e.printStackTrace(); 
    	}
        throw new RuntimeException("implement me!");
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	int sides;
    	
    	if(angle>0&&angle<180)
    	{
    		sides=(int)((360/(180-angle))+0.5);
    			return sides;
    	}
    	
        throw new RuntimeException("implement me!");
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	
    	for(int i=0;i<sides;i++)
    	{
    		turtle.forward(sideLength);
    		double angle=calculateRegularPolygonAngle(sides);
    	}
    	
    	
        throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	
    	double angle;
    	double d;
    	if(currentX==targetX)
    	{
    		if(targetY>currentY)
    		return (360-currentHeading)%360;
    		else
    		return (360+180-currentHeading)%360;
    	}
    	if(targetY==currentY)
    	{
    		if(currentX<targetX)
    		return (90-currentHeading)%360;
    		else
    		return (90-currentHeading)%360;
    	}
        d = Math.atan((targetY - currentY) / (targetX - currentX));
        d=Math.toDegrees(d);
        angle=(90-d-currentHeading+360)%360;
    	return angle;	
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
    	double currentHeading=0;
    	List<Double> angle=new ArrayList<>();
    	/*int sideLength=(int)Math.sqrt(Math.pow(Math.abs(xCoords.get(0)-xCoords.get(1)), 2)+Math.pow(Math.abs(yCoords.get(0)-yCoords.get(1)), 2));*/
    	double tmp=0;
    	for(int i=1;i<xCoords.size();i++)
    	{
    		
    		tmp=calculateHeadingToPoint(tmp,xCoords.get(i-1),yCoords.get(i-1),
        			xCoords.get(i),yCoords.get(i));
    		angle.add(tmp);
    		
    	}
    	return angle;
        /*throw new RuntimeException("implement me!");*/
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	java.util.Random angle=new java.util.Random();
    	java.util.Random side=new java.util.Random();
    	for(int i=0;i<120;i++)
    	{
    		turtle.color(PenColor.BLUE);
    		turtle.forward(10+i);
        	turtle.turn(300-2*i);
    	}
    	turtle.forward(30);
    	for(int i=0;i<50;i++)
    	{
    		turtle.color(PenColor.GREEN);
    		turtle.forward(10-2*i);
        	turtle.turn(300-1.5*i);
    	}
    	turtle.forward(30);
    	for(int i=0;i<100;i++)
    	{
    		turtle.color(PenColor.ORANGE);
    		turtle.forward(10-2*i);
        	turtle.turn(300-1.5*i);
    	}
    	turtle.forward(30);
    	for(int i=0;i<100;i++)
    	{
    		turtle.color(PenColor.PINK);
    		turtle.forward(10-2*i);
        	turtle.turn(300-1.5*i);
    	}
    	turtle.turn(120);
    	turtle.forward(120);
    	
    	for(int i=0;i<100;i++)
    	{
    		turtle.color(PenColor.BLACK);
    		turtle.forward(10-2*i);
        	turtle.turn(300-1.5*i);
    	}
    	turtle.forward(30);
    	
    	
        /*throw new RuntimeException("implement me!");*/
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        /*drawSquare(turtle, 40);*/
        drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }

}
