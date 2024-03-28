import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SingleBuffer{
  Integer sleituras;
  ArrayList<String> value;

  public SingleBuffer(){
    value = new ArrayList<String>();
    sleituras = 0;
  }
  public void deposit(String v){
    sleituras = 4;
    value.add(v);
  }
  public void fetch(Semaphore s){
    sleituras = sleituras-1;
    String mensagem = "";
    for(int i=0; i<value.size(); i++){
      mensagem += value.get(i);
      mensagem += " ";
    }
    System.out.println(mensagem+"\n");
    if(sleituras==0){
      value = new ArrayList<String>();
      s.release();
    }
    
  }
  
}
