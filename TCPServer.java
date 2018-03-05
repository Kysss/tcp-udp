import java.io.*;
import java.net.*;

/**
 * Created by Yingying Xia on 2018/2/17.
 */

public class TCPServer {

    static void sendBackSame(){
        final int PORT = 2705;
        byte[] clientMessage = null;
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("TCP : Port " + PORT + " is opened and is listening ......");
            for(;;){

                Socket socket = serverSocket.accept();
                DataInputStream inFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

                int length = inFromClient.readInt();

                if(length>0){
                    clientMessage = new byte[length];
                    inFromClient.readFully(clientMessage,0,clientMessage.length);

                }
                //  while((length=inFromClient.read())>0){}

                System.out.println("SERVER: Received " + clientMessage.length + " bytes of data."  );

                outToClient.writeInt(clientMessage.length);
                outToClient.write(clientMessage);
                System.out.println("SERVER: Sent back " + clientMessage.length + " bytes of data back to client.");

                outToClient.close();
                inFromClient.close();
                socket.close();
            }
        }catch(IOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }


    }

    static void oneByteAcknowledgement(){
        final int PORT = 2705;
        byte[] clientMessage = null;
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("TCP : Port " + PORT + " is opened and is listening ......");
            for(;;){

                Socket socket = serverSocket.accept();
                DataInputStream inFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

                int length = inFromClient.readInt();

                if(length>0){
                    clientMessage = new byte[length];
                    inFromClient.readFully(clientMessage,0,clientMessage.length);

                }
                //  while((length=inFromClient.read())>0){}

                System.out.println("SERVER: Received " + clientMessage.length + " bytes of data."  );

                byte[] serverMessage = new byte[1];
                outToClient.writeInt(serverMessage.length);
                outToClient.write(serverMessage);
                System.out.println("SERVER: Sent back " + serverMessage.length + " bytes of data back to client.");

                outToClient.close();
                inFromClient.close();
                socket.close();
            }
        }catch(IOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }
    }


    public static void main(String args[]) {
       oneByteAcknowledgement();
    }

}
