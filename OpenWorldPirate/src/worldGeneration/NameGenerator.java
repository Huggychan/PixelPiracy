package worldGeneration;

import java.util.Random;

public class NameGenerator 
{
	static Random r = new Random();
	public static String captainName() 
	{
		String name="";
		switch(r.nextInt(8))
		{
		case 1:
		{
			name = "Ahab";
			break;
		}
		case 2:
		{
			name = "Bering";
			break;
		}
		case 3:
		{
			name = "Alexanderson";
			break;
		}
		case 4:
		{
			name = "Aruga";
			break;
		}
		case 5:
		{
			name = "Acac";
			break;
		}
		case 6:
		{
			name = "Barney";
			break;
		}
		case 7:
		{
			name = "Barron";
			break;
		}
		
		default:
			name = "Wood";
			break;
		}
		return "Captain "+ name;
	}
	
	public static String crewMemberName() 
	{
		return "Scumbag Jenkins";
	}

}
