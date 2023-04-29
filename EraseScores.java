// Name: Dana Hum & Charlie To
// Date: January 18th, 2023
// Teacher: Ms. Basaraba
// Descritpion: Clears the high score files
import java.awt.*;
import hsa.Console;
import java.lang.*;
import java.io.*;

public class EraseScores extends Thread
{
    private Console c;

    public void eraseScores (int mode)
    {

	try
	{
	    if (mode == 3)
	    {
		FileWriter w = new FileWriter ("highscoresTimed.txt");
		w.write ("");
		w.close ();
	    }
	    else if(mode == 4)
	    {
		FileWriter w = new FileWriter ("highscoresUntimed.txt");
		w.write ("");
		w.close ();
	    }
	}   
	catch (Exception e)
	{
	    c.print ("Error loading File (Thread)");
	}

    }


    public EraseScores (Console con)
    {
	c = con;
    }


    public void eraseTimed ()
    {
	eraseScores (3);
    }


    public void eraseUntimed ()
    {
	eraseScores (4);
    }
}
