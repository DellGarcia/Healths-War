package br.com.healthswar.comunication;

import java.io.Serializable;

public enum ResgisterResponse implements Serializable {

	REGISTRATION_COMPLETED(0),
	REGISTRATION_FAILED(1);
	
	ResgisterResponse(int code) {}
	
}
