import java.io.*;

import java.net.Socket;

import java.util.*;

public class TACACSclient{

// Main function

public static void main(String[] args){

Scanner scan = new Scanner(System.in);

// Router user interface (Exec Privilege Mode)

for(;;){

System.out.print("\nRouter# ");

String cli = scan.nextLine();

// Command line that need to verify before can be execute

if((cli.toLowerCase().indexOf("debug")) >= 0){

verification(cli);

}else if(cli.toLowerCase().indexOf("exit") >= 0){ // Exit

program

break;

}else if(cli.toLowerCase().indexOf("telnet") >= 0){

verification(cli);

}else if(cli.toLowerCase().indexOf("enable") >= 0){

verification(cli);

}

}

}

// Verification process

public static void verification(String cli){

String username = "", password = "", errorHandling = "";

String encoded = "", recvData="";

char[] pass = null;

Scanner scan = new Scanner(System.in);

Console cons = System.console();

try{

Socket sock = new Socket("192.168.42.133", 49);

PrintWriter pw = new PrintWriter(sock.getOutputStream(), true);

BufferedReader br = new BufferedReader(new

InputStreamReader(sock.getInputStream()));

// Send command input from user

encoded = encoder(cli);

pw.print(encoded);

pw.flush();

System.out.println("\n\nUser Access Verification\n");

// Send username

recvData = br.readLine();

System.out.print(recvData);

username = scan.nextLine();

encoded = encoder(username);

pw.print(encoded);

pw.flush();

// Send password

recvData = br.readLine();

pass = cons.readPassword(recvData, new Object[0]);

password = new String(pass);

encoded = encoder(password);

pw.print(encoded);

pw.flush();

// Handle error message received from server

errorHandling = br.readLine();

System.out.println("\n"+errorHandling);

if((cli.toLowerCase().indexOf("debug")) >= 0){

debug(sock, cli);

}

}catch (IOException localIOException) {

System.out.println(localIOException);

}

}

// Encode the data to send to TACACS server

public static String encoder(String line){

String encoded = "";

encoded = Base64.getEncoder().encodeToString(line.getBytes());

return encoded;

}

// Debug process by read log file from server

public static void debug(Socket sock, String cli){

byte[] decoder = new byte[4096];

String line = "", decoded = "";

String s = "";

System.out.println("\nTACACS access control debugging is on\n");

try{

BufferedReader br = new BufferedReader(new

InputStreamReader(sock.getInputStream()));

while((line = br.readLine()) != null){

decoder = Base64.getDecoder().decode(line);

decoded = new String(decoder);

System.out.println(decoded);

}

br.close();

}catch (Exception localException) {

System.out.println(localException);

}

}

}
