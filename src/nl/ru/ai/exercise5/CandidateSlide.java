package nl.ru.ai.exercise5;

public class CandidateSlide 
{
	  public AttemptSlide attempt;
	  public int parentCandidate;
	  
	  public CandidateSlide(AttemptSlide attempt, int parentCandidate)
	  {
	    this.attempt=attempt;
	    this.parentCandidate=parentCandidate;
	  }

}
