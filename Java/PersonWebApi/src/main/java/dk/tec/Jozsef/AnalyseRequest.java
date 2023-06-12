package dk.tec.Jozsef;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyseRequest {
	MatchPerson Person;
	int id;
	
	public MatchPerson getPerson() {
		return Person;
	}

	public void setPerson(MatchPerson Person) {
		this.Person = Person;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public AnalyseRequest(String pathinfo)
	{
		Matcher pattern = Pattern.compile("/Person/([0-9]+)").matcher(pathinfo);
		
		if(pattern.find())
		{
			Person = MatchPerson.MatchPersonId;
			id = Integer.parseInt(pattern.group(1));
		}
		else
		{
			pattern = Pattern.compile("/Person").matcher(pathinfo);
			
			if(pattern.find())
			{
				Person = MatchPerson.MatchPerson;
				
			}
			else 
			{
				Person = MatchPerson.NoMatch;
			}
		}
			
	}

}
