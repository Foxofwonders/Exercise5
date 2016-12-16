package nl.ru.ai.exercise5;

import static nl.ru.ai.exercise5.Maze.*;

import java.util.ArrayList;

public class Exercise5
{
  public static void main(String[] arguments)
  {
    ArrayList<Candidate> candidates=new ArrayList<Candidate>();
    candidates.add(new Candidate(new Attempt(1,7),0));
    boolean found=false;
    int c=0;
    while(c<candidates.size() & !found)
    {
    	char[][] maze = Maze.maze;
    	Candidate currentCandidate = candidates.get(c);
    	if (Maze.hasRabbit(currentCandidate.attempt.row, currentCandidate.attempt.col))
    	{ 
	    	//showPath (candidates, c);
	    	found=true; 
    	}
    	else
    	{
    		found=false;
    	}
    	System.out.print(found);
    	c++;
    }
  }

	private static void showPath(ArrayList<Candidate> candidates, int c)
	{
		return;
	}
}
