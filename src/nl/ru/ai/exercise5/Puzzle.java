package nl.ru.ai.exercise5;

import java.util.Arrays;

public class Puzzle {

	static int [][] board = 
		{
			{1,2,3},
			{4,5,6},
			{0,7,8}
		};
	
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
