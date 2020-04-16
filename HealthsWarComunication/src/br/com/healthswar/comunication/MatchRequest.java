package br.com.healthswar.comunication;

import java.io.Serializable;

public enum MatchRequest implements Serializable {
	
	DRAW_A_CARD(0),
	SEND_A_FIGHTER(1),
	USE_AN_ITEM(2),
	ATACK_THE_OPONENT(3),
	END_THE_TURN(4),
	GET_PHASE(5);
	
	public int requestCode;

	MatchRequest(int requestCode) {
		this.requestCode = requestCode;
	}
	
}
