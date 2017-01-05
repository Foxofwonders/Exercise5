package nl.ru.ai.exercise5;

import java.util.Arrays;

public class Puzzle {

	static final int WIDTH = 3;
	static final int HEIGHT = 3;
	static int [][] board = 
		{
			{1,2,3},
			{4,5,6},
			{0,7,8}
		};
	
	static int findZero(int[][]puzzle)
	{
		int counter = 0;
		for(int row=0;row<HEIGHT;row++)
		{
			for(int col=0;col<WIDTH;col++)
			{
				if(puzzle[row][col]==0) return counter;
				counter++;
			}
		}
		return 0;
	}
	
	static boolean puzzleReady(int[][] puzzle)
	{
		int[][] puzzleDone =
			{
					{1,2,3},
					{4,5,6},
					{7,8,0}
			};
		return Arrays.deepEquals(puzzle, puzzleDone);
	}
}
