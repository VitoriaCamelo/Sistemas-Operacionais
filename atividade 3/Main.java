import java.util.concurrent.Semaphore;

public class Main{
  public static void main(String[] args) {
    // mensagens a serem enviadas pelos produtores
    String[] m1 = {"Oi,", "tudo", "bem?"};
    String[] m2 = {"Eu", "vou", "bem"};
    String[] m3 = {"Está", "estudando", "muito?"};
    String[] m4 = {"Sim,", "sistemas", "operacionais"};

    // buffer único 
    SingleBuffer b = new SingleBuffer();
    // o semáforo s1 permite que 1 produtor execute logo 
    Semaphore s1 = new Semaphore(1);
    // s2 = 0 não deixa os consumidores executarem leitura até algum produtor liberar
    Semaphore s2 = new Semaphore(0);
    // criação dos produtores e consumidores
    Producer p1 = new Producer(b, m1, s1, s2);
    Producer p2 = new Producer(b, m2, s1, s2);
    Producer p3 = new Producer(b, m3, s1, s2);
    Producer p4 = new Producer(b, m4, s1, s2);
    Consumer c1 = new Consumer(b, s1, s2);
    Consumer c2 = new Consumer(b, s1, s2);
    Consumer c3 = new Consumer(b, s1, s2);
    Consumer c4 = new Consumer(b, s1, s2);
  // threads são iniciadas
    p1.start();
    p2.start();
    p3.start();
    p4.start();

    c1.start();
    c2.start();
    c3.start();
    c4.start();
  for(int i=0; i<6; i++){
    String[] m = {"Questão", "2: ", String.valueOf(i)};
    Producer p = new Producer(b, m, s1, s2);
    p.start();
    String[] n = {"Segunda", "questão: ", String.valueOf(i+1)};
    Producer pn = new Producer(b, n, s1, s2);
    pn.start();
    Consumer c = new Consumer(b, s1, s2);
    c.start();
    Consumer cn = new Consumer(b, s1, s2);
    cn.start();
  }
  }
}
