import java.util.concurrent.Semaphore;

public class Producer extends Thread{
  SingleBuffer buffer;
  String[] value;
  Semaphore s1;
  Semaphore s2;

  public Producer(SingleBuffer b, String[] v, Semaphore s, Semaphore t){
    // produtor recebe buffer, mensagem para emviar e os semáforos s1 e s2
    buffer = b;
    value = v;
    s1 = s;
    s2 = t;
  }
  public void run(){
    // s1.acquire() garante que 1 produtor execute por vez
    try{s1.acquire();}catch(Exception e){}
    // loop para enviar cada palavra da mensagem
    for(int j=0; j<value.length; j++){
      // envio da palavra
      buffer.deposit(value[j]);
      // espera
      try{Thread.sleep(2000);}
      catch(InterruptedException e){}
    }
    // produtor libera consumidores
    try{s2.release();}catch(Exception e){}
  }
}
