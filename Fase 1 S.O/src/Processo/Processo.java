package Processo;

public class Processo {
	private int id;
	private int quantidadeInstrucoes;
	
	public Processo(int id, int quantidadeInstrucoes) {
		this.id = id;
		this.quantidadeInstrucoes = quantidadeInstrucoes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidadeInstrucoes() {
		return quantidadeInstrucoes;
	}

	public void setQuantidadeInstrucoes(int quantidadeInstrucoes) {
		this.quantidadeInstrucoes = quantidadeInstrucoes;
	}
	
	public boolean estaFinalizado(){
		return quantidadeInstrucoes == 0;
	}	
}
