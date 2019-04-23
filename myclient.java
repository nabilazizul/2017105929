import java.io.*;
import java.net.*;

public class myclient
{

  public static void main(String[] args) throws Exception

  {

     Socket sock = new Socket("192.168.42.131", 45400);

                               //reading from keyboard(keyRead object)

     BufferedReader keyRead = new BufferedReader(new 
InputStreamReader(System.in));

                              //sending to client(pwrite object)
     
     OutputStream ostream = sock.getOutputStream(); 

     PrintWriter pwrite = new PrintWriter(ostream, true);

 

                              //receiving from server(receiveReadobject)
     
     InputStream istream = sock.getInputStream();

     BufferedReader receiveRead = new BufferedReader(new 
InputStreamReader(istream));

 
     System.out.println("Connection Successfully");
     System.out.println("Start Chatting");
     System.out.println("\n");

 

     String receiveMessage, sendMessage;               

     while(true)

     {
        System.out.println("\n");
        System.out.println("To Server:");
        sendMessage = keyRead.readLine();  //keyboard reading
        pwrite.println(sendMessage);       //sending toserver
        pwrite.flush();                    // flush the data

        if((receiveMessage = receiveRead.readLine()) != null) //receivefromserver

        {
            System.out.println("From Server:");
            System.out.println(receiveMessage); //displaying at DOS prompt

        }         

      }               

    }                    

}                        
