package dk.tec.jaj.example3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import dk.tec.jaj.AnalyseRequest;
import dk.tec.jaj.Match;
import dk.tec.jaj.Elev;


//@WebServlet("/MyWebApi")
public class MyWebApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NoMatch = null;
	private static final String MatchElevid = null;

	//get
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		AnalyseRequest analyze = new AnalyseRequest(request.getPathInfo());
		
		DBTools db = new DBTools();
		
		switch(analyze.getLevel())
		{
			case MatchElevid:
				Elev elev = db.getElevById(analyze.getId());
				out.print(mapper.writeValueAsString(elev));
				break;
			case MatchElev:
				List<Elev> ElevList = db.getAllElev();
				out.print(mapper.writeValueAsString(ElevList));
				break;
			case NoMatch:
				out.print("No match");
				response.setStatus(400);
				break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + analyze.getLevel());
		}
		
		
	}
	
	
	//post add
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		super.doPost(request, response);		

		response.setCharacterEncoding("UTF-8");
		response.setStatus(200);
		
		BufferedReader reader = request.getReader();
		String jsonStr = reader.readLine();
		System.out.println(jsonStr);
		
		ObjectMapper mapper = new ObjectMapper();
		Elev elev = mapper.readValue(jsonStr, Elev.class);
		
		System.out.print("Name: " + elev.getName());
		
		DBTools db = new DBTools();
		db.addElev(elev);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		AnalyseRequest analyze = new AnalyseRequest(request.getPathInfo());

		
		switch (analyze.getLevel()) {
		case MatchElevid:
			BufferedReader reader = request.getReader();
			String jsonStr = reader.readLine();			

			Elev updateElev = mapper.readValue(jsonStr, Elev.class);
			updateElev.setId(analyze.getId());
			
			DBTools db = new DBTools();
			db.updateElev(updateElev);
			
			out.print(mapper.writeValueAsString(updateElev));
			break;
			
		case NoMatch: {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + analyze.getLevel());
		}
			
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		DBTools db = new DBTools();
		
		AnalyseRequest analyze = new AnalyseRequest(request.getPathInfo());
		
		switch (analyze.getLevel()) {
		case MatchElevid:
			int id = analyze.getId();
			db.deleteElevById(id);
			response.setStatus(HttpServletResponse.SC_OK);
			break;

		default:
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}


}
