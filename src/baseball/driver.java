package baseball;

import java.util.ArrayList;

public class driver {

	public static void main(String[] args) {
		
		League l=new League("players.txt");
		l.alphabetizedList();
		ArrayList<Player> play=l.findGoodBatters();
		System.out.println("GOOD BATTERS------------------------------------");
		for(int i=0;i<play.size();i++)
			System.out.println(play.get(i));
		System.out.println("GOOD BATTERS------------------------------------");
		System.out.println("ALL--------------------------------");
		for(int i=0;i<l.getPlayers().size();i++)
		{
			if(l.getPlayers().get(i) instanceof Pitcher) {
			System.out.println(l.getPlayers().get(i));
			}
		}
		//System.out.println(l);
		//l.writeToFile();
		Search s= new Search();
	}

}
