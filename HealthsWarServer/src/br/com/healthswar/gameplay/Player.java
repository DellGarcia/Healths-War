package br.com.healthswar.gameplay;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Player {
	
	// Configuracao pro server
	public Socket socket;
	public ObjectOutputStream out;
	public ObjectInputStream in;
	
	// Configuracao da partida
	private Deck deck;
	
	public Player(Socket socket) throws IOException {
		this.socket = socket;
		out = new ObjectOutputStream(socket.getOutputStream());
		in 	= new ObjectInputStream(socket.getInputStream());
	}
	
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	@Override
	public String toString() {
		return "Player [socket=" + socket + ", out=" + out + ", in=" + in + "]";
	}
	
}
