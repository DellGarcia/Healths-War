package br.com.healthswar.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Player {
	
	public Socket socket;
	public ObjectOutputStream out;
	public ObjectInputStream in;
	
	public Player(Socket socket) throws IOException {
		this.socket = socket;
		out = new ObjectOutputStream(socket.getOutputStream());
		in 	= new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public String toString() {
		return "Player [socket=" + socket + ", out=" + out + ", in=" + in + "]";
	}
	
}
