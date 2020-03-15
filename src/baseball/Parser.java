package baseball;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
	private String file;
	public Parser(String fName)
	{
		Scanner infile=null;
		try{
			infile=new Scanner(new File(fName));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file not found");
			System.exit(-1);
		}
		file="";
		while(infile.hasNextLine())
		{
			file+=infile.nextLine();
		}
	}
	public String parse(){
		String toReturn="";
		for(int c=0;c<file.length();c++)
		{
			if(file.charAt(c)=='(')
			{
				while(file.charAt(c)!=')')
				{
					c++;
				}
				c++;
				toReturn+='\n';
			}
			else if((file.charAt(c)>='A'&&file.charAt(c)<='Z')&&(file.charAt(c-1)>='0'&&file.charAt(c-1)<='9'))
				toReturn+='\n';
			
			toReturn+=file.charAt(c);
		}
		return toReturn;
	}
	
	public static void main(String[] arg)
	{
		Parser p=new Parser("players2.txt");
		System.out.println(p.parse());
	}
}
