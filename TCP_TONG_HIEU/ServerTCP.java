package TCP_TONG_HIEU;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket server = new ServerSocket(9999);
            Socket socket = server.accept();
            System.out.println("Connected");
            DataOutputStream send_data = new DataOutputStream(socket.getOutputStream());
            DataInputStream receive_data = new DataInputStream(socket.getInputStream());
            int a = receive_data.readInt();
            int b = receive_data.readInt();
            int c = a + b;
            send_data.writeInt(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
