/**
 * Created by Yingying Xia on 2018/2/20.
 */

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

class UDPServerWithSeq{

    public static void listen(int byteSize, boolean withSequence){
        try {
            int PORT = 2705;
           // int byteSize = 1;
            while (true) {
                DatagramSocket serverSocket = new DatagramSocket(PORT);
                byte[] receiveData = new byte[byteSize];


                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                serverSocket.receive(receivePacket);
             //   System.out.println("received data length " + receivePacket.getData().length);
                receiveData = receivePacket.getData();
                int sequenceNumber = 0;
                if(withSequence==true) {
                    sequenceNumber = ByteBuffer.wrap(receiveData).getInt();
                    System.out.println("UDP SERVER: " + "received a packet with sequence number " + sequenceNumber);
                }else{
                    System.out.println("UDP Server: received a packet with " + receiveData.length + " bytes.");
                }

                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                byte[] sendData;
                if(withSequence == true) {
                    sendData = ByteBuffer.allocate(byteSize).putInt(sequenceNumber).array();
                    DatagramPacket sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    System.out.println("UDP SERVER: sending back packet with sequence number " + sequenceNumber);
                    serverSocket.send(sendPacket);
                    serverSocket.close();
                }else{
                    sendData = ByteBuffer.allocate(byteSize).array();
                    DatagramPacket sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    System.out.println("UDP SERVER: sending back packet with " + sendData.length + " bytes.");
                    serverSocket.send(sendPacket);
                    serverSocket.close();
                }


            }
        }catch(SocketException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void listenWithOneByteAcknowledgement(int byteSize, boolean withSequence) {
        try {
            int PORT = 2705;
            // int byteSize = 1;
            while (true) {
                DatagramSocket serverSocket = new DatagramSocket(PORT);
                byte[] receiveData = new byte[byteSize];


                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                serverSocket.receive(receivePacket);
                //   System.out.println("received data length " + receivePacket.getData().length);
                receiveData = receivePacket.getData();
                int sequenceNumber = 0;
                if (withSequence == true) {
                    sequenceNumber = ByteBuffer.wrap(receiveData).getInt();
                    System.out.println("UDP SERVER: " + "received a packet with sequence number " + sequenceNumber);
                } else {
                    System.out.println("UDP Server: received a packet with " + receiveData.length + " bytes.");
                }

                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                if (withSequence == true) {
                    byte [] sendData = ByteBuffer.allocate(1).putInt(sequenceNumber).array();
                    DatagramPacket sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    System.out.println("UDP SERVER: sending back packet with sequence number " + sequenceNumber);
                    serverSocket.send(sendPacket);
                    serverSocket.close();
                } else {
                    byte [] sendData = ByteBuffer.allocate(1).array();
                    DatagramPacket sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    System.out.println("UDP SERVER: sending back packet with " + sendData.length + " bytes.");
                    serverSocket.send(sendPacket);
                    serverSocket.close();
                }


            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String args[]) {
        //byteSize of each datagram packet, whether contains number


        int byteSize = 64;
        listen(byteSize,false);
    }

}


