package dk.tec.jaj.example2;

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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		
		Elev E1 = new Elev(1000,"Martin",22, "McDonalds");
		
		List<Elev> elever = new ArrayList<Elev>();
		elever.add(new Elev(1001, "Marko", 19, "blsblablajob"));
		elever.add(new Elev(1002, "Julie", 40, "EjendomsService manager"));
		elever.add(new Elev(1003, "Jill",34, "Csumpalumpa"));
		elever.add(new Elev(1003, "John", 14, "Berakós Pista Bácsi"));
		ObjectMapper mapper = new ObjectMapper();
		
		AnalyseRequest analyze = new AnalyseRequest(request.getPathInfo());
		
		if(analyze.getLevel() == Match.MatchElevid) 
		{
			out.print("Match on Elev and Id: " + analyze.getId());
			String JsonStr = mapper.writeValueAsString(elever.get(analyze.getId()));
			out.print(JsonStr);
		}
		else if(analyze.getLevel() == Match.MatchElev) 
		{
			String JsonStr = mapper.writeValueAsString(elever);
			out.print("<br/>Match on Elev.");
			out.print(JsonStr);
		}
		else
		{
			out.print("No match!<br>");
		}
		
		
	}


}
