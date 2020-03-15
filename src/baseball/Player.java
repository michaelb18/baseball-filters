package baseball;

import java.util.ArrayList;

public abstract class Player {
	protected String name;

	public Player(String n/*, int hr, int rbi, double avg, int p*/)
	{
		/*if(hr<0)
			throw new IllegalArgumentException("hr<0");
		if(rbi<0)
			throw new IllegalArgumentException("rbi<0");
		if(avg<0)
			throw new IllegalArgumentException("avg<0");
		*/
		name=n;
	//	homeruns=hr;
	//	rbis=rbi;
	//	ba=avg;
		//price=p;
	}
	
	public abstract boolean isGood(); 
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return String.format("%10s", name);
	}
}
