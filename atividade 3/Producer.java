import java.util.concurrent.Semaphore;

public class Producer extends Thread{
  SingleBuffer buffer;
  String[] value;
  Semaphore s1;
  Semaphore s2;

  public Producer(SingleBuffer b, String[] v, Semaphore s, Semaphore t){
    buffer = b;
    value = v;
    s1 = s;
    s2 = t;
  }
  public void run(){
    try{s1.acquire();}catch(Exception e){}
    for(int j=0; j<value.length; j++)
      buffer.deposit(value[j]);
      try{Thread.sleep(2000);}
      catch (InterruptedException e) {
        System.out.println("Thread interrupted");
    }
    try{s2.release();}catch(Exception e){}
  }
}
