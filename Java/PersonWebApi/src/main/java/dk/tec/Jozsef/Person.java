package dk.tec.Jozsef;

public class Person {
	
	int id;
	String name;
	String job;
	int tlf;
	int hairColor;
	boolean favorit;
	int pet;
	
	public Person() {}

	public Person(int id, String name, String job, int tlf, int hairColor, boolean favorit, int pet) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
		this.tlf = tlf;
		this.hairColor = hairColor;
		this.favorit = favorit;
		this.pet = pet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getTlf() {
		return tlf;
	}

	public void setTlf(int tlf) {
		this.tlf = tlf;
	}

	public int getHairColor() {
		return hairColor;
	}

	public void setHairColor(int hairColor) {
		this.hairColor = hairColor;
	}

	public boolean getFavorit() {
		return favorit;
	}

	public void setFavorit(boolean favorit) {
		this.favorit = favorit;
	}

	public int getPet() {
		return pet;
	}

	public void setPet(int pet) {
		this.pet = pet;
	}	


}
