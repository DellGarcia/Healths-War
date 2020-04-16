package br.com.healthswar.comunication;

import java.io.Serializable;

public enum Phases implements Serializable {

	DRAW_PHASE(0),
	MAIN_PHASE(1),
	BATTLE_PHASE(2),
	END_PHASE(3);
	
	public int phaseCode;
	
	Phases(int phaseCode) {
		
	}

}
