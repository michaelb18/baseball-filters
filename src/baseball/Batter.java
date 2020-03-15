package baseball;

public class Batter extends Player{
	private int homeruns;
	private int rbis;
	private double ba;
	private int sb;
	private int r;
	public  Batter(int runs, int hr, int rbi, double avg, String name,int stolenBases){
		super(name);
		if(hr<0)
			throw new IllegalArgumentException("hr<0");
		if(rbi<0)
			throw new IllegalArgumentException("rbi<0");
		if(avg<0)
			throw new IllegalArgumentException("avg<0");
		if(stolenBases<0)
			throw new IllegalArgumentException("sb<0");
		homeruns=hr;
		rbis=rbi;
		ba=avg;
		sb=stolenBases;
		r=runs;
	}


	
	public int getHR()
	{
		return homeruns;
	}
	public int getRBIS()
	{
		return rbis;
	}
	public double getBA()
	{
		return ba;
	}
	public int getSB()
	{
		return sb;
	}
	public int getR()
	{
		return r;
	}
	public boolean isGood()
	{
		return (r>50&&(homeruns<25&&homeruns>10)&&(rbis<90&&rbis>60)&&ba>.250)&&(sb>2&&sb<25);//note read in steals
	}
	public String toString()
	{
		return String.format("%10s, AVG:%.3f HR:%d RBI:%d SB:%d R:%d", name,ba,homeruns,rbis,sb,r);
	}
}
