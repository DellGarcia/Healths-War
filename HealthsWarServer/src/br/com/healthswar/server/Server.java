package br.com.healthswar.server;

import java.io.IOException;
import java.net.ServerSocket;

import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;

public class Server extends ServerSocket {
	
	private static Server server;
	
	public static boolean ligado;
	
	private Partida solo;
	private Partida duo;
	private Partida squad;
	
	private Server(int port) throws IOException {
		super(port);
		solo = new Partida(Request.PLAY_A_SOLO_MATCH);
		duo = new Partida(Request.PLAY_A_DUO_MATCH);
		squad = new Partida(Request.PLAY_A_SQUAD_MATCH);
	}

	public static Server ligar(int port) throws IOException {
		if(server == null) {
			server = new Server(port);
			ligado = true;
		}
		return server;
	}
	
	public static void desligar() throws IOException {
		server.close();
		ligado = false;
		server = null;
	}
	
	public void awaitConnetion() throws IOException, ClassNotFoundException {
		Player player = new Player(accept());
		
		Request request = (Request) player.in.readObject();
		
		switch(request) {
			case PLAY_A_SOLO_MATCH:
				solo.addPlayer(player);
				break;
				
			case PLAY_A_DUO_MATCH:
				duo.addPlayer(player);
				break;
				
			case PLAY_A_SQUAD_MATCH:
				squad.addPlayer(player);
				break;
		}
		
		player.out.writeObject(Response.MATCH_FOUND);
		
		verificarPartida(solo, Request.PLAY_A_SOLO_MATCH);
		verificarPartida(duo, Request.PLAY_A_DUO_MATCH);
		verificarPartida(squad, Request.PLAY_A_SQUAD_MATCH);
	}
	
	private void verificarPartida(Partida match, Request request) {
		if(match.getCompleto()) {
			match.start();
			match = new Partida(request);
		}
	}
	
}
