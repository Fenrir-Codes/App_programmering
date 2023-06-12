package dk.tec.Jozsef;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBTools {

	private Connection con;
	private Statement stmt;
	
	String connectionString = "jdbc:sqlserver://localhost; databaseName=PersonDB;encrypt=true;trustServerCertificate=true";
	
	private void connect() {
		
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
			System.out.print("Noget");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Person getPersonById(int id) {
		
		connect();
		Person person = new Person();
		String selectStr = "Select * from Person where Id = " + id;
		
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			
			if (result.next()) {
				person.setId(result.getInt("Id"));
				person.setName(result.getString("Name"));
				person.setJob(result.getString("Job"));
				person.setTlf(result.getInt("Tlf"));
				person.setHairColor(result.getInt("HairColor"));
				person.setFavorit(result.getBoolean("Favorit"));
				person.setPet(result.getInt("Pet"));
				
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return person;		
	}
	
	//get all
	public List<Person> getAllPersons() {
		
		connect();
		List<Person> personList = new ArrayList<>();
		String selectStr = "SELECT * FROM Person";
		
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			
			while (result.next()) {
				Person person = new Person();
				person.setId(result.getInt("Id"));
				person.setName(result.getString("Name"));
				person.setJob(result.getString("Job"));
				person.setTlf(result.getInt("Tlf"));
				person.setHairColor(result.getInt("HairColor"));
				person.setFavorit(result.getBoolean("Favorit"));
				person.setPet(result.getInt("Pet"));
				personList.add(person);				
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return personList;
	}

	
	//create new
	public void addPerson(Person person) {
		
		connect();
		String insertStr = "INSERT INTO Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES (?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement statement = con.prepareStatement(insertStr)){

			statement.setString(1, person.getName());
			statement.setString(2, person.getJob());
			statement.setInt(3, person.getTlf());
			statement.setInt(4, person.getHairColor());
			statement.setBoolean(5, person.getFavorit());
			statement.setInt(6, person.getPet());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//delete
	public void deletePersonById(int id) {
		connect();
		String deleteStr = "DELETE FROM Person WHERE Id = ?";
		
		try (PreparedStatement statement = con.prepareStatement(deleteStr)){
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	//update
	public void updatePerson(Person person) {
		connect();
		String updateStr = "UPDATE Person SET Name = ?, Job = ?, Tlf = ?, HairColor = ?, Favorit = ?, Pet = ? WHERE Id = ?";
		
		try (PreparedStatement statement = con.prepareStatement(updateStr)){
			statement.setString(1, person.getName());
			statement.setString(2, person.getJob());
			statement.setInt(3, person.getTlf());
			statement.setInt(4, person.getHairColor());
	        statement.setBoolean(5, person.getFavorit());
	        statement.setInt(6, person.getPet());
	        statement.setInt(7, person.getId());
	        statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
