package baseball;
import java.util.*;

import java.io.*;
public class AnagramFinder {
	StringFrequency frequencies[];
	
	public AnagramFinder(String fileName)
	{
		Scanner infile=null;
		try
		{
			infile=new Scanner(new File(fileName));
		}catch(FileNotFoundException e)
		{
			System.out.println("File not found");
			System.exit(-1);
		}
		int count=0;
		while(infile.hasNextLine())
		{
			count++;
			infile.nextLine();
		}
		frequencies=new StringFrequency[count];
		infile=null;
		try
		{
			infile=new Scanner(new File(fileName));
		}catch(FileNotFoundException e)
		{
			System.out.println("File not found");
			System.exit(-1);
		}
		count=0;
		while(infile.hasNextLine())
		{
			frequencies[count]=new StringFrequency(infile.nextLine());
			count++;
		}
	}
	
	public boolean hasAnagrams(String word)
	{
		StringFrequency other= new StringFrequency(word);
		for(int i=0;i<frequencies.length;i++)
			if(frequencies[i].hasSameFrequency(other))
				return true;
		return false;
	}
}
