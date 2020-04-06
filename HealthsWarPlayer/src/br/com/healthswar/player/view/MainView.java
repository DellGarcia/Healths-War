package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;

import br.com.dellgarcia.frontend.Label;
import br.com.dellgarcia.frontend.Panel;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	private Toolkit tk;
	
	private Panel container;
	
	public MainView() {
		tk = Toolkit.getDefaultToolkit();
		
		setTitle("Health's War");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tk.getScreenSize());
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		container = new Panel(new Color(54,54,54));
		setContentPane(container);
		
		container.add(new Label(500, 50, "Agora vai dar bom", new Font("verdana", Font.PLAIN, 18), Color.BLACK));
		
		setVisible(true);
	}
	
}
