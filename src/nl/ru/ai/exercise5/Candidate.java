package nl.ru.ai.exercise5;

public class Candidate
{
  public Attempt attempt;
  public int parentCandidate;
  public int[][] puzzle;
  
  public Candidate(Attempt attempt, int parentCandidate)
  {
    this.attempt=attempt;
    this.parentCandidate=parentCandidate;
  }

public Candidate(int[][] puzzle, int parentCandidate2) 
	{
		this.puzzle=puzzle;
		this.parentCandidate=parentCandidate2;
	}
}
