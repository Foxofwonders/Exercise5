package nl.ru.ai.exercise5;

public class AttemptSlide 
{
	 public int element1;
	  public int element2;
	  public int[] slides;
	  public AttemptSlide(int element1, int element2,int[] slides)
	  {
		int help=slides[element1];
	    this.slides[element1]=this.slides[element2];
	    this.slides[element2]=help;
	  }

}
