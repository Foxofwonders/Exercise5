package nl.ru.ai.exercise5;

import java.util.ArrayList;
import java.util.Arrays;

public class Sliding_puzzle 
{
	public enum Direction {NORTH, SOUTH, EAST, WEST};
	
	public static void main(String[] arguments)
	  {
		int[][] begin = Puzzle.board;
		solve(begin);
	  }
	
	/** Solves the given (3x3) sliding puzzle
	 * @param puzzle
	 */
	public static void solve(int[][] puzzle)
	{
		assert puzzle!=null:"Puzzle should be initialised";
		
	    ArrayList<CandidateSlide> candidates=new ArrayList<CandidateSlide>();
	    boolean found=false;
	    int c=0;
	    CandidateSlide first = new CandidateSlide(new AttemptSlide(puzzle,Puzzle.findZero(puzzle)),0);
	    candidates.add (first); 
	    while(c<candidates.size() & !found)
	    {
	    	CandidateSlide currentCandidate = candidates.get(c);
	    	if (Puzzle.puzzleReady(currentCandidate.AttemptSlide.thisPuzzle))
	    	{ 
		    	showPath (candidates, c);
		    	found=true;
	    	}
	    	else
	    	{
	    		
	    		if((goNorth(currentCandidate))) addNewCandidate(candidates, c, Direction.NORTH);
	    		if((goSouth(currentCandidate))) addNewCandidate(candidates, c, Direction.SOUTH);
	    		if((goWest(currentCandidate))) addNewCandidate(candidates, c, Direction.WEST);
	    		if((goEast(currentCandidate))) addNewCandidate(candidates, c, Direction.EAST);
	    	}
	    	c++;
	    }
	}
	
	
	/** Checks if it's possible to move the zero (empty) north.
	 * @param currentCandidate
	 * @return
	 */
	static boolean goNorth(CandidateSlide currentCandidate) 
	{
		assert currentCandidate!=null:"currentCandidate must be declared.";
		return currentCandidate.AttemptSlide.zeroCoordinate>=Puzzle.WIDTH;
	}
	
	/**Checks if it's possible to move the zero (empty) south.
	 * @param currentCandidate
	 * @return
	 */
	static boolean goSouth(CandidateSlide currentCandidate) 
	{
		assert currentCandidate!=null:"currentCandidate must be declared.";
		return currentCandidate.AttemptSlide.zeroCoordinate<(Puzzle.WIDTH*(Puzzle.HEIGHT-1));
	}
	
	/**Checks if it's possible to move the zero (empty) west.
	 * @param currentCandidate
	 * @return
	 */
	static boolean goWest(CandidateSlide currentCandidate) 
	{
		assert currentCandidate!=null:"currentCandidate must be declared.";
		return currentCandidate.AttemptSlide.zeroCoordinate%Puzzle.WIDTH!=0;
	}
	
	/** Checks if it's possible to move the zero (empty) east.
	 * @param currentCandidate
	 * @return
	 */
	static boolean goEast(CandidateSlide currentCandidate) 
	{
		assert currentCandidate!=null:"currentCandidate must be declared.";
		return (currentCandidate.AttemptSlide.zeroCoordinate+1)%Puzzle.WIDTH!=0;
	}
	
	/**
	 * Adds a new candidate in the specified direction, if the candidate doesn't already exist.
	 * @param candidates
	 * @param c
	 * @param direction
	 */
	private static void addNewCandidate(ArrayList<CandidateSlide> candidates, int c, Direction direction) 
	{
		assert candidates!=null:"Arraylist cannot be empty";
		assert direction!=null:"Direction must be specified";
		
		CandidateSlide currentCandidate = candidates.get(c);
		int[][] newPuzzle = new int [Puzzle.HEIGHT][Puzzle.WIDTH];
		for(int i=0;i<Puzzle.HEIGHT;i++)
		{
			for(int j=0;j<Puzzle.WIDTH;j++)
			{
				newPuzzle[i][j]=currentCandidate.AttemptSlide.thisPuzzle[i][j];
			}
		}
		int zeroOldPuzzle = Puzzle.findZero(newPuzzle);
		
		
		if (direction==Direction.NORTH)
		{
			int zeroNewPuzzle=zeroOldPuzzle-Puzzle.WIDTH;
			int oldCoordinate = newPuzzle[zeroNewPuzzle/Puzzle.WIDTH][zeroNewPuzzle%Puzzle.WIDTH];
			newPuzzle[zeroNewPuzzle/Puzzle.WIDTH][zeroNewPuzzle%Puzzle.WIDTH]=0;
			newPuzzle[zeroOldPuzzle/Puzzle.WIDTH][zeroOldPuzzle%Puzzle.WIDTH]=oldCoordinate;
			
			
			for(int i=0; i<candidates.size(); i++)
			{
				if(Arrays.deepEquals(newPuzzle,candidates.get(i).AttemptSlide.thisPuzzle))
				{
					return;
				}
			}
			CandidateSlide newCandidateSlide = new CandidateSlide(new AttemptSlide(newPuzzle,zeroNewPuzzle),c);
			candidates.add(newCandidateSlide);
				
		}
		if (direction==Direction.SOUTH)
		{
			int zeroNewPuzzle=zeroOldPuzzle+Puzzle.WIDTH;
			int oldCoordinate = newPuzzle[zeroNewPuzzle/Puzzle.WIDTH][zeroNewPuzzle%Puzzle.WIDTH];
			newPuzzle[zeroNewPuzzle/Puzzle.WIDTH][zeroNewPuzzle%Puzzle.WIDTH]=0;
			newPuzzle[zeroOldPuzzle/Puzzle.WIDTH][zeroOldPuzzle%Puzzle.WIDTH]=oldCoordinate;
			CandidateSlide newCandidateSlide = new CandidateSlide(new AttemptSlide(newPuzzle,zeroNewPuzzle),c);
			for(int i=0; i<candidates.size(); i++)
			{
				if(Arrays.deepEquals(newPuzzle,candidates.get(i).AttemptSlide.thisPuzzle))
				{
					return;
				}
				else candidates.add(newCandidateSlide);
			}
		}
		if (direction==Direction.WEST)
		{
			int zeroNewPuzzle=zeroOldPuzzle-1;
			int oldCoordinate = newPuzzle[zeroNewPuzzle/Puzzle.WIDTH][zeroNewPuzzle%Puzzle.WIDTH];
			newPuzzle[zeroNewPuzzle/Puzzle.WIDTH][zeroNewPuzzle%Puzzle.WIDTH]=0;
			newPuzzle[zeroOldPuzzle/Puzzle.WIDTH][zeroOldPuzzle%Puzzle.WIDTH]=oldCoordinate;
			CandidateSlide newCandidateSlide = new CandidateSlide(new AttemptSlide(newPuzzle,zeroNewPuzzle),c);
			for(int i=0; i<candidates.size(); i++)
			{
				if(Arrays.deepEquals(newPuzzle,candidates.get(i).AttemptSlide.thisPuzzle))
				{
					return;
				}
				else candidates.add(newCandidateSlide);
			}
		}
		if (direction==Direction.EAST)
		{
			int zeroNewPuzzle=zeroOldPuzzle+1;
			int oldCoordinate = newPuzzle[zeroNewPuzzle/Puzzle.WIDTH][zeroNewPuzzle%Puzzle.WIDTH];
			newPuzzle[zeroNewPuzzle/Puzzle.WIDTH][zeroNewPuzzle%Puzzle.WIDTH]=0;
			newPuzzle[zeroOldPuzzle/Puzzle.WIDTH][zeroOldPuzzle%Puzzle.WIDTH]=oldCoordinate;
			CandidateSlide newCandidateSlide = new CandidateSlide(new AttemptSlide(newPuzzle,zeroNewPuzzle),c);
			for(int i=0; i<candidates.size(); i++)
			{
				if(Arrays.deepEquals(newPuzzle,candidates.get(i).AttemptSlide.thisPuzzle))
				{
					return;
				}
				else candidates.add(newCandidateSlide);
			}
		}
	}
	
	/** Shows how to get to the solution.
	 * @param candidates
	 * @param c
	 */
	private static void showPath(ArrayList<CandidateSlide> candidates, int c) 
	{
		assert candidates!=null: "ArrayList should be initialzed";
		assert c>=0&&c<candidates.size():"Invalid value for c";
		if(c==0)
		{
			System.out.println(Arrays.deepToString(candidates.get(c).AttemptSlide.thisPuzzle));
			System.out.println("This is the shortest path.");
		}
		else
		{
			System.out.println(Arrays.deepToString(candidates.get(c).AttemptSlide.thisPuzzle));
			showPath(candidates,candidates.get(c).parentCandidate);
		}
		
	}
}
