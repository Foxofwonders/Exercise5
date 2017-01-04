package nl.ru.ai.exercise5;

public class CandidateSlide 
{
	  public AttemptSlide AttemptSlide;
	  public int parentCandidate;
	  
	  public CandidateSlide(AttemptSlide AttemptSlide, int parentCandidate)
	  {
	    this.AttemptSlide=AttemptSlide;
	    this.parentCandidate=parentCandidate;
	  }

}
