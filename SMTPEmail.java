import java.io.*;

import java.net.*;



public class SMTPEmail {



private final static int SMTP_PORT = 2525;

private final static String MAIL_SERVER = "example.com";

private final static String SENDER_EMAIL = "user1@example.com";

private final static String RECEIVER_EMAIL = "user2@example.com";

private final static String EMAIL_MESSAGE = "This is a test email agent!";



public static void main(String[] args) throws Exception {



Socket socket = null;



try

{



// Establish a TCP connection with the mail server.

socket = new Socket(MAIL_SERVER, SMTP_PORT);



// Create a BufferedReader to read a line at a time.

InputStream is = socket.getInputStream();

InputStreamReader isr = new InputStreamReader(is);

BufferedReader br = new BufferedReader(isr);



// Read greeting from the server.

String response = br.readLine();

if (!response.startsWith("220")) {

throw new Exception("220 reply not received from server.");

}



// Get a reference to the socket's output stream.

OutputStream os = socket.getOutputStream();



// Send HELO command and get server response.

String command = "HELO MAIL SERVER\r\n";

os.write(command.getBytes("US-ASCII"));

response = br.readLine();



System.out.println(response);

if (!response.startsWith("250")) {

throw new Exception("250 reply not received from server.");

}

// Send MAIL FROM command.

command = "MAIL FROM "+SENDER_EMAIL+"\r\n";

os.write(command.getBytes("US-ASCII"));

response = br.readLine();



// Send RCPT TO command.

command = "RCPT TO "+RECEIVER_EMAIL+"\r\n";

os.write(command.getBytes("US-ASCII"));

response = br.readLine();



// Send DATA command.

command = "DATA"+"\r\n";

os.write(command.getBytes("US-ASCII"));

response = br.readLine();



// Send message data.

command = EMAIL_MESSAGE+"\r\n";

os.write(command.getBytes("US-ASCII"));



// End with line with a single period.

command = "\r\n."+"\r\n";

os.write(command.getBytes("US-ASCII"));

response = br.readLine();



// Send QUIT command.

command = "QUIT"+"\r\n";

os.write(command.getBytes("US-ASCII"));

response = br.readLine();

}

finally

{

// close the socket

if( socket != null )

socket.close();

}

}

}
