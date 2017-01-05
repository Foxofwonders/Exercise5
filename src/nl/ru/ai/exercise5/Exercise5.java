package nl.ru.ai.exercise5;

//By Anna Gansen and Denise van Baalen

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

  /**
   * Adds new Candidates to the ArrayList
   * @param candidates
   * @param c
   * @param direction
   */
	private static void addNewCandidates(ArrayList<Candidate> candidates, int c, String direction)
	{
		assert candidates!=null: "ArrayList should be initialzed";
		assert c>=0&&c<candidates.size():"Invalid value for c";
		assert direction!=null:"Invalid value for direction";
		Candidate currentCandidate = candidates.get(c);
		if (direction.equals("west"))
		{
			Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row,currentCandidate.attempt.col-1),c);
			candidates.add(newCandidate);
		}
		if (direction.equals("north"))
		{
			Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row-1,currentCandidate.attempt.col),c);
			candidates.add(newCandidate);
		}
		if (direction.equals("east"))
		{
			Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row,currentCandidate.attempt.col+1),c);
			candidates.add(newCandidate);
		}
		if (direction.equals("south"))
		{
			Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row+1,currentCandidate.attempt.col), c);
			candidates.add(newCandidate);
		}
	}

	/**
	 * Prints out shortest path
	 * @param candidates
	 * @param c
	 */
	private static void showPath(ArrayList<Candidate> candidates, int c)
	{
		assert candidates!=null: "ArrayList should be initialzed";
		assert c>=0&&c<candidates.size():"Invalid value for c";
		if(c==0)
		{
			System.out.println("( " + candidates.get(c).attempt.row + " / " + candidates.get(c).attempt.col + " )");
			System.out.println("This is the shortest path.");
		}
		else
		{
			System.out.println("( " + candidates.get(c).attempt.row + " / " + candidates.get(c).attempt.col + " )");
			showPath(candidates,candidates.get(c).parentCandidate);
		}
		
	}
}
