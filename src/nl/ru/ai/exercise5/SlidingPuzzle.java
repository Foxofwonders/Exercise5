package nl.ru.ai.exercise5;


import java.util.ArrayList;
import java.util.Arrays;

public class SlidingPuzzle 
{
	private final static int[] SOLUTION = {1,2,3,4,5,6,7,8,9,0};
	 public static void main(String[] arguments)
	  {
		int[] puzzle = {1,2,3,4,5,6,0,7,8};
	    ArrayList<CandidateSlide> candidates=new ArrayList<CandidateSlide>();
	    candidates.add(new CandidateSlide(new AttemptSlide (6,puzzle),0));
	    boolean found=false;
	    int c=0;
	    while(c<candidates.size() & !found)
	    {
	    	CandidateSlide currentSlideCandidate = candidates.get(c);
	    	if (currentSlideCandidate.AttemptSlide.zeroCoordinate==9)
	    	{ 
	    		if(Arrays.equals(currentSlideCandidate.AttemptSlide.slides, SOLUTION))
	    		{
		    	showPath (candidates, c);
		    	found=true; 
	    		}
	    		else
	    		
	    	}
	    	else
	    	{
	    		int direction = 0;
	    		/*
	    		 * west = 0
	    		 * east = 1
	    		 * north = 2
	    		 * south = 3
	    		 */
	    		
	    		//If it's possible to go west.
	    		if (currentSlideCandidate.AttemptSlide.zeroCoordinate-1>=0 && currentSlideCandidate.AttemptSlide.zeroCoordinate-1<=9)
	    		{
	    			direction = 0;
	    			addNewCandidates(candidates, c, direction);
	    		}
	    		//If it's possible to go east.
	    		if (!Maze.hasWall(currentSlideCandidate.attempt.row,currentSlideCandidate.attempt.col+1) && !hasVisited(currentSlideCandidate.attempt.row,currentSlideCandidate.attempt.col+1))
	    		{
	    			direction = "east";
	    			addNewCandidates(candidates, c, direction);
	    		}
				//If it's possible to go north.
	    		if (!Maze.hasWall(currentSlideCandidate.attempt.row-1,currentSlideCandidate.attempt.col) && !hasVisited(currentSlideCandidate.attempt.row-1,currentSlideCandidate.attempt.col))
	    		{
	    			direction = "north";
	    			addNewCandidates(candidates, c, direction);
	    		}
				//If it's possible to go south.
	    		if (!Maze.hasWall(currentSlideCandidate.attempt.row+1,currentSlideCandidate.attempt.col) && !hasVisited(currentSlideCandidate.attempt.row+1,currentSlideCandidate.attempt.col))
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
		private static void addNewCandidates(ArrayList<Candidate> candidates, int c, int direction)
		{
			assert candidates!=null: "ArrayList should be initialzed";
			assert c>=0&&c<candidates.size():"Invalid value for c";
			assert direction>=0 && direction<=3 :"Invalid value for direction";
			Candidate currentCandidate = candidates.get(c);
			if (direction==0)
			{
				Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row,currentCandidate.attempt.col-1),c);
				candidates.add(newCandidate);
			}
			if (direction==1)
			{
				Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row,currentCandidate.attempt.col+1),c);
				candidates.add(newCandidate);
			}
			if (direction==2)
			{
				Candidate newCandidate = new Candidate(new Attempt(currentCandidate.attempt.row-1,currentCandidate.attempt.col),c);
				candidates.add(newCandidate);
			}
			
			if (direction==3)
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
