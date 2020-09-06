package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class BotonTablero extends JButton {

	private int x;
	private int y;
	
	public BotonTablero(int x, int y) {
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setForeground(Color.BLACK);
		this.x = x;
		this.y = y;
		setText("");
		setFont(new Font("Arial", Font.PLAIN, 30));
	}

	public void setValor(int valor) {
		if(valor != 0) {
			setText(valor + "");
		}
		else {
			setText("");
		}
	}
	
	public void fijar(int valor) {
		setValor(valor);
		if(valor != 0){
			setContentAreaFilled(true);
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
		}
		else {
			setContentAreaFilled(false);
			setForeground(Color.BLACK);
		}
	}
	
	public int getPosicionX() {
		return x;
	}

	public int getPosicionY() {
		return y;
	}

	public void reset() {
		setValor(0);
		setContentAreaFilled(false);
		setForeground(Color.BLACK);
	}
}
