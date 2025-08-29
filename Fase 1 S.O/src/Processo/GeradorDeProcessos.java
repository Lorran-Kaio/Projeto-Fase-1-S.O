package Processo;
import java.util.Random;

public class GeradorDeProcessos {
	private static int contadorId = 1;
	private static Random random = new Random();
	
	public Processo gerarProcesso(){
		int instrucoes = 10 + random.nextInt(41);
		Processo novo = new Processo(contadorId, instrucoes);
		contadorId++;
		return novo;
	}
}