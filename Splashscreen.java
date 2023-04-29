// Name: Dana Hum & Charlie To
// Date: January 18th, 2023
// Teacher: Ms. Basaraba
// Descritpion:the splashscreen of the game
import java.awt.*;
import hsa.Console;
import java.lang.*;
import java.io.*;


public class Splashscreen extends Thread
{
    private Console c;
    Font Norm = new Font ("SansSerig", Font.BOLD, 20);
    static Color bg = new Color (60, 158, 250);
    static Color tile = new Color (223, 242, 241);

    public void splashscreen ()
    {

	//letters
	tileMove (100, 450, 20, 200, "B");
	tileMove (150, 450, 126, 150, "O");
	tileMove (250, 450, 232, 100, "G");
	tileMove (350, 450, 338, 100, "G");
	tileMove (450, 450, 444, 150, "L");
	tileMove (500, 450, 550, 200, "E");
	
	c.setColor(Color.BLACK);
	c.fillRect(150, 280, 350, 145);
	c.setColor(tile);
	c.fillRect(175, 300, 300, 100);
	c.setColor(Color.BLACK);
	c.setFont (Norm);
	c.drawString ("Press any key to continue", 200, 350);

	c.getChar ();
    }


    public void tileMove (int startX, int startY, int endX, int endY, String letter)  //for the moving tiles
    {
	
	double changeX = (endX - startX) / 20.0; //how much the x changes each frame
	double changeY = (endY - startY) / 20.0; //how much the y changes each frame
	double x = startX;
	double y = startY;

	for (int t = 0 ; t < 20 ; t++)
	{
	    //the erasing
	    c.setColor (bg);
	    c.fillRect ((int) (x - changeX), (int) (y - changeY), 90, 90);

	    //the tile
	    c.setColor (tile);
	    c.fillRect ((int) x, (int) y, 80, 80);

	    //the letter
	    c.setColor (Color.black);
	    c.drawString (letter, (int) (x - changeX) + 25, (int) (y - changeY) + 40);
	    //box
	    c.fillRect (100, 450, 450, 500);

	    x += changeX;
	    y += changeY;



	    try
	    {
		Thread.sleep (10);
	    }
	    catch (Exception e)
	    {
	    }

	}

    }


    public Splashscreen (Console con)
    {
	c = con;
    }


    public void run ()
    {
	splashscreen ();
    }
}
