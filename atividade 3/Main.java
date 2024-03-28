import java.util.concurrent.Semaphore;
/*
1) 
- Só há um buffer central geranciado pela thread main. Ela deve controlar para apagar a última mensagem lida só depois que cada thread consumidora lê-la;

2) Aumente a quantidade de threads e apresente uma demonstração de que as mensagens estão sincronizadas em todos os casos.
*/

public class Main{
  public static void main(String[] args) {
    String[] m1 = {"Oi,", "tudo", "bem?"};
    String[] m2 = {"Eu,", "vou", "bem"};
    String[] m3 = {"Está", "estudando", "muito?"};
    String[] m4 = {"Sim,", "sistemas", "operacionais"};

    SingleBuffer b = new SingleBuffer();
    Semaphore s1 = new Semaphore(1);
    Semaphore s2 = new Semaphore(0);
    Producer p1 = new Producer(b, m1, s1, s2);
    Producer p2 = new Producer(b, m2, s1, s2);
    Producer p3 = new Producer(b, m3, s1, s2);
    Producer p4 = new Producer(b, m4, s1, s2);
    Consumer c1 = new Consumer(b, s1, s2);
    Consumer c2 = new Consumer(b, s1, s2);
    Consumer c3 = new Consumer(b, s1, s2);
    Consumer c4 = new Consumer(b, s1, s2);
    
    p1.start();
    p2.start();
    p3.start();
    p4.start();
    
    c1.start();
    c2.start();
    c3.start();
    c4.start();
  }
}
