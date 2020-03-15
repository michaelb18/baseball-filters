package baseball;

public class Pitcher extends Player{
	private int k;
	private double era;
	private int wins;
	private double ip;
	private int loss;
	private int saves;
	private double wip;
	public Pitcher(double ip,int ks,double er, int w, int l,String name,double whip, int s)
	{
		super(name);
		k=ks;
		era=er;
		wins=w;
		loss=l;
		this.ip=ip;
		saves=s;
		wip=whip;
	}
	
	public String toString()
	{
		return super.toString()+String.format("IP:%.1f W:%d L:%d ERA:%.2f WHIP:%.2f K:%d SV:%d",ip,wins,loss,era,wip,k,saves);
	}
	public int getk()
	{
		return k;
	}
	public double getERA()
	{
		return era;
	}
	public int getWins()
	{
		return wins;
	}
	public boolean isGood()
	{
		return ((k>155&&k<225)&&(era>3&&era<4.22)&&(wins>10)&&(wip<1.35));
	}
	
	public double getIP()
	{
		return ip;
	}
	
	public int getL()
	{
		return loss;
	}
	
	public int getS()
	{
		return saves;
	}
	public double getWHIP()
	{
		return wip;
	}
}
