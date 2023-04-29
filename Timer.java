// Name: Dana Hum & Charlie To
// Date: January 18th, 2023
// Teacher: Ms. Basaraba
// Descritpion: 2 minute count down timer

import java.awt.*; // access to java files
import hsa.Console; // access to Console class
import java.lang.*; // to access Thread Class

public class Timer extends Thread
{
    private Console c; // private console
    public void timer ()  // timer method
    {
	int border = 5;
	for (int x = 120 ; x >= 0 ; x--)
	{
	    c.setFont (new Font ("SansSerif", Font.BOLD, 27));
	    c.setColor (Color.BLACK);
	    c.fillRoundRect (455-border, 25-border, 100 + border*2, 50+border*2, 10, 10);
	    c.setColor (new Color (250, 94, 82));
	    c.fillRoundRect (455, 25, 100, 50, 10, 10);
	    c.setColor (Color.BLACK);
	    c.drawString (x + "", 480, 60);

	    try
	    {
		Thread.sleep (1000); // delay
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.setColor (new Color (60, 158, 250)); // sets color to bg
	c.fillRect (0, 0, 800, 500);
	c.setColor (Color.BLACK);
	c.setFont (new Font ("SansSerif", Font.BOLD, 60));
	c.drawString ("TIMES UP", 100, 100);
	c.setFont (new Font ("SansSerif", Font.PLAIN, 40));
	c.drawString ("Press the space bar to end", 100, 200);
    }


    public Timer (Console con)  // setting console to c
    {
	c = con;
    }


    public void run ()
    {
	timer ();
    }
}
