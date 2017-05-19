/**
 * Course:      Data Structures and Algorithms for Language Processing SS 2017
 * Assignment:  lab 4, exercise 4. 19.05.2017
 * Author:      Timothy Day
 * Description: This program takes a string as an argument and checks it for XML wellformedness
 *
 * Honor Code:  I pledge that this program represents my own work.
 *		I received help from:
 * 		no one
 *		in designing and debugging my program.
 */

import java.util.Random;
import java.util.LinkedList;
import java.applet.Applet;
import java.awt.*;
import java.lang.Math;

public class SnowFlakeRandom extends Applet {
	
	/*
	 * Draw a snowflake like star at coordinate x,y on Canvas canvas.
     * The beams of the star are beamLen pixels long
     * params - the canvas, the center coords x and y and the beam length
	 */
	private void drawStar(Graphics canvas, int x, int y, int beamLen ) {
	    for (int i=0; i<6; i++) {  //draws the six points, each beam from x,y to endX,endY
	        int endX = x + (int) (beamLen * Math.cos(((2 * Math.PI) / 6) * i));
	        int endY = y - (int) (beamLen * Math.sin(((2 * Math.PI) / 6) * i));
	        canvas.drawLine(x,y,endX,endY); //draw this beam
	        if (beamLen > 8){
	    	    drawStar(canvas, endX, endY, beamLen/3);   
	        }
	    }
	}
	
	/**
     * Paint method of the applet. Gets called whenever the applet needs a redraw
     * @param gr - The canvas to draw the applets content on
     */
	public void paint(Graphics gr) {
		
		//get window dimensions
	    int width  = getSize().width;  //window width
	    int height = getSize().height;
	    
		//how many snow flakes to randomly make
	    Random randomizer = new Random();
	    int ranFlakes = randomizer.nextInt(20) + 5; //min of 5, because less than that is boring
	    
	    //Set background 
	    setBackground(Color.white); //note. white is 255,255,255
	    
	    //make each snowflake
	    for (int i=0; i<= ranFlakes; i++) {
	    	
	    	//set random pen color
	    	int r = randomizer.nextInt(255) + 1;
	    	int g = randomizer.nextInt(200) + 1; //I'm limiting this to make sure that every random color is visible on white backdrop
	    	int b = randomizer.nextInt(255) + 1;
	    	Color ranColor = new Color(r,g,b);
	    	gr.setColor(ranColor);
	    	
	    	//get random args
		    int ranX = randomizer.nextInt(width - 50) + 25;   // keep at least 25px away from the edges
		    int ranY = randomizer.nextInt(height - 50) + 25;
//		    int ranLength = Math.min(ranX/5, ranY/5); 		  //firt try. dont want it to be too small, nor too big
		    int ranLength = Math.min(randomizer.nextInt(width/9) + 5, randomizer.nextInt(height/9) + 5);  //better
		    
	    	// Draw a star  
	        drawStar(gr, ranX, ranY, ranLength);
	    }
	}
}
