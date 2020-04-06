package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

import br.com.dellgarcia.frontend.Button;
import br.com.dellgarcia.frontend.Panel;
import br.com.healthswar.comunication.Request;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.player.model.Player;

@SuppressWarnings("serial")
public class InitView extends JFrame {

	private Toolkit tk;
	
	private Panel container;
	
	private Button soloMatch;
	private Button duoMatch;
	private Button squadMatch;
	
	public InitView() {
		
		tk = Toolkit.getDefaultToolkit();
		
		setTitle("Health's War");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tk.getScreenSize());
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		container = new Panel(Color.WHITE);
		setContentPane(container);
		
		Font font = new Font("Verdana", Font.PLAIN, 22);
		
		soloMatch = new Button(
				getWidth()/2 - 100, getHeight()/2 - 25 - 65,
				200, 50,
				Color.DARK_GRAY, Color.WHITE,
				font, "Solo Match",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		soloMatch.addActionListener(matchAction(1));
		
		duoMatch = new Button(
				getWidth()/2 - 100, getHeight()/2 - 25,
				200, 50,
				Color.DARK_GRAY, Color.WHITE,
				font, "Duo Match",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		duoMatch.addActionListener(matchAction(2));
		
		squadMatch = new Button(
				getWidth()/2 - 100, getHeight()/2 - 25 + 65,
				200, 50,
				Color.DARK_GRAY, Color.WHITE,
				font, "Squad Match",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		squadMatch.addActionListener(matchAction(3));
				
		container.add(soloMatch);
		container.add(duoMatch);
		container.add(squadMatch);
		
		setVisible(true);
	}
	
	/**
	 * Vê qual partida o player escolheu
	 * */
	private ActionListener matchAction(int option) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Player player = new Player(new Socket("localhost", 40000));
					
					switch (option) {
						case 1:
							player.out.writeObject(Request.PLAY_A_SOLO_MATCH);
							break;
		
						case 2:
							player.out.writeObject(Request.PLAY_A_DUO_MATCH);
							break;
							
						case 3:
							player.out.writeObject(Request.PLAY_A_SQUAD_MATCH);
							break;
					}
					
					Response response = (Response) player.in.readObject();
					if(response == Response.MATCH_FOUND) {
						new AwaitView(player);
						dispose();
					}
					
				} catch (IOException | ClassNotFoundException e2) {
					System.out.println("Problemas ao tentar conectar ao servidor");
					e2.printStackTrace();
				}
				
			}
		};
	}
	
}
