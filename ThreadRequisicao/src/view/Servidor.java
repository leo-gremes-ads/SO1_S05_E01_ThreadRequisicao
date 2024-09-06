package view;

import controller.*;
import java.util.concurrent.Semaphore;

public class Servidor
{
	public static void main(String[] args)
	{
		Semaphore semaforo = new Semaphore(1);
		for (int i = 0; i < 21; i++) {
			Thread t = new ThreadRequisicao(i + 1, semaforo);
			t.start();
		}
	}
}
