package Java_Tong_hieu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerUDP {
    public static void main(String[] args) throws IOException, SocketException {
        DatagramSocket server = new DatagramSocket(9999);
        byte[] receive_data = new byte[1024];
        DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);
        
        server.receive(receive_packet);
        String s_receive = new String(receive_packet.getData(), 0, receive_packet.getLength());

        // "tong 3 5" -> ["tong", "3", "5"]; a = 3, b = 5
        String[] data = s_receive.split(" ");
        int result = 0;

        int a = Integer.parseInt(data[1]);
        int b = Integer.parseInt(data[2]);
        switch(data[0]) {
            case "sum": {;
                result = a + b;
                break;
            }
            case "Subtraction": {
                result = a - b;
                break;
            }
            default: {
                break;
            }
        }
        byte[] send_data = String.valueOf(result).getBytes();
        DatagramPacket send_packet = new DatagramPacket(send_data, send_data.length, receive_packet.getAddress(), receive_packet.getPort());
        server.send(send_packet);
        server.close();
    }
}
