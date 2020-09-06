package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class VentanaPrincipal extends JFrame {

	private JButton btnResolver;
	private final Tablero tablero;
	
	public VentanaPrincipal() {
		
		this.setBounds(100, 100, 565, 615);
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		tablero = new Tablero();
		
		JPanel panelBotones = new JPanel();
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.btnResolver = new JButton("Resolver");
		panelBotones.add(btnResolver);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero.reset();
			}
		});
		panelBotones.add(btnReset);
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablero.resolver();
			}
		});
		
		getContentPane().add(tablero, BorderLayout.CENTER);
	}

}
