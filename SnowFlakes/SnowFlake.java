import java.applet.Applet;
import java.awt.*;
import java.lang.Math;

public class SnowFlake extends Applet {

  /*
   * Draw a snowflake like star at coordinate x,y on Canvas canvas.
   * The beams of the star are beamLen pixels long
   */
  private void drawStar(Graphics canvas, int x, int y, int beamLen ) {
	  for (int i=0; i<6; i++) {  //draws the six points, each beam from x,y to endX,endY
          int endX = x + (int) (beamLen * Math.cos(((2 * Math.PI) / 6) * i));
          int endY = y - (int) (beamLen * Math.sin(((2 * Math.PI) / 6) * i));
          canvas.drawLine(x,y,endX,endY); //draw this beam
          if (beamLen > 5){
        	  drawStar(canvas, endX, endY, beamLen/3);   
          }
      }
  }
  
    
  /**
   * Paint method of the applet. Gets called whenever the applet needs a redraw
   * @param gr - The canvas to draw the applets content on
   */
  public void paint (Graphics gr) { 
    int width  = getSize().width;
    int height = getSize().height;
    int min; //?? why this
    
    // Set background and pen color
    setBackground(Color.white);
    gr.setColor  (Color.blue);
    
    // Calculate the maximal length in the window
      
    // Draw a star  
    drawStar(gr, width/2, height/2, Math.min(width/4, height/4));
    
  }
}
