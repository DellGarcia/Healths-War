package br.com.healthswar.server;

import java.io.IOException;
import java.util.Arrays;

import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;

public class Partida extends Thread {

	private final Request MATCH_TYPE;
	
	private Player[] players; 
	private int playersConneted;
	private boolean completo;
	
	public Partida(Request MATCH_TYPE) {
		this.MATCH_TYPE = MATCH_TYPE;
		this.playersConneted = 0;
		this.completo = false;
		
		switch(this.MATCH_TYPE) {
			case PLAY_A_SOLO_MATCH:
				this.players = new Player[1];
				break;
				
			case PLAY_A_DUO_MATCH:
				this.players = new Player[2];
				break;	
				
			case PLAY_A_SQUAD_MATCH: 
				this.players = new Player[4];
		}
	}
	
	/**
	 * Adiona players enquanto tiver espaco na partida
	 * */
	public void addPlayer(Player player) {
		if(!completo) {
			this.players[playersConneted] = player;
			this.playersConneted++;
			if(playersConneted == players.length) {
				completo = true;
			}
		} 
	}
	
	public boolean getCompleto() {
		return this.completo;
	}

	
	/**
	 * Aqui vai rolar a partida
	 * */
	@Override
	public void run() {
		for(Player player: players) {
			try {
				player.out.writeObject(Response.MATCH_READY);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
//		while(true) {
//			System.out.println("Partida rolando!");
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

	@Override
	public String toString() {
		return "Partida [MATCH_TYPE=" + MATCH_TYPE + ", players=" + Arrays.toString(players) + ", playersConneted="
				+ playersConneted + ", completo=" + completo + "]";
	}
	
}
