package br.com.healthswar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import br.com.dellgarcia.frontend.Button;
import br.com.dellgarcia.frontend.Label;
import br.com.healthswar.server.Server;

@SuppressWarnings("serial")
public class TelaControle extends JFrame {

	private JPanel 	container;
	private Button 	btnClose;
	private Button	btnStart;
	private Label 	lblTitle;
	
	private static JTextArea log;
	
	private Server server;
	
	public TelaControle() {
		setTitle("Tela de Controle");
		setSize(1080, 720);
		setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		
		container = new JPanel();
		init();
	}
	
	private void init() {
		// Container
			container.setBackground(Color.DARK_GRAY);
			container.setLayout(null);
			container.setSize(this.getSize());
			setContentPane(container);
		
		// Botao sair	
			btnClose = new Button(
					container.getWidth()-50, 0,
					50, 30,
					container.getBackground(), Color.LIGHT_GRAY,
					Fonts.NORMAL, "X",
					Color.BLACK, 0,
					Color.RED, Color.WHITE);
			container.add(btnClose);
			btnClose.addActionListener(closeAction());
		
		// Titulo da pagina	
			lblTitle = new Label(200, 30, "Health's War Server", Fonts.DESTAQUE, Color.WHITE, container.getBackground());
			container.add(lblTitle);
		
		// Botao iniciar
			btnStart = new Button(
					container.getWidth()/2 - 50, container.getHeight()/2 - 50,
					100, 40,
					Color.WHITE, Color.BLACK,
					Fonts.DESTAQUE, "Start",
					Color.BLACK, 1,
					new Color(65,105,225), Color.white
					);
			container.add(btnStart);
			btnStart.addActionListener(swicthAction());
		
		// Log do servidor
			log = new JTextArea();
			log.setBackground(Color.LIGHT_GRAY);
			log.setSize(new Dimension(700, 300));
			log.setLocation(new Point(container.getWidth()/2 - log.getWidth()/2, 400));
			log.setText("Inicie o servidor e veja o status aqui no Log!");
			log.setFont(Fonts.DESTAQUE);
			log.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			log.setEditable(false);
			log.setFocusable(false);
			container.add(log);
		
		setVisible(true);
	}
	
	private ActionListener closeAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
	}
	
	private ActionListener swicthAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Se estiver desligado ele liga
					if(server == null) {
						server = Server.ligar(40000);
						log.setText(log.getText() + "\nServidor aguardando na porta 40000");
						aguardarPlayers().start();
						btnStart.setText("Stop");
					} else {
						Server.desligar();
						server = null;
						btnStart.setText("Start");
						log.setText("Inicie o servidor e veja o status aqui no Log!");
					}
				} catch (IOException err) {
					System.out.println("Input Error");
					err.printStackTrace();
				}
			}
		};
	}
	
	/**
	 * Enquanto o servidor estiver ligado fica aguardando novos players
	 * */
	private Thread aguardarPlayers() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(Server.ligado) {
					try {
						server.awaitConnetion();
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public static void atualizarLog(String msg) {
		log.setText(log.getText() + "\n" + msg);
		System.out.println(msg);
	}
	
}
