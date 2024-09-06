package controller;

import java.lang.InterruptedException;
import java.util.concurrent.Semaphore;

public class ThreadRequisicao extends Thread
{
	private int id;
	private Semaphore semaforo;

	public ThreadRequisicao(int id, Semaphore semaforo)
	{
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run()
	{
		int min, max, bd;
		switch (this.id % 3) {
			case 0:
				for (int i = 0; i < 3; i++) {
					calculos(1000, 2000);
					bancoDeDados(1500);
				}
				break;
			case 1:
				for (int i = 0; i < 2; i++) {
					calculos(200, 1000);
					bancoDeDados(1000);
				}
				break;
			default: //case 2
				for (int i = 0; i < 3; i++) {
					calculos(500, 1500);
					bancoDeDados(1500);
				}
				break;
		}
	}

	public void calculos(int min, int max)
	{
		int tempo = (int)(Math.random() * (max - min)) + min;
		System.out.println("#" + this.id + " está fazendo cálculos (" + tempo + " ms)");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	public void bancoDeDados(int tempo)
	{
		System.out.println("#" + this.id + " está pronto para transação com o Banco de Dados");
		try {
			this.semaforo.acquire();
			System.out.println("#" + this.id + " está realizando transações com o Banco de Dados (" + tempo + " ms)");
			sleep(tempo);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			System.out.println("#" + this.id + " terminou a transação com o Banco de Dados.");
			this.semaforo.release();
		}
	}
}
