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
	    	showPath (candidates, c);
	    	found=true; 
    	}
    	else
    	{
    		String direction = "";
    		Maze.visited(currentCandidate.attempt.row, currentCandidate.attempt.col);
    		//If it's possible to go west.
    		if (!Maze.hasWall(currentCandidate.attempt.row,currentCandidate.attempt.col-1) && !hasVisited(currentCandidate.attempt.row,currentCandidate.attempt.col-1))
    		{
    			direction = "west";
    			addNewCandidates(candidates, c, direction);
    		}
    		//If it's possible to go east.
    		if (!Maze.hasWall(currentCandidate.attempt.row,currentCandidate.attempt.col+1) && !hasVisited(currentCandidate.attempt.row,currentCandidate.attempt.col+1))
    		{
    			direction = "east";
    			addNewCandidates(candidates, c, direction);
    		}
			//If it's possible to go north.
    		if (!Maze.hasWall(currentCandidate.attempt.row-1,currentCandidate.attempt.col) && !hasVisited(currentCandidate.attempt.row-1,currentCandidate.attempt.col))
    		{
    			direction = "north";
    			addNewCandidates(candidates, c, direction);
    		}
			//If it's possible to go south.
    		if (!Maze.hasWall(currentCandidate.attempt.row+1,currentCandidate.attempt.col) && !hasVisited(currentCandidate.attempt.row+1,currentCandidate.attempt.col))
    		{
    			direction="south";
    			addNewCandidates(candidates, c, direction);
    		}
    	}
    	c++;
    }
  }

	private static void addNewCandidates(ArrayList<Candidate> candidates, int c, String direction)
	{
		Candidate currentCandidate = candidates.get(c);
		if (direction.equals("west"))
		{
			Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row,currentCandidate.attempt.col-1),c+1);
			candidates.add(newCandidate);
		}
		if (direction.equals("north"))
		{
			Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row-1,currentCandidate.attempt.col),c+1);
			candidates.add(newCandidate);
		}
		if (direction.equals("east"))
		{
			Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row,currentCandidate.attempt.col+1),c+1);
			candidates.add(newCandidate);
		}
		if (direction.equals("south"))
		{
			Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row+1,currentCandidate.attempt.col), c+1);
			candidates.add(newCandidate);
		}
	}

	private static void showPath(ArrayList<Candidate> candidates, int c)
	{
		return;
	}
}
