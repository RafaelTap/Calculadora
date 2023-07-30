package calc.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame {


	public Calculadora() {

		organizarLayout();

		setSize(429, 519);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void organizarLayout() {
		setLayout(new BorderLayout());
		
		Display display = new Display();
		display.setPreferredSize(new Dimension(233,110));
		add(display, BorderLayout.NORTH); 
		
		Teclado teclado = new Teclado();
		add(teclado, BorderLayout.CENTER);



	}

	public static void main(String[] args) {

		new Calculadora();
	}

}

// 1- setSize(WIDTH, HEIGHT); define a altura e largura.
// 2- setDefaultCloseOperation(); garante que a aplicação fechará após fechar a
// tela.
// 3- DISPOSE_ON_CLOSE: finaliza a tela atual, se tiver apenas uma tela na
// aplicação, ela fecha normalmente.
// 4-setLocationRelativeTo(null); garante que a tela abrirá no centro da tela o
// computador.
// 5- EXIT_ON_CLOSE: garante o fechamente da aplicação de fato, usado apenas no
// Jframe e aplicações.
// 6- setVisible() já aparecerá uma tela.

//organizarLayout() {} :método responsável por organizar o layout da calculadora, referente aos locais onde ficarão o display e o teclado.
// 1- setLayout(); organiza como ficará o layout, em norte/sul ...
// 2- BorderLayout(); constrói um layout sem espaços entre os componentes.
// 3- add(display, BorderLayout.NORTH); adiciona o componente display ao norte baseado no layout que estamos usando. 
//Obs: o BorderLayout só funcionará se ele estiver setado( setLayout).
// 4- display.setPreferredSize(new Dimension(233,110)); define as dimensões do display.
// setUndecoreted(true): sumir com a barra (fechar, minimizar, maximizar).
