import java.util.concurrent.*;

public class Consumer extends Thread{
  SingleBuffer buffer;
  String value;
  Semaphore s1;
  Semaphore s2;

  public Consumer(SingleBuffer b, Semaphore s, Semaphore t){
    buffer = b;
    s1 = s;
    s2 = t;
  }
  public void run(){
    while(true){
      try{s2.wait();}catch(Exception e){}
      buffer.fetch(s1);
      try{s2.release();}catch(Exception e){}
    }
  }
}
