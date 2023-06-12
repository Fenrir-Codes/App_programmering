package dk.tec.jaj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyseRequest {
	Match level;
	int id;
	
	public Match getLevel() {
		return level;
	}

	public void setLevel(Match level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public AnalyseRequest(String pathinfo)
	{
		Matcher match = Pattern.compile("/Elev/([0-9]+)").matcher(pathinfo);
		
		if(match.find())
		{
			level = Match.MatchElevid;
			id = Integer.parseInt(match.group(1));
		}
		else
		{
			match = Pattern.compile("/Elev").matcher(pathinfo);
			
			if(match.find())
			{
				level = Match.MatchElevid;
				
			}
			else 
			{
				level = Match.NoMatch;
			}
		}
			
	}

}
