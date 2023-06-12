package dk.tec.jaj;

import java.sql.ResultSet;

public class Elev 
{

	int Id;
	String Name;
	int Age;
	String Job;
	
	public Elev() {
		
	}
	
	public Elev(int id, String name, int age, String job) {
		super();
		Id = id;
		Name = name;
		Age = age;
		Job = job;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}

	
	
}
