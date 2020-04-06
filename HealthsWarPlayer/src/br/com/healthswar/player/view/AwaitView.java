package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import br.com.dellgarcia.frontend.Label;
import br.com.dellgarcia.frontend.Panel;
import br.com.healthswar.comunication.Response;
import br.com.healthswar.player.model.Player;

@SuppressWarnings("serial")
public class AwaitView extends JFrame {

	private Toolkit tk;	
	
	private Player player;
	
	private Panel container;
	
	private Label lblMsg;
	
	public AwaitView(Player player) {
		tk = Toolkit.getDefaultToolkit();
		this.player = player;
		
		setTitle("Health's War - Await View");
		setSize(tk.getScreenSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setLayout(null);
		
		container = new Panel();
		setContentPane(container);
		
		lblMsg = new Label(350, 50, "Aguardando os outros jogadores...", new Font("Verdana", Font.PLAIN, 20), Color.BLACK);
		lblMsg.setLocation(this.getWidth()/2 - lblMsg.getWidth()/2, this.getHeight()/2 - lblMsg.getHeight()/2);
		container.add(lblMsg);
		
		setVisible(true);
		animacao().start();
	
		aguardarPartida().start();
	}

	private Thread aguardarPartida() {
		return new Thread(new Runnable() {
			public void run() {
				try {
					Response response = (Response) player.in.readObject();
					if(response == Response.MATCH_READY) {
						new MainView();
						dispose();
					}
					
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private Thread animacao() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						lblMsg.setText("Aguardando os outros jogadores");
						Thread.sleep(500);
						lblMsg.setText("Aguardando os outros jogadores.");
						Thread.sleep(500);
						lblMsg.setText("Aguardando os outros jogadores..");
						Thread.sleep(500);
						lblMsg.setText("Aguardando os outros jogadores...");
						Thread.sleep(500);
					} catch(Exception e) {
						
					}
				}
			}
		});
		
	}
}