import java.util.concurrent.*;
/*
- As threads consumidoras deverão checar o buffer, ler a última palavra não lida no buffer e imprimi-la na tela identificando seu próprio Thread ID;
*/
public class Consumer extends Thread{
  SingleBuffer buffer;
  String value;

  public Consumer(SingleBuffer b){
    buffer = b;
  }
  public void run(){
    while(true)
      buffer.fetch();
  }
}
