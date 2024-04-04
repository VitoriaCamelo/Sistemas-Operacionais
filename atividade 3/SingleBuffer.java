import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SingleBuffer{
  Integer sleituras; //guarda quantas vezes a mensagem foi lida
  ArrayList<String> value; // guarda palavras escritas

  public SingleBuffer(){
   // inicializa o buffer
    value = new ArrayList<String>();
    sleituras = 0;
  }
  public void deposit(String v){
    // quando uma palavra é depositada, 4 leituras devem ser feitas
    sleituras = 16;
    value.add(v);
  }
  public void fetch(Semaphore s, Semaphore t){
    // com 1 leitura, sleituras é decrementado
    sleituras = sleituras-1;
    // as palavras são unidas para formar uma frase
    String mensagem = "";
    for(int i=0; i<value.size(); i++){
      mensagem += value.get(i);
      mensagem += " ";
    }
    // mensagem é impressa
    System.out.println(mensagem+"\n");
    if(sleituras==0){
      // se 4 leituras foram feitas, o buffer é esvaziado e outro produtor pode escrever 
      value = new ArrayList<String>();
      try{s.release();}catch(Exception e){}
    }
    else{
      // se existirem leituras pendentes, consumidores são liberados
      try{t.release();}catch(Exception e){}
      // espera produtor ser liberado 
      // a intenção é impedir que um consumidor consuma várias vezes seguidas
      //try{s.acquire();}catch(Exception e){}
      //try{s.release();}catch(Exception e){}
    }
  }
}
