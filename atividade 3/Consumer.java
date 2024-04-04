import java.util.concurrent.*;

public class Consumer extends Thread{
  SingleBuffer buffer;
  String value;
  Semaphore s1;
  Semaphore s2;

  public Consumer(SingleBuffer b, Semaphore s, Semaphore t){
    // consumidor recebe buffer e semáforos s1 e s2
    buffer = b;
    s1 = s;
    s2 = t;
  }
  public void run(){
    while(true){
      // consumidor espera ser liberado por algum produtor
      try{s2.acquire();}catch(Exception e){}
      // lê mensagem
      buffer.fetch(s1, s2);  
    }
  }
}
