package dk.tec.Jozsef.PersonApi;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import dk.tec.Jozsef.AnalyseRequest;
import dk.tec.Jozsef.Person;
import dk.tec.Jozsef.DBTools;


//@WebServlet("/PersonApi")
public class PersonApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//get
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		AnalyseRequest analyze = new AnalyseRequest(request.getPathInfo());
		
		DBTools db = new DBTools();
		
		switch(analyze.getPerson())
		{
			case MatchPerson:
				List<Person> ListOfPersons = db.getAllPersons();
				out.print(mapper.writeValueAsString(ListOfPersons));
				break;
			case MatchPersonId:
				Person person = db.getPersonById(analyze.getId());
				out.print(mapper.writeValueAsString(person));
				break;
			case NoMatch:
				out.print("No match");
				response.setStatus(400);
				break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + analyze.getPerson());
		}
		
		
	}
	
	//create
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		super.doPost(request, response);		

		response.setCharacterEncoding("UTF-8");
		response.setStatus(200);
		
		BufferedReader reader = request.getReader();
		String jsonStr = reader.readLine();
		System.out.println(jsonStr);
		
		ObjectMapper mapper = new ObjectMapper();
		Person person = mapper.readValue(jsonStr, Person.class);
		
		DBTools db = new DBTools();
		db.addPerson(person);
	}
	
	
	//update
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		AnalyseRequest analyze = new AnalyseRequest(request.getPathInfo());

		
		switch (analyze.getPerson()) {
		case MatchPersonId:
			BufferedReader reader = request.getReader();
			String jsonStr = reader.readLine();			

			Person updatePerson = mapper.readValue(jsonStr, Person.class);
			updatePerson.setId(analyze.getId());
			
			DBTools db = new DBTools();
			db.updatePerson(updatePerson);
			
			out.print(mapper.writeValueAsString(updatePerson));
			break;
			
		case NoMatch: {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + analyze.getPerson());
		}
			
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	
	//delete
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		DBTools db = new DBTools();
		
		AnalyseRequest analyze = new AnalyseRequest(request.getPathInfo());
		
		switch (analyze.getPerson()) {
		case MatchPersonId:
			int id = analyze.getId();
			db.deletePersonById(id);
			response.setStatus(HttpServletResponse.SC_OK);
			break;

		default:
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}


}

