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
	public static void solve(int[][] puzzle)
	{
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
		    	System.out.print(found);
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
	
	
	static boolean goNorth(CandidateSlide currentCandidate) 
	{
		return currentCandidate.AttemptSlide.zeroCoordinate>=Puzzle.WIDTH;
	}
	
	static boolean goSouth(CandidateSlide currentCandidate) 
	{
		return currentCandidate.AttemptSlide.zeroCoordinate<(Puzzle.WIDTH*(Puzzle.HEIGHT-1));
	}
	
	static boolean goWest(CandidateSlide currentCandidate) 
	{
		return currentCandidate.AttemptSlide.zeroCoordinate%Puzzle.WIDTH!=0;
	}
	
	static boolean goEast(CandidateSlide currentCandidate) 
	{
		return (currentCandidate.AttemptSlide.zeroCoordinate+1)%Puzzle.WIDTH!=0;
	}
	
	private static void addNewCandidate(ArrayList<CandidateSlide> candidates, int c, Direction direction) 
	{
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
