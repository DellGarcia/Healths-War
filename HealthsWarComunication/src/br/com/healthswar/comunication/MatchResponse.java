package br.com.healthswar.comunication;

import java.io.Serializable;

public enum MatchResponse implements Serializable {
	
	AVALIBLE_CARD(1),
	NO_CARDS(0),
	FIGHTER_READY(2),
	NO_FIGHTER(3),
	ITEM_USED(4),
	IMPOSSIBLE_TO_USE(5),
	SUCCESSFUL_ATACK(6);
	
	public int responseCode;

	MatchResponse(int responseCode) {
		this.responseCode = responseCode;
	}
	
}
