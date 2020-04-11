package br.com.healthswar.model;

public class Person {

	private String name;
	private String email;
	private String password;
	
	private int victories;
	private int defeats;
	
	public Person(String name, String email, String password) {
		this.name 		= name;
		this.email 		= email;
		this.password 	= password;
	}
	
	// Getters and Setters
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public String getPassword() {
			return password;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}
	
		public int getVitorias() {
			return victories;
		}
	
		public void setVitorias(int vitorias) {
			this.victories = vitorias;
		}
	
		public int getDerrotas() {
			return defeats;
		}
	
		public void setDerrotas(int derrotas) {
			this.defeats = derrotas;
		}

	// To String
		@Override
		public String toString() {
			return "Person [name=" + name + ", email=" + email + ", password=" + password + ", victories=" + victories
					+ ", defeats=" + defeats + "]";
		}
	
}
