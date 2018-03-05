/**
 * Created by Yingying Xia on 2018/2/20.
 */

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

class UDPClientWithSeq {
 //   static int byteSize = 1;
    static final int PORT = 2705;
  // static final String HOST = "pi.cs.oswego.edu";
    static final String HOST = "localhost";
    static DatagramSocket clientSocket = null;
    static long startTime, elapsed, endTime;

    static void initializeConnection(){
        try {
            clientSocket = new DatagramSocket();
            System.out.println("Initialized....");
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    static void sendData(int byteSize, int dataInt){

        try {
            byte[] sendData = ByteBuffer.allocate(byteSize).putInt(dataInt).array();
           // ByteBuffer.allocate(byteSize).array();
            // above can be used to not have number
            //make another send Data method that would take no dataINT
            InetAddress IPAddress = InetAddress.getByName(HOST);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PORT);
            startTime = System.nanoTime();
            clientSocket.send(sendPacket);
            System.out.println("UDP CLIENT: just sent out a packet with sequence number " + dataInt + " to the server");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void sendData(int byteSize){
        try {
            byte[] sendData = ByteBuffer.allocate(byteSize).array();
            InetAddress IPAddress = InetAddress.getByName(HOST);
            DatagramPacket sendPacket = new DatagramPacket (sendData, sendData.length, IPAddress, PORT);
            startTime = System.nanoTime();
            clientSocket.send(sendPacket);
            System.out.println("UDP CLIENT: just sent out a packet with " + sendData.length + " bytes to the server");
        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void receiveData(int byteSize, boolean sequenced){

        try {
            byte[] receiveData = new byte[byteSize];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            clientSocket.receive(receivePacket);
            receiveData = receivePacket.getData();
            endTime = System.nanoTime();
            if(sequenced == true) {
                int sequenceNumber = ByteBuffer.wrap(receiveData).getInt();
                System.out.println("UDP Client: " + "received a packet with sequence number " + sequenceNumber + " back from the server.");

            }else{

                System.out.println("UDP CLIENT: " + "received a packet with  " + receiveData.length + " bytes back from the server..");
            }
            elapsed = endTime - startTime;
            System.out.println("UDP CLIENT: The connection took " + elapsed + " nanoseconds / " + elapsed / 1000000 + " milliseconds.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static void receiveOneByteData(boolean sequenced){

        try {
            byte[] receiveData = new byte[1];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            clientSocket.receive(receivePacket);
            receiveData = receivePacket.getData();
            endTime = System.nanoTime();
            if(sequenced == true) {
                int sequenceNumber = ByteBuffer.wrap(receiveData).getInt();
                System.out.println("UDP Client: " + "received a packet with sequence number " + sequenceNumber + " back from the server.");

            }else{

                System.out.println("UDP CLIENT: " + "received a packet with  " + receiveData.length + " bytes back from the server..");
            }
            elapsed = endTime - startTime;
            System.out.println("UDP CLIENT: The connection took " + elapsed + " nanoseconds / " + elapsed / 1000000 + " milliseconds.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static void cleanUp(){
        clientSocket.close();
    }




    //true = packing trial number
    //false = no number packed
    public static void runTrial(int trial, int byteSize, boolean sequenceNumber){

        long timeTaken = 0;
        System.out.println("====================Start Running Trials====================");
        for (int i = 1; i <= trial; i++) {
            initializeConnection();
            System.out.println("Running Trial " + i + " :");

            if(sequenceNumber == false) {
                sendData(byteSize);
            }else{
                sendData(byteSize,i);
            }
            receiveData(byteSize, sequenceNumber);
            timeTaken += elapsed;
        }
        long averageRTT = timeTaken / trial;
        System.out.println("==================Finished Running Trials ==================");
        // System.out.println("Finished running " + trial + " times.");

        System.out.println("UDP SUMMARY: Average RTT for " + trial + " trials is " + averageRTT + " nanoseconds / " +
                (averageRTT / 1000000) + " milliseconds.");

        cleanUp();



    }
    public static void runTrialWithOneByteAcknowledgement(int trial, int byteSize, boolean sequenceNumber){

        long timeTaken = 0;
        System.out.println("====================Start Running Trials====================");
        for (int i = 1; i <= trial; i++) {
            initializeConnection();
            System.out.println("Running Trial " + i + " :");
            //if you wnat number sequenced change here
            if(sequenceNumber == false) {
                sendData(byteSize);
            }else{
                sendData(byteSize,i);
            }
            receiveOneByteData(sequenceNumber);
            timeTaken += elapsed;
        }
        long averageRTT = timeTaken / trial;
        System.out.println("==================Finished Running Trials ==================");
        // System.out.println("Finished running " + trial + " times.");
        System.out.println("UDP SUMMARY: Total time taken is " + timeTaken + " nanoseconds / " + timeTaken/1000000 +
                " milliseconds");
        System.out.println("UDP SUMMARY: Average RTT for " + trial + " trials is " + averageRTT + " nanoseconds / " +
                (averageRTT / 1000000) + " milliseconds.");

        cleanUp();



    }


 public static void main(String args[])  {
    // runTrial(10,1);
     //byteSize and boolean need to be changed in the UDP Server as well.
     int byteSize = 64;
     runTrial(10,byteSize,false);
    // runTrialWithOneByteAcknowledgement(256, byteSize, false);
 }
}



