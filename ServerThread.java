import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class ServerThread extends Thread {

    MulticastSocket socket = null;
    DatagramPacket inPacket = null;
    byte[] inBuf = new byte[256];

    // public ServerThread(Socket clientSocket) {
    // this.clientSocket = clientSocket;
    // }
    @SuppressWarnings("deprecation")
    public void run() {
        try {
            socket = new MulticastSocket(8888);
            InetAddress address = InetAddress.getByName("224.0.0.2");
            socket.joinGroup(address);

            while (true) {
                inPacket = new DatagramPacket(inBuf, inBuf.length);
                socket.receive(inPacket);
                String msg = new String(inBuf, 0, inPacket.getLength());
                // if (msg != InetAddress.getLocalHost())
                if (msg.startsWith(InetAddress.getLocalHost().toString()))
                    continue;
                // System.out.println("From " + inPacket.getAddress() + " " + msg);
                System.out.println(msg);

            }
            // Close the output stream, close the input stream, close the socket.
        } catch (IOException e) {
        }
    }

}
