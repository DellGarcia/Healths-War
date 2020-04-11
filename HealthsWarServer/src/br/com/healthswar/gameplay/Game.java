package br.com.healthswar.gameplay;

import java.util.Arrays;

public class Game {

	private int turno;
	private boolean ativo;
	
	private Player[] players;
	
	public Game(Player[] players) {
		this.players = players;
		this.turno = 1;
		this.ativo = true;
		sortDeck();
	}
	
	private void sortDeck() {
		players[0].setDeck(new Deck(DeckTheme.IMMUNE_SYSTEM));
		players[1].setDeck(new Deck(DeckTheme.FOREIGN_BODIES));
		Arrays.sort(players);
	}
	
	// Açoes do player
		public void comprarCarta(Player player) {
			
		}
		
		public void enviarCombatente(Player player) {
			
		}
		
		public void usarItem(Player player) {
			
		}
		
		public void atacar(Combatente escolhido, Combatente alvo) {
			
		}
		
		public void encerrarTurno(Player player) {
			this.turno++;
		}
		
	// Getters e Setters
		public int getTurno() {
			return turno;
		}

		public void setTurno(int turno) {
			this.turno = turno;
		}

		public boolean isAtivo() {
			return ativo;
		}

		public void setAtivo(boolean ativo) {
			this.ativo = ativo;
		}

		public Player[] getPlayers() {
			return players;
		}

		public void setPlayers(Player[] players) {
			this.players = players;
		}
}
