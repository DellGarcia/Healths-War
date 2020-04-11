package br.com.healthswar.comunication;

import java.io.Serializable;

import br.com.healthswar.model.Person;

@SuppressWarnings("serial")
public class RegisterRequest implements Serializable {

	private Person person;
	
	public RegisterRequest(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
