// Name: Dana Hum & Charlie To
// Date: January 18th, 2023
// Teacher: Ms. Basaraba
// Descritpion: 2 player boggle game with a timed and untimed mode
import java.awt.*;
import java.io.*;
import hsa.*;

public class Boggle
{
    static Console c;           // The output console
    //static Timer t;
    Font Title = new Font ("SansSerif", Font.BOLD, 40);
    Font Main = new Font ("SansSerif", Font.BOLD, 30);
    Font Norm = new Font ("SansSerig", Font.BOLD, 20);
    static final Color bg = new Color (60, 158, 250);
    static Color tile = new Color (223, 242, 241);
    static boolean option;
    boolean wordInBoard;
    static int level;
    static int turn;
    static char menuOption = 0;
    static String name1; //the name of player 1
    static String name2; // the name of player 2
    static String[] p1Words = new String [100];
    static String[] p2Words = new String [100];
    static int p1TotalWords = 0;
    static int p2TotalWords = 0;
    int entries;

    int points1; //the points of the first player
    int points2; //points of the second

    String[] namesT = new String [10];
    int[] scoresT = new int [10];
    int[] scoresU = new int [10];
    String[] namesU = new String [10];

    String[] [] board = new String [4] [4];

    public Boggle ()
    {
	c = new Console ();
    }


    public void splashscreen ()
    {
       Splashscreen splash = new Splashscreen(c);
       title();
       splash.run();
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


    public void title ()
    {
	c.setColor (bg);
	c.fillRect (0, 0, 650, 800);
	c.setColor (Color.black);
	c.setFont (Title);
	c.drawString ("Boggle", 250, 60);
    }


    public void mainMenu ()
    {
	int xPosMainMenu = 150;
	int yPosMainMenu = 40;
	int borderWidth = 10;
	boolean option = true;
	title ();
	// border and box
	c.setColor (Color.BLACK);
	c.fillRect (110 - borderWidth, yPosMainMenu + 76 - borderWidth, 420 + borderWidth * 2, 320 + borderWidth * 2);
	c.setColor (tile);
	c.fillRect (110, yPosMainMenu + 76, 420, 320);
	c.setColor (Color.BLACK);

	c.setFont (Main);
	c.drawString ("Main Menu", 240, 130 + yPosMainMenu);
	c.setFont (Norm);
	c.drawString ("Press <0> for instructions", xPosMainMenu, 170 + yPosMainMenu);
	c.drawString ("Press <1> for timed highscores", xPosMainMenu, 200 + yPosMainMenu);
	c.drawString ("Press <2> for untimed  highscores", xPosMainMenu, 230 + yPosMainMenu);
	c.drawString ("Press <3> to play with a timer", xPosMainMenu, 280 + yPosMainMenu);
	c.drawString ("Press <4> to play with unlimited time", xPosMainMenu, 310 + yPosMainMenu);
	c.drawString ("Press <5> to exit", xPosMainMenu, 360 + yPosMainMenu);
	menuOption = c.getChar ();


    }



    public void instructions ()
    {
	title ();
	int x = 40;
	c.setFont (Main);
	c.drawString ("Instructions", 230, 100);
	c.setFont (Norm);
	c.drawString ("Number of Players: 2", x, 170);
	c.drawString ("Goal: Find as many words as you can in the 4x4 letter grid", x, 200);
	c.drawString ("Winner: Player with the most points", x, 230);
	c.drawString ("The letters must be adjacent to each other", x, 260);
	c.drawString ("Can't be less than 3 letters", x, 290);
	c.drawString ("You will get points depending on the length", x, 320);
	c.drawString ("You will play until the time runs out or", x, 350);
	c.drawString ("until you can't find any more words", x, 380);
	c.drawString ("Players will go one at a time", x, 410);
	c.drawString ("Press any key to go back to main menu", x, 470);

	c.getChar ();
    }


    public void highScore (int mode)
    {

	title ();
	c.setFont (Norm);
	if (mode == 3)
	{
	    c.drawString ("Timed Highscores", 220, 100);
	}
	else if (mode == 4)
	{
	    c.drawString ("Untimed Highscores", 220, 100);
	}
	c.drawString ("Press e to erase and leave, press anything else to just leave", 40, 460);
	int size = fileSize (mode);

	for (int i = 0 ; i <= 9 ; i++)
	{
	    scoresT [i] = 0; //erases all the scores
	    namesT [i] = null; //erases
	}


	if (mode == 3)
	{


	    try
	    { //opens the file, reads it
		for (int i = 0 ; i <= 9 ; i++)
		{
		    scoresT [i] = 0; //erases all the scores
		    namesT [i] = null; //erases
		}
		BufferedReader b = new BufferedReader (new FileReader ("highscoresTimed.txt"));
		int s = 9; //places the highest score at 9
		entries = scoresT.length - (size / 2); //this calculates the amount of scores saved
		while (s >= entries) //takes the entries and puts them in the arrays
		{
		    //sets the array based off of the file
		    namesT [s] = b.readLine ();
		    String temp = b.readLine ();
		    scoresT [s] = Integer.parseInt (temp);
		    s -= 1;

		}




	    }
	    catch (Exception e)
	    {
		c.println ("Error Reading File(3)");
	    }

	}
	else if (mode == 4)
	{
	    for (int i = 0 ; i <= 9 ; i++)
	    {
		scoresU [i] = 0; //erases all the scores
		namesU [i] = null;
	    }

	    try
	    {
		BufferedReader b = new BufferedReader (new FileReader ("highscoresUntimed.txt"));
		int s = 9; //places the highest score at 9
		entries = scoresU.length - (size / 2); //this calculates the amount of scores saved

		while (s >= entries) //takes the entries and puts them in the arrays
		{
		    //sets the array based off of the file
		    namesU [s] = b.readLine ();
		    String temp = b.readLine ();
		    scoresU [s] = Integer.parseInt (temp);
		    s -= 1;
		}
	    }
	    catch (Exception e)
	    {
		c.println ("Error Reading File(3)");
	    }
	}



	int y = 150;
	if (mode == 3)
	{
	    for (int t = 9, n = 1 ; t >= 0 ; t--, n++)
	    {

		if (scoresT [t] == 0)
		{
		    continue;
		}
		else
		{
		    //displays the score board
		    c.drawString (scoresT [t] + "", 400, y);
		    c.drawString (n + ".  " + namesT [t] + "", 200, y);

		    y += 30;

		}

	    }
	}
	if (mode == 4)
	{
	    for (int t = 9, n = 1 ; t >= 0 ; t--, n++)
	    {

		if (scoresU [t] == 0)
		{
		    continue;
		}
		else
		{
		    //displays the score board
		    c.drawString (scoresU [t] + "", 400, y);
		    c.drawString (n + ".  " + namesU [t] + "", 200, y);

		    y += 30;

		}

	    }
	}


	//for erasing the scores
	EraseScores erase = new EraseScores (c);
	char eraseScore = '@';
	eraseScore = c.getChar (); //this will cause the highscores frame to close
	//highscores will be empty the next time you open it.
	if (eraseScore == 'e') //you must press e to erase
	{
	    if (mode == 3) //erasing the timed scores
	    {
		erase.eraseTimed (); //starts the erase thread
		for (int i = 0 ; i <= 9 ; i++)
		{
		    scoresT [i] = 0; //erases all the scores
		    namesT [i] = null; //erases all the names


		}
	    }
	    else if (mode == 4) //erasing the untimed cores
	    {
		erase.eraseUntimed (); //starts the erase thread
		for (int i = 0 ; i <= 9 ; i++)
		{
		    scoresU [i] = 0; //erases all the scores
		    namesU [i] = null; //erases all the names

		}
	    }
	}



    }


    private void setScores (int mode)  //checks where the scores should go and saves the highscores in the file
    {
	if (mode == 3)
	{
	    if (scoresT [0] < points1)

		{
		    int i = 0;
		    while (i < scoresT.length)  //for the length of the array, check to see if points1 is larger
		    {

			if (scoresT [i] < points1)
			{

			    i++;

			}
			else
			{
			    break;
			}

		    }

		    int place = 0; //where the new score will eventually be
		    scoresT [0] = 0; //clears the lowest score
		    namesT [0] = null;


		    while (place < i - 1) //moves the lower scores down
		    {

			scoresT [place] = scoresT [place + 1];
			namesT [place] = namesT [place + 1];
			place++;
		    }

		    scoresT [place] = points1; //places the score
		    namesT [place] = name1; //places the name



		}


	    //for the second player
	    if (scoresT [0] < points2)

		{
		    int i = 0;
		    while (i < scoresT.length)  //for the length of the array, check to see if points2 is larger
		    {

			if (scoresT [i] < points2)
			{

			    i++;

			}
			else
			{
			    break;
			}

		    }
		    int place = 0; //where the score will eventually go
		    scoresT [0] = 0; //erases the lowest score
		    namesT [0] = null;
		    while (place < i - 1) //moves the scores down
		    {

			scoresT [place] = scoresT [place + 1];
			namesT [place] = namesT [place + 1];
			place++;
		    }

		    scoresT [place] = points2; //places the score and the name
		    namesT [place] = name2;



		}
	    try
	    {
		//The navigation is specific to the computer, you need the full address, right make sure there are double slashes in between everything
		FileWriter fWriter = new FileWriter ("highscoresTimed.txt");
		int y = 200; //where the scoreboard starts
		for (int t = 9 ; t >= 0 ; t--)
		{

		    if (scoresT [t] == 0) //skips over the empty scores
		    {
			continue;
		    }
		    else
		    {
			fWriter.write (namesT [t] + "\n" + scoresT [t] + "\n"); //prints the scores
			y += 30;

		    }

		}


		fWriter.close ();


	    }



	    catch (Exception e)
	    {
		c.println ("Could not open or find the file(3)");
	    }
	}

	if (mode == 4)
	{
	    if (scoresU [0] < points1)

		{
		    int i = 0;
		    while (i < scoresU.length)  //for the length of the array, check to see if points1 is larger
		    {

			if (scoresU [i] < points1)
			{

			    i++;

			}
			else
			{
			    break;
			}

		    }

		    int place = 0; //where the new score will eventually be
		    scoresU [0] = 0; //clears the lowest score
		    namesU [0] = null;


		    while (place < i - 1) //moves the lower scores down
		    {

			scoresU [place] = scoresU [place + 1];
			namesU [place] = namesU [place + 1];
			place++;
		    }

		    scoresU [place] = points1; //places the score
		    namesU [place] = name1; //places the name



		}


	    //for the second player
	    if (scoresU [0] < points2)

		{
		    int i = 0;
		    while (i < scoresU.length)  //for the length of the array, check to see if points2 is larger
		    {

			if (scoresU [i] < points2)
			{

			    i++;

			}
			else
			{
			    break;
			}

		    }
		    int place = 0; //where the score will eventually go
		    scoresU [0] = 0; //erases the lowest score
		    namesU [0] = null;
		    //c.println(i);
		    while (place < i - 1) //moves the scores down
		    {

			scoresU [place] = scoresU [place + 1];
			namesU [place] = namesU [place + 1];
			place++;
		    }

		    scoresU [place] = points2; //places the score and the name
		    namesU [place] = name2;



		}
	    try
	    {
		//The navigation is specific to the computer, you need the full address, right make sure there are double slashes in between everything
		FileWriter fWriter = new FileWriter ("highscoresUntimed.txt");
		int y = 200; //where the scoreboard starts
		for (int t = 9 ; t >= 0 ; t--)
		{

		    if (scoresU [t] == 0) //skips over the empty scores
		    {
			continue;
		    }
		    else
		    {
			fWriter.write (namesU [t] + "\n" + scoresU [t] + "\n"); //prints the scores
			y += 30;

		    }

		}


		fWriter.close ();

	    }



	    catch (Exception e)
	    {
		c.println ("Could not open or find the file(4)");
	    }
	}

    }


    private int fileSize (int mode)  //checks to see how many lines there are.
    {

	int count = 0;

	if (mode == 3)
	{

	    try
	    {
		//file must be same as before
		BufferedReader b = new BufferedReader (new FileReader ("highscoresTimed.txt"));

		String line = b.readLine ();
		while (line != null)
		{
		    count++;
		    line = b.readLine ();
		}
	    }


	    catch (Exception e)

	    {
		c.println ("Error opening file");

	    }
	}
	else
	{
	    try
	    {
		//file must be same as before
		BufferedReader b = new BufferedReader (new FileReader ("highscoresUntimed.txt"));

		String line = b.readLine ();
		while (line != null)
		{
		    count++;
		    line = b.readLine ();
		}
	    }


	    catch (Exception e)

	    {
		c.println ("Error opening file");
	    }
	}


	return count;
    }


    public void turnSwitch (int whoPlays, String name)
    {
	title ();
	c.setFont (Main);
	c.drawString (name + "'s turn (Player " + whoPlays + ")", 200, 200);
	c.drawString ("Press any key to start", 200, 270);
	c.getChar ();
    }


    public void display (int whoPlays, int Mode)
    {
	title ();
	//timer ();
	c.setFont (new Font ("SansSerif", Font.BOLD, 27));
	//c.drawString ("Board", 110, 80);

	// Outline for the Board
	int xPos = 50;
	int yPos = 140;
	int borderWidth = 5;
	c.setColor (Color.BLACK);
	c.fillRect (xPos - 18 - borderWidth, yPos - 40 - borderWidth, 255 + borderWidth * 2, 255 + borderWidth * 2);
	// Board Background
	c.setColor (tile);
	c.fillRect (xPos - 18, yPos - 40, 255, 255);
	// board borders
	c.setColor (Color.BLACK);
	for (int i = 0 ; i < 3 ; i++)
	{
	    c.fillRect (xPos - 18 + 61 + 63 * i, yPos - 40, borderWidth, 255);
	}
	for (int i = 0 ; i < 3 ; i++)
	{
	    c.fillRect (xPos - 18, yPos - 40 + 63 + 63 * i, 255, borderWidth);
	}


	// Displaying the board

	c.setFont (new Font ("SansSerif", Font.PLAIN, 30));
	for (int r = 0 ; r < board.length ; r++)
	{
	    for (int col = 0 ; col < board [0].length ; col++)
	    {
		c.setColor (Color.BLACK);
		c.drawString (board [r] [col] + "", xPos, yPos);
		xPos += 65;

	    }

	    xPos = 50;
	    yPos += 65;
	}
	c.setFont (Norm);
	c.drawString ("Enter <1> to end", 30, 485);

	// Displaying user input section and the wrds
	askData ();
    }


    public void askData ()
    {
	Timer t = new Timer (c);
	boolean run = true;
	if (menuOption == '3')      // timed mode
	{
	    t.start ();     //starts the thread
	}

	while (run)
	{
	    if (menuOption == '3')
	    {
		run = t.isAlive ();
	    }
	    // ERasing
	    c.setColor (bg);
	    c.fillRect (50, 370, 500, 30);
	    c.setColor (Color.BLACK);
	    c.setFont (new Font ("SansSerif", Font.BOLD, 20));
	    c.drawString ("Enter Your Word: ", 30, 400);
	    c.setCursor (20, 30);
	    String errorMsg = "";
	    String word = "";
	    // Get User Input for the Word
	    char tempChar = ' ';
	    while (true)
	    {
		tempChar = c.getChar ();
		if (menuOption == '3' && !t.isAlive () && tempChar == ' ')
		{
		    break;
		}
		else if (menuOption == '3' && !t.isAlive ())
		{
		    continue;
		}
		if (tempChar == '\n')
		{
		    break;
		}
		if (tempChar == '\b')   // backspace: removes last character of the word
		{
		    String tempWord = word;
		    word = "";
		    for (int i = 0 ; i < tempWord.length () - 1 ; i++)
		    {
			word += tempWord.charAt (i);
		    }
		    c.setColor (bg);
		    c.fillRect (220, 375, 400, 50);
		}
		else
		{
		    word += tempChar;
		}
		c.setColor (Color.BLACK);
		c.setFont (new Font ("SansSerif", Font.PLAIN, 20));
		c.drawString (word, 220, 400);
	    }
	    //String word = c.readLine ();
	    word = word.toUpperCase ();

	    // Exit code
	    if (word.equals ("1")) // && menuOption == '4')
	    {
		if (menuOption == '3')
		{
		    t.stop ();
		}
		break;
	    }
	    if (!t.isAlive () && menuOption == '3')
	    {
		break;
	    }
	    try
	    {
		if (word == "")
		{
		    errorMsg = "nothing entered";
		    throw new Exception ("No word");
		}

		else if (word.length () <= 2)        // if the word is 2 or less characters long
		{
		    errorMsg = word.toLowerCase () + " is too short";
		    throw new Exception ("Word too short");
		}
		else if (!wordInDict (word))    // if the word isn't in dictionary
		{
		    errorMsg = word.toLowerCase () + " is not a valid word";
		    throw new Exception ("Word not in dictionary");
		}
		else if (!findWords (board, word))
		{
		    errorMsg = word.toLowerCase () + " was not found on the board";
		    throw new Exception ("Word not in board");
		}

		// If the word is valid

		// Erasing
		c.setColor (bg);
		c.fillRect (30, 360, 500, 100);

		c.setColor (Color.GREEN);
		c.setFont (new Font ("SansSerif", Font.BOLD, 25));
		c.drawString (word.toLowerCase () + " is good!", 30, 440);

		// Add to good word to array
		if (turn == 1)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (word.equals (p1Words [i]))
			{
			    errorMsg = word.toLowerCase () + " is was already found";
			    throw new Exception ("Word already found");
			}
			if (p1Words [i] == null)
			{
			    break;
			}
		    }
		    p1Words [p1TotalWords] = word;
		    p1TotalWords += 1;
		    int xTemp = 0;
		    int yTemp = 50;
		    c.setFont (new Font ("SansSerif", Font.PLAIN, 30));
		    c.setColor (bg);
		    c.fillRect (0 + xTemp, yTemp + 0, 200, 30);
		    c.setColor (Color.BLACK);
		    c.drawString ("Points: " + calcPoints (p1Words), 30 + xTemp, 30 + yTemp);
		}
		else if (turn == 2)
		{

		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (word.equals (p2Words [i]))
			{
			    errorMsg = word.toLowerCase () + " is was already found";
			    throw new Exception ("Word already found");
			}
			if (p2Words [i] == null)
			{
			    break;
			}
		    }
		    p2Words [p2TotalWords] = word;
		    p2TotalWords += 1;
		    int xTemp = 0;
		    int yTemp = 50;
		    c.setFont (new Font ("SansSerif", Font.PLAIN, 30));
		    c.setColor (bg);
		    c.fillRect (0 + xTemp, yTemp + 0, 200, 30);
		    c.setColor (Color.BLACK);
		    c.drawString ("Points: " + calcPoints (p2Words), 30 + xTemp, 30 + yTemp);
		}

		// Display the good words
		int yCounter = 110;
		int xCounter = 300;
		int wordCounter = 0;
		String currentWord = "";
		c.setColor (Color.GREEN);
		for (int index = 0 ; index <= 100 ; index++)
		{
		    if (turn == 1)
		    {
			currentWord = p1Words [index];
		    }
		    else if (turn == 2)
		    {
			currentWord = p2Words [index];
		    }
		    if (currentWord == null)
		    {
			break;
		    }
		    wordCounter += 1;
		    c.setFont (new Font ("SansSerif", Font.BOLD, 19));
		    if (wordCounter == 11)
		    {
			xCounter += 90;
			yCounter = 110;
		    }
		    else if (wordCounter == 21)
		    {
			xCounter += 90;
			yCounter = 110;
		    }
		    else if (wordCounter == 31)
		    {
			xCounter += 90;
			yCounter = 110;
		    }
		    c.setColor (Color.GREEN);
		    c.drawString (currentWord, xCounter, yCounter);
		    yCounter += 25;
		}

	    }
	    catch (Exception e)
	    {
		// ERasing
		c.setColor (bg);
		c.fillRect (20, 360, 800, 100);
		c.setColor (Color.RED);
		c.setFont (new Font ("SansSerif", Font.BOLD, 25));
		c.drawString (errorMsg, 30, 440);
	    }
	}
    }


    public void askName ()
    {
	title ();
	c.setColor (Color.BLACK);
	c.fillRect (100 - 10, 120 - 10, 430 + 20, 190 + 20);
	c.setColor (tile);
	c.fillRect (100, 120, 430, 190);
	c.setColor (Color.BLACK);
	c.setFont (Main);
	c.drawString ("Player's Usernames", 130, 170);
	// Player 1 name
	c.setFont (new Font ("SansSerig", Font.PLAIN, 25));

	do
	{
	    String msg = "";

	    try
	    {
		c.setColor (Color.BLACK);
		c.drawString ("Player 1:", 130, 220);
		c.setCursor (11, 33);
		name1 = c.readLine ();
		c.fillRect (530, 120, 10, 200);
		c.setColor (bg);
		c.fillRect (540, 120, 100, 200);
		if (name1.length () > 10)
		{
		    msg = "Name cannot be more than 10 characters";
		    throw new Exception ("Name cannot be more than 10 characters");
		}
		if (name1.equals (""))
		{
		    msg = "Name cannot be blank";
		    throw new Exception ("Name cannot be blank");
		}
		break;
	    }
	    catch (Exception e)
	    {
		c.setColor (bg);
		c.fillRect (100 - 100, 120 - 10, 90, 190 + 20);
		c.setColor (Color.BLACK);
		c.fillRect (90, 120 - 10, 10, 190 + 20);
		c.setColor (tile);
		c.fillRect (250, 200, 280, 30);
		new Message (msg);
	    }
	}
	while (true);


	// Player 2 name
	while (true)
	{
	    String msg = "";
	    try
	    {
		c.setColor (Color.BLACK);
		c.drawString ("Player 2:", 130, 260);
		c.setCursor (13, 33);
		name2 = c.readLine ();
		c.fillRect (530, 120, 10, 200);
		c.setColor (bg);
		c.fillRect (540, 120, 100, 200);
		if (name2.length () > 10)
		{
		    msg = "Name cannot be more than 10 characters";
		    throw new Exception ("Name cannot be more than 10 characters");
		}
		if (name2.equals (""))
		{
		    msg = "Name cannot be blank";
		    throw new Exception ("Name cannot be blank");
		}
		break;
	    }
	    catch (Exception e)
	    {
		c.setColor (bg);
		c.fillRect (100 - 100, 120 - 10, 90, 190 + 20);
		c.setColor (Color.BLACK);
		c.fillRect (90, 120 - 10, 10, 190 + 20);
		c.setColor (tile);
		c.fillRect (250, 240, 280, 30);
		new Message (msg);
	    }
	}
    }


    public void winner ()
    {
	Color balloons = new Color (154, 54, 255);

	title ();
	c.setColor (Color.BLACK);
	c.setFont (Norm);
	c.drawString (name1 + " has " + calcPoints (p1Words) + " points", 200, 150);
	c.drawString (name2 + " has " + calcPoints (p2Words) + " points", 200, 200);
	if (calcPoints (p1Words) > calcPoints (p2Words))
	{
	    c.drawString (name1 + " WINS", 250, 250);
	}


	else if (calcPoints (p1Words) < calcPoints (p2Words))
	{
	    c.drawString (name2 + " WINS", 250, 250);
	}


	else
	{
	    c.drawString ("IT'S A TIE", 250, 250);
	}

	c.setFont (Norm);
	c.drawString ("Press any key to", 200, 370);
	c.drawString ("go back to main manu", 200, 400);


	points1 = calcPoints (p1Words);
	points2 = calcPoints (p2Words);


	//animation

	for (int balloonY = 500 ; balloonY > 10 ; balloonY--)
	{
	    //left balloon
	    c.setColor (bg);
	    c.fillRect (98, balloonY + 196, 4, 204);
	    c.fillOval (48, balloonY - 1, 105, 205);
	    c.setColor (balloons);
	    c.fillOval (50, balloonY, 100, 200); //balloon part
	    c.setColor (Color.black);
	    c.drawLine (100, balloonY + 198, 100, balloonY + 300); //rope

	    //right balloon

	    c.setColor (bg);
	    c.drawRect (550, balloonY + 196, 4, 204);
	    c.fillOval (498, balloonY - 1, 105, 205);
	    c.setColor (balloons);
	    c.fillOval (500, balloonY, 100, 200); //ballon part
	    c.setColor (Color.black);
	    c.drawLine (550, balloonY + 198, 550, balloonY + 300); //rope

	    try
	    {
		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }


	}


	c.getChar ();
    }


    public void goodbye ()
    {
	title ();
	int xtemp = 0;
	int ytemp = 120;
	int border = 10;
	c.setFont (Main);
	c.setColor (Color.BLACK);
	c.fillRect (65 + xtemp - border, 40 + ytemp - border, 500 + border * 2, 200 + border * 2);
	c.setColor (tile);
	c.fillRect (65 + xtemp, 40 + ytemp, 500, 200);
	c.setColor (Color.BLACK);
	c.drawString ("Thanks for playing!", 100 + xtemp, 100 + ytemp);
	c.drawString ("Created by: Charlie and Dana", 100 + xtemp, 150 + ytemp);
	c.drawString ("Date: January 18th, 2023", 100 + xtemp, 200 + ytemp);

	try
	{
	    Thread.sleep (2000);
	}
	catch (Exception e)
	{
	}
    }


    public void createBoard ()
    {
	// Generates a random board using the 15 dice (from the real boggle game)
	int diceCounter = 0;

	char[] di0 = {'R', 'I', 'F', 'O', 'B', 'X'};
	char[] di1 = {'I', 'F', 'E', 'H', 'E', 'Y'};
	char[] di2 = {'D', 'E', 'N', 'O', 'W', 'S'};
	char[] di3 = {'U', 'T', 'O', 'K', 'N', 'D'};
	char[] di4 = {'H', 'M', 'S', 'R', 'A', 'O'};
	char[] di5 = {'L', 'U', 'P', 'E', 'T', 'S'};
	char[] di6 = {'A', 'C', 'I', 'T', 'O', 'A'};
	char[] di7 = {'Y', 'L', 'G', 'K', 'U', 'E'};
	char[] di8 = {'R', 'B', 'M', 'J', 'O', 'A'};
	char[] di9 = {'E', 'H', 'I', 'S', 'P', 'N'};
	char[] di10 = {'V', 'E', 'T', 'I', 'G', 'N'};
	char[] di11 = {'B', 'A', 'L', 'I', 'Y', 'T'};
	char[] di12 = {'E', 'Z', 'A', 'V', 'N', 'D'};
	char[] di13 = {'R', 'A', 'L', 'E', 'S', 'C'};
	char[] di14 = {'U', 'W', 'I', 'L', 'R', 'G'};
	char[] di15 = {'P', 'A', 'C', 'E', 'M', 'D'};

	char[] [] allDice = {di0, di1, di2, di3, di4, di5, di6, di7, di8, di9, di10, di11, di12, di13, di14, di15};
	c.setFont (Title);
	for (int r = 0 ; r < board.length ; r++)
	{
	    for (int col = 0 ; col < board [0].length ; col++)
	    {
		board [r] [col] = allDice [diceCounter] [((int) Math.floor (Math.random () * (0 - 7 + 1) + 1) * -1)] + "";
		diceCounter++;
	    }
	}
    }


    public boolean wordInDict (String word)
    {
	String lines = "";
	//int numLines = 0;
	String line;

	try
	{


	    BufferedReader fileRead = new BufferedReader (new FileReader ("Collins Scrabble Words (2019).txt"));

	    while ((lines = fileRead.readLine ()) != null) //read the file for as long as there are  text lines in the file
	    {
		if (lines.equals (word))
		{
		    return true;
		}
	    }
	    fileRead.close ();

	    return false;

	}
	catch (FileNotFoundException ex)
	{
	    System.out.println ("This file does not exist"); //your message to the user
	    System.err.println (ex.getMessage ());            //specifies the file not found
	    return false;
	}
	catch (IOException ex)
	{
	    System.out.println ("Problem reading file"); // your message to the user
	    System.err.println (ex.getMessage ());            //specifies the error
	    return false;
	}
    }


    public void funcFindWords (String[] [] board, boolean[] [] visited, int i, int j, String str, String word)
    {
	visited [i] [j] = true;
	str = str + board [i] [j];
	if (str.equals (word))
	{
	    wordInBoard = true;
	    return;
	}
	int row = i - 1;
	while (row <= i + 1 && row < 4)
	{

	    int col = j - 1;
	    while (col <= j + 1 && col < 4)
	    {
		if (row >= 0 && col >= 0 && visited [row] [col] == false)
		{
		    funcFindWords (board, visited, row, col, str, word);
		}
		col += 1;
	    }
	    row += 1;
	}
	visited [i] [j] = false;
	str = str.charAt (str.length () - 1) + "";  // Erase current character from string
    }


    public boolean findWords (String[] [] board, String word)
    {
	wordInBoard = false;
	boolean[] [] visited = {{false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false}};
	String str = "";    // Current string
	for (int i = 0 ; i < 4 ; i++)
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {
		if (board [i] [j].equals (word.charAt (0) + "") == false)
		{
		    continue;
		}
		funcFindWords (board, visited, i, j, str, word);
		if (wordInBoard == true)
		{
		    return true;
		}
	    }
	}
	return false;
    }


    public int calcPoints (String[] words)
    {
	int pts = 0;
	for (int i = 0 ; i < words.length ; i++)
	{
	    String currentWord = words [i];
	    if (words [i] == null)
	    {
		break;
	    }
	    if (currentWord.length () <= 4)   // 3 or 4 letter points are worth 1 point
	    {
		pts += 1;
	    }
	    else if (currentWord.length () <= 5)  // 5 letter words are worth 2 points
	    {
		pts += 2;
	    }
	    else if (currentWord.length () <= 6)  // 6 letter words are worth 3 points
	    {
		pts += 3;
	    }
	    else if (currentWord.length () <= 7)  // 5 letter words are worth 5 points
	    {
		pts += 5;
	    }
	    else if (currentWord.length () <= 8)  // 8 letter words are worth 11 points
	    {
		pts += 11;
	    }
	    else                                  // 9 letter words or longer are worth 2 points per letter
	    {
		pts += currentWord.length () * 2;
	    }
	}
	return pts;
    }


    public static void main (String[] args)
    {
	Boggle b = new Boggle ();
	c.setTextBackgroundColor (tile);
	b.splashscreen ();
	while (true)
	{
	    b.mainMenu ();
	    // Resetting variables
	    turn = 1;
	    p1Words = new String [100];
	    p2Words = new String [100];
	    p1TotalWords = 0;
	    p2TotalWords = 0;

	    if (menuOption == '0')
	    {
		b.instructions ();
	    }
	    if (menuOption == '1')
	    {
		b.highScore (3);
	    }
	    if (menuOption == '2')
	    {
		b.highScore (4);
	    }
	    if (menuOption == '3')
	    {
		level = 1;
		b.askName ();
		b.createBoard ();
		turn = 1;
		b.turnSwitch (turn, name1);
		b.display (turn, level);
		turn = 2;
		b.turnSwitch (turn, name2);
		b.display (turn, level);
		b.winner ();
		b.setScores (3);
	    }
	    if (menuOption == '4')
	    {
		level = 2;
		b.askName ();
		b.createBoard ();
		turn = 1;
		b.turnSwitch (turn, name1);
		b.display (turn, level);
		turn = 2;
		b.turnSwitch (turn, name2);
		b.display (turn, level);
		b.winner ();
		b.setScores (4);
	    }
	    if (menuOption == '5')
	    {
		b.goodbye ();
		c.close ();
		break;
	    }
	}
    }


    // Place your program here.  'c' is the output console
} // main method


// Boggle class
