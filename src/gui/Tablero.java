package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;

public class Tablero extends JPanel {

	private BotonTablero[][] botones;
	private ControladorTablero controlador;
	private boolean bloqueado;
	
	public Tablero() {
		
		this.controlador = new ControladorTablero();
		this.botones = new BotonTablero[9][9];
				
		setLayout(new GridLayout(9, 9, 0, 0));
		
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				final BotonTablero boton = new BotonTablero(x, y);
				boton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (!bloqueado) {
							int valor = 0;
							if (e.getButton() == MouseEvent.BUTTON1) {
								valor = controlador.incrementar(boton.getPosicionX(), boton.getPosicionY());
							} else if (e.getButton() == MouseEvent.BUTTON3) {
								valor = controlador.disminuir(boton.getPosicionX(), boton.getPosicionY());
							}
							boton.fijar(valor);
						}
					}
				});
				add(boton);
				botones[x][y] = boton; 			
			}
		}	
		controlador.setVista(this);
	}

	public void resolver() {
		controlador.resolver();
	}

	public void bloquear() {
		this.bloqueado = true;
	}
	
	public void desbloquear() {
		this.bloqueado = false;
	}
	
	public void reset() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				botones[x][y].reset();
			}
		}
		controlador.reset();
	}

	public void mostrarResultado(int[][] resultado) {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				botones[x][y].setValor(resultado[x][y]);
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));
		Rectangle r = getBounds();
		g2d.drawLine(r.width/3, 0, r.width/3, r.height);
		g2d.drawLine(r.width*2/3, 0, r.width*2/3, r.height);
		g2d.drawLine(0, r.height/3, r.width, r.height/3);
		g2d.drawLine(0, r.height*2/3, r.width, r.height*2/3);

		g2d.setStroke(new BasicStroke());
		
		for(int i = 1; i<9; i++) {
			g2d.drawLine(r.width * i / 9, 0, r.width * i / 9, r.height);
		}
		for(int i = 1; i<9; i++) {
			g2d.drawLine(0, r.height * i / 9, r.width, r.height * i /9);
		}
	}
	
}
