package calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private enum TipoComando {
		ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, PONTO, TROCARSINAL; // acrescentar TROCARSINAL
	};

	private static final Memoria instancia = new Memoria();
	private final List<MemoriaObservador> observadores = new ArrayList<>();

	private TipoComando ultimaOperacao = null; // amazenar o valor de operações.
	private boolean substituir = false;// substituir o display por um novo número, detecta quando isso irá ocorrer.
	// quando alguma operação matemática for clicada.
	private String textoAtual = "";
	private String textoBuffer = ""; // armazenar os números e sinais.

	private Memoria() {

	}

	public static Memoria getInstancia() {
		return instancia;
	}

	public void adicionarObservador(MemoriaObservador observador) {
		observadores.add(observador); // sempre que houver uma mudança no texto, ele irá notificar todos os
										// observadores.
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}

	public void processarComando(String texto) {
		TipoComando tipoComando = detectarTipoComando(texto);
		System.out.println(tipoComando);

		if (tipoComando == null) {
			return;
		} else if (tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		}else if (tipoComando == TipoComando.TROCARSINAL && textoAtual.contains("-")) {
			textoAtual = textoAtual.substring(1);
		}else if (tipoComando == TipoComando.TROCARSINAL && !textoAtual.contains("-")) {
			textoAtual = "-" + textoAtual;
		} else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.PONTO) {
	 		textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = obterResultadoOperacao(); // primeiro calcula o resultada da operação para
			textoBuffer = textoAtual; // o resultado ser colocado no buffer
			ultimaOperacao = tipoComando; // pegar o valor da última operação.
		}

		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));

	}

	private String obterResultadoOperacao() {
		if (ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		double numeroBuffer = Double.parseDouble(textoBuffer);
		double numeroAtual = Double.parseDouble(textoAtual);

		double resultado = 0;
		if (ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		} else if (ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		} else if (ultimaOperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		} else if (ultimaOperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
		}

		String resultadoString = Double.toString(resultado);
		boolean inteiro = resultadoString.endsWith(".0");
		return inteiro ? resultadoString.replace(".0", "") : resultadoString;
	}

	private TipoComando detectarTipoComando(String texto) {
		if (textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
			// quando não for número ...
			if ("AC".equals(texto)) {
				return TipoComando.ZERAR;
			} else if ("/".equals(texto)) {
				return TipoComando.DIV;
			} else if ("*".equals(texto)) {
				return TipoComando.MULT;
			} else if ("+".equals(texto)) {
				return TipoComando.SOMA;
			} else if ("-".equals(texto)) {
				return TipoComando.SUB;
			} else if ("=".equals(texto)) {
				return TipoComando.IGUAL;
			} else if ("±".equals(texto)) {
				return TipoComando.TROCARSINAL;
			} else if (".".equals(texto) & !textoAtual.contains(".")) {
				return TipoComando.PONTO;
			}

		}
		return null;
	}

}
// processarComando().
// quando passar o novo comando para processar, ele acrescenta e notifica todos
// os observadores
// método para processar o novo caractere de estado.

//o método getTextoAtual() quer dizer que, caso não tenha nada digitado, na tela
// aparecerá o número 0.

// o evento acontece no teclado, isso percisa ser armazenado na memória e assim 
// traduzido para o display. Utilizamos então o padrão observer.
