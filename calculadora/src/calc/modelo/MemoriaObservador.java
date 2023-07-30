package calc.modelo;

@FunctionalInterface
public interface MemoriaObservador {
	
	void valorAlterado (String novoValor);
		
 
}

// o display é o interessado em implementar o valor Alterado.