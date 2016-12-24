package nl.ru.ai.exercise5;

import java.util.ArrayList;
import java.util.Arrays;

public class Sliding_puzzle 
{
	
	public static void main(String[] arguments)
	  {
		int[][] begin = Puzzle.board;
		solve(begin);
	  }
	public static void solve(int[][] puzzle)
	{
	    ArrayList<Candidate> candidates=new ArrayList<Candidate>();
	    boolean found=false;
	    int c=0;
	    Candidate first = new Candidate(puzzle,0);
	    candidates.add (first) ; 
	    while(c<candidates.size() & !found)
	    {
	    	Candidate currentCandidate = candidates.get(c);
	    	if (Puzzle.puzzleReady(puzzle))
	    	{ 
		    	showPath (candidates, c);
		    	found=true; 
	    	}
	    	else
	    	{
	    	}
	    }
	}
	
	private static void showPath(ArrayList<Candidate> candidates, int c) 
	{
		assert candidates!=null: "ArrayList should be initialzed";
		assert c>=0&&c<candidates.size():"Invalid value for c";
		if(c==0)
		{
			System.out.println(Arrays.deepToString(candidates.get(c).puzzle));
			System.out.println("This is the shortest path.");
		}
		else
		{
			System.out.println(Arrays.deepToString(candidates.get(c).puzzle));
			showPath(candidates,candidates.get(c).parentCandidate);
		}
		
	}
}
