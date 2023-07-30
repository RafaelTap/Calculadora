package calc.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import calc.modelo.Memoria;
import calc.modelo.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador {

	private final JLabel label;

	public Display() {
		Memoria.getInstancia().adicionarObservador(this);
		setBackground(new Color(46, 49, 50));
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(Color.white);
		label.setFont(new Font("courier", Font.PLAIN, 50));

		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 45));
		add(label);
	}

	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor); // que houver uma mudança na memória, irá modificar quem está interessado.

	}

}

// JLabel: são os número que aparecem no display.
//setBackground(new Color(46, 49, 50)): cor de fundo da tela.
//label = new JLabel("1234,56"); o que aparecerá na tela.
// label.setForeground(Color.white): cor dos números.
//abel.setFont(new Font("courier", Font.PLAIN, 30)); tipo, formato e tamanho da fonte.
// font.PLAIN não é negrito.
// setLayout(new FlowLayout(FlowLayout.RIGHT));colocar mais para o lado direito da tela e centralizado.
// o próprio FlowLayout() tem as posições onde desejamos colocar o layout, sem precisar colocar um int até acertar onde ficará o layout.
// (FlowLayout.RIGHT, 10, 45 ) posicionamento dos números digitados. 10 e 45 representam os gap vertical e horizontal, mover para os lados e para cima e para baixo.
// label.setText(novoValor); // que houver uma mudança na memória, irá modificar quem está interessado.
// Memoria.getInstancia().adicionarObservador(this); -> diz ao observador que está interessado em saber sempre que o valor modificar no teclado modificar.
