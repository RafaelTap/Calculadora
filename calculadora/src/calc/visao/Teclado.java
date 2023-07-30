package calc.visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import calc.modelo.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener {

	private final Color COR_CINZA_ESCURO = new Color(68, 68, 68);
	private final Color COR_CINZA_CLARO = new Color(99, 99, 99);
	private final Color COR_LARANJA = new Color(242, 163, 60);

	public Teclado() {

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setLayout(layout);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;

		// linha 1

		c.gridwidth = 2;
		adicionarBotao("AC", COR_CINZA_ESCURO, c, 0, 0);
		c.gridwidth = 1;
		adicionarBotao("±", COR_CINZA_ESCURO, c, 2, 0);

		adicionarBotao("/", COR_LARANJA, c, 3, 0);

		// linha 2
		adicionarBotao("7", COR_CINZA_CLARO, c, 0, 1);
		adicionarBotao("8", COR_CINZA_CLARO, c, 1, 1);
		adicionarBotao("9", COR_CINZA_CLARO, c, 2, 1);
		adicionarBotao("*", COR_LARANJA, c, 3, 1);

		// linha 3
		adicionarBotao("4", COR_CINZA_CLARO, c, 0, 2);
		adicionarBotao("5", COR_CINZA_CLARO, c, 1, 2);
		adicionarBotao("6", COR_CINZA_CLARO, c, 2, 2);
		adicionarBotao("-", COR_LARANJA, c, 3, 2);

		// linha 4
		adicionarBotao("1", COR_CINZA_CLARO, c, 0, 3);
		adicionarBotao("2", COR_CINZA_CLARO, c, 1, 3);
		adicionarBotao("3", COR_CINZA_CLARO, c, 2, 3);
		adicionarBotao("+", COR_LARANJA, c, 3, 3);

		// linha 5
		
		c.gridwidth = 2;
		adicionarBotao("0", COR_CINZA_CLARO, c, 0, 4);
		c.gridwidth = 1;
		adicionarBotao(".", COR_CINZA_CLARO, c, 2, 4);
		adicionarBotao("=", COR_LARANJA, c, 3, 4);

	}

	private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this);
		add(botao, c);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
		JButton botao = (JButton) e.getSource();
        Memoria.getInstancia().processarComando(botao.getText());

		}
	}
}

// setLayout(new GridLayout(5,4)); não é tão flexível quanto o gridBagLayout(). 
// com este último, podemos fazer um botão ocupar mais de uma casa.
// gridBagLayout(), construímos posição dos botões apartir dos eixos x e y.
// obs: inserção das linhas e colunas é sempre a partir do índice 0.
// linha 0, coluna 0 -> primeiro botão.
// método "fill" é utilizado para preencher os botões. c.fill = GridBagConstraints.BOTH (preenchimento).
// c.weightx = 1; | c.weighty = 1;  -> propriedades de "peso" que irão alargar o teclado para preencher toda a calculadora.
// c.gridwidth -> para ocupar uma quantidade x de linhas. (largura  do botão).
// actionPerformed(ActionEvent e) ação executada.
// JButton botao = (JButton) e.getSource(); -> mostra no display o valor digitado.
// Memoria.getInstancia().processarComando(botao.getText()); -> imprime no display o que está escrito no botão. 

// e.getSource() retorna object, é preciso fazer um cast para JButton.
// (e.getSource() instanceof JButton) -> quer dizer que irá imprimir no display aquilo que está impresso no botão .