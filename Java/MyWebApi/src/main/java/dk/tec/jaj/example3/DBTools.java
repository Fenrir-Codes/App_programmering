package dk.tec.jaj.example3;

import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import dk.tec.jaj.Elev;

public class DBTools {
	
	Connection con;
	Statement stmt;
	ResultSet result;
	
	//(LocalDb)\\MSSQLLocalDB
	String connectionString = "jdbc:sqlserver://localhost; databaseName=MyApiDB;encrypt=true;trustServerCertificate=true";
	
	private void Connect() {
		try {
			//System.out.print("live");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//System.out.print("getconn");
			con = DriverManager.getConnection(connectionString,"sa", "Passw0rd");
			//System.out.print("createstate");
			stmt = con.createStatement();
			//System.out.print("live");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Elev getElevById(int id) {
		Connect();
		String sqlString = "SELECT * FROM Elev WHERE id=" +id;
		
		Elev elev = new Elev();
		
		try {			
			result = stmt.executeQuery(sqlString);
			
			if(result.next()) {
				elev.setId(result.getInt("Id"));
				elev.setName(result.getString("Name"));
				elev.setAge(result.getInt("Age"));
				elev.setJob(result.getString("Job"));
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return elev;
	}
	
	public List<Elev> getAllElev() {
		Connect();
		
		List<Elev> elever = new ArrayList<>();
		String selectStr = "SELECT * FROM Elev";
		
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			Elev elev = new Elev();
			
			while (result.next()) {
				
				elev.setId(result.getInt("Id"));
				elev.setName(result.getString("Name"));
				elev.setAge(result.getInt("Age"));
				elev.setJob(result.getString("Job"));
				
				elever.add(elev);				
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return elever;
	}
	
	public void addElev(Elev elev) {
		Connect();
		
		String insertStr = "INSERT INTO Elev (Id, Name, Age, Job) VALUES (?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement statement = con.prepareStatement(insertStr)){

			statement.setInt(1, elev.getId());
			statement.setString(2, elev.getName());
			statement.setInt(3, elev.getAge());
			statement.setString(4, elev.getJob());
			statement.executeUpdate(insertStr);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public void deleteElevById(int id) {
		Connect();
		
		String deleteStr = "DELETE FROM Elev WHERE Id =" +id; 
		
		try (PreparedStatement statement = con.prepareStatement(deleteStr)){
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateElev(Elev elev) {
		Connect();
		
		String updateStr = "UPDATE Elev SET Name = ?, Age = ?, Job = ? WHERE Id = ?";
		
		try (PreparedStatement statement = con.prepareStatement(updateStr)){
			statement.setInt(1, elev.getId());
			statement.setString(2, elev.getName());
			statement.setInt(3, elev.getAge());
			statement.setString(4, elev.getJob());
	        statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
