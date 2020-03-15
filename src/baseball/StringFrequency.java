package baseball;

public class StringFrequency {
	private int[] frequencies;
	private String word;
	
	public StringFrequency(String word)
	{
		this.word=word;
		frequencies= new int[26];
		word=word.toUpperCase();
		for(int i=0;i<this.word.length(); i++)
		{
			if(word.charAt(i)!=' ')
				frequencies[(int)word.charAt(i)-65]++;
		}
	}
	
	public StringFrequency(StringFrequency other)
	{
		this(other.word);
	}
	
	public String getWord()
	{
		return word;
	}
	public boolean hasSameFrequency(StringFrequency other)
	{
		for(int i=0; i<frequencies.length;i++)
		{
			if(frequencies[i]!=other.frequencies[i])
				return false;
		}
		return true;
	}
}
