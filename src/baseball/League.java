package baseball;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class League {
	private ArrayList<Player> players;
	
	public League(String file)
	{
		players=new ArrayList<Player>();
		Scanner infile= null;
		try{
			infile=new Scanner(new File(file));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file not found");
			System.exit(-1);
		}
		int counter=0;
		while(infile.hasNextLine())
		{
			counter++;
			infile.nextLine();
		}
			
		infile= null;
		try{
			infile=new Scanner(new File(file));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file not found");
			System.exit(-1);
		}
		System.out.println(counter);
		int count=0;
		while(count<counter/2)
		{
			//System.out.println(infile.hasNextLine()+"\n"+infile.hasNextInt());
			String name=infile.nextLine();
			System.out.println(name);
			//String name="bob";
			//System.out.println(name);
			//System.out.println(name.substring(name.length()-4,name.length()-3));
			if(count>786)
			{
				double ip=infile.nextDouble();
				int k=infile.nextInt();
				//System.out.println(infile.nextInt());
				int w=infile.nextInt();
				int s=infile.nextInt(); 
				double era=infile.nextDouble();
				double whip=infile.nextDouble();
				//zscoresum+=standardDeviation();
				infile.nextInt();
				for(int i=0;i<5;i++)
					infile.nextInt();
				//infile.nextInt();
				int l=infile.nextInt();
				players.add(new Pitcher(ip,k,era,w,l,name,whip,s/*,infile.nextInt()*/));
			}
			else
			{
			infile.nextInt();
			//System.out.println(infile.nextInt());
			int r=infile.nextInt();
			int hr=infile.nextInt();
			int rbis=infile.nextInt(); 
			int sb=infile.nextInt();
			double ba=infile.nextDouble();
			//zscoresum+=standardDeviation();
			infile.nextDouble();
			for(int i=0;i<5;i++)
				infile.nextInt();
			infile.nextDouble();
			players.add(new Batter(r,hr,rbis,ba,name,sb/*,infile.nextInt()*/));
			}
			if(count!=counter/2-1)
				infile.nextLine();
			count++;
		}
	}
	

	/*public int getPrice()
	{
		
	}
	private double standardDeviation()
	{
		
	}
	
	private double zHR()
	{
		double avg=meanHR();
		double sum=0;
		for(int i=0;i<players.size();i++)
		{
			sum+=Math.pow(players.get(i).getHomeruns()-avg, 2.0);
		}
		return Math.sqrt(sum);
	}
	private double zRBI()
	{
		double avg=meanRBI();
		double sum=0;
		for(int i=0;i<players.size();i++)
		{
			sum+=Math.pow(players.get(i).getRBIS()-avg, 2.0);
		}
		return Math.sqrt(sum);
	}
	private double meanHR()
	{
		int sum=0;
		for(int i=0;i<players.size();i++)
		{
			sum+=players.get(i).getHomeruns();
		}
		return(double)sum/players.size();
	}
	private double meanRBI()
	{
		int sum=0;
		for(int i=0;i<players.size();i++)
		{
			sum+=players.get(i).getRBIS();
		}
		return(double)sum/players.size();
	}
	
	private double xBA()
	{
		
	}*/
	public void writeToFile()
	{
		int counter1=0;
		try{
			FileWriter outfile= new FileWriter("results.txt");
			while(counter1<players.size())
			{
				outfile.write(players.get(counter1).toString());
				counter1++;
			}
			outfile.close();
		}
		catch(IOException e)
		{
			System.exit(0);
		}
		
	}
	public ArrayList<Player> findGoodBatters()
	{
		ArrayList<Player> toReturn=new ArrayList<Player>();
		for(int i=0;i<players.size();i++)
		{
			if(players.get(i).isGood())
			{
				toReturn.add(players.get(i));
			}
		}
		return toReturn;
	}
	
	public void alphabetizedList()
	{
		//Player[] p=(Player[])players.toArray();
		 int i, j, first;
		Player temp;  
	     for ( i = players.size()-1; i >=1 ; i -- )  
	     {
	          first = 0;   //initialize to subscript of first element
	          for(j = i; j >= 1; j --)   //locate smallest element between positions 1 and i.
	          {
	               if( players.get(j).getName().compareTo(players.get(first).getName()) < 0 )         
	                 first = j;
	          }
	          temp = players.get(first);  //swap smallest found with element in position i.
	          players.set(first, players.get(i));
	          players.set(i, temp);
	      }
	}
	
	public ArrayList<Player> getHits(String input)
	{
		ArrayList<Player> toReturn=new ArrayList<Player>();
		ArrayList<Player> examine=new ArrayList(players);
		for(int i=0;i<players.size();i++)
		{
			//examine.get(i)=new Player(examine.get(i).getName().toLowerCase());
			if(examine.get(i) instanceof Pitcher)
				examine.set(i,new Pitcher(((Pitcher)examine.get(i)).getIP(),((Pitcher) examine.get(i)).getk(),((Pitcher)examine.get(i)).getERA(),((Pitcher)examine.get(i)).getWins(),((Pitcher)examine.get(i)).getL(),examine.get(i).getName().toLowerCase(),((Pitcher)examine.get(i)).getWHIP(),((Pitcher)examine.get(i)).getS()));
			else if(examine.get(i) instanceof Batter)
				examine.set(i,new Batter(((Batter) examine.get(i)).getR(),((Batter) examine.get(i)).getHR(),((Batter)examine.get(i)).getRBIS(),((Batter)examine.get(i)).getBA(),examine.get(i).getName().toLowerCase(),((Batter)examine.get(i)).getSB()));
			input=input.toLowerCase();
			if(examine.get(i).getName().indexOf(input)!=-1)
				toReturn.add(players.get(i));
		}
		return toReturn;
	}

	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	public String toString()
	{
		ArrayList<Pitcher> pitch=new ArrayList<Pitcher>();
		ArrayList<Batter> bat=new ArrayList<Batter>();
		for(Player p:players)
		{
			if(p instanceof Pitcher)
				pitch.add((Pitcher)p);
			else if(p instanceof Batter)
				bat.add((Batter)p);
		}
		for(Pitcher p:pitch)
			System.out.println(p);
		/*for(Batter b:bat)
			System.out.println(b);*/
		return "";
	}
}
