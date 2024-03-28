import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SingleBuffer{
  Semaphore sprodutor;
  ArrayList<String> value;

  public SingleBuffer(){
    sprodutor = new Semaphore(1);
    value = new ArrayList<String>();
  }
  public void deposit(String v){
    value.add(v);
  }
  public void fetch(){
    sleituras--;
    String mensagem = "";
    for(int i=0; i<value.size(); i++){
      mensagem += value.get(i);
      mensagem += " ";
    }
    System.out.println(mensagem);
    if(sleituras==0)
      value = new ArrayList<String>();
    
  }
  
}
