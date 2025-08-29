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

	public void setQuantidadeInstrcoes(int quantidadeInstrucoes) {
		this.quantidadeInstrucoes = quantidadeInstrucoes;
	}
	
	public void executarInstrucoes(){
		if(quantidadeInstrucoes > 0);
		quantidadeInstrucoes--;
	}
	
	public boolean estaFinalizado(){
		return quantidadeInstrucoes == 0;
	}

	@Override
	public String toString() {
		return "Processo [id=" + id + ", quantidadeInstrucoes="
				+ quantidadeInstrucoes + "]";
	}	
}