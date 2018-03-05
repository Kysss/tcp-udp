import java.io.*;
import java.net.*;
public class TCPClient {

    static long startTime, endTime, elapsed;
    static Socket clientSocket = null;
    static DataInputStream inFromServer = null;
    static DataOutputStream outToServer =  null;
    static byte[] serverMessage = null;

    private static void initiateConnection(String HOST, int PORT){

        try {
            System.out.println("CLIENT: Trying to connect to [" + HOST + "] at port " + PORT + " ......");
            clientSocket = new Socket(HOST, PORT);
            System.out.println("CLIENT: Successfully connected to [" + HOST + "] at port " + PORT);
        } catch(ConnectException ex){
            //retry connecting until it connects...
            initiateConnection(HOST,PORT);
        } catch(IOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    private static void writeToServer(int byteLength){
        byte[] message = new byte[byteLength];
        try {
         //   inFromServer = new DataInputStream(clientSocket.getInputStream());
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            startTime = System.nanoTime();
            outToServer.writeInt(byteLength);
            outToServer.write(message);

          //  outToServer.flush();

            System.out.println("CLIENT: Sending " + message.length + " bytes of data.");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void receiveFromServer(){
        int length;
        try {
            inFromServer = new DataInputStream(clientSocket.getInputStream());
            length = inFromServer.readInt();
            if(length > 0){
                serverMessage = new byte[length];
                inFromServer.readFully(serverMessage,0,serverMessage.length);
            }


            endTime = System.nanoTime();
            System.out.println("CLIENT: Received " +serverMessage.length+ " bytes of information back!");

            elapsed = endTime - startTime;
            System.out.println("CLIENT: The connection took " + elapsed + " nanoseconds / " + elapsed/1000000 + " milliseconds.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void cleanUp(){
        try {
            inFromServer.close();
            outToServer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void runTrial(int trial, int byteSize,String HOST, int PORT){

        long timeTaken = 0;
        System.out.println("====================Start Running Trials====================");
        for (int i = 1; i <= trial; i++) {
            initiateConnection(HOST,PORT);
                System.out.println("Running Trial " + i + " :");
                writeToServer(byteSize);
                receiveFromServer();
                timeTaken += elapsed;
            cleanUp();
            }
        long averageRTT = timeTaken / trial;
        System.out.println("==================Finished Running Trials ==================");
       // System.out.println("Finished running " + trial + " times.");

        System.out.println("SUMMARY: Total time taken is:" + timeTaken + "/ " + timeTaken/1000000 +" milliseconds.");
        System.out.println("SUMMARY: Average RTT for " + trial + " trials is " + averageRTT + " nanoseconds / " +
                (averageRTT / 1000000) + " milliseconds.");





    }


    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 2705;
        //RTT Collect trials/byteSize
        runTrial(10, 1,HOST,PORT);
    }


}
