import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread extends Thread {
  private DataInputStream is = null;
  private PrintStream os = null;
  private Socket clientSocket = null;

  // Multicast
  DatagramSocket socket = null;
  DatagramPacket outPacket = null;
  byte[] outBuf;
  final int PORT = 8888;

  public ClientThread(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  public void sendMessage(String msg) throws IOException {
    msg = InetAddress.getLocalHost() + ": " + msg;
    outBuf = msg.getBytes();

    InetAddress address = InetAddress.getByName("224.0.0.2");
    outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);
    socket.send(outPacket);
  }

  @SuppressWarnings("deprecation")
  public void run() {
    try {
      // Create input and output streams for this client.
      is = new DataInputStream(clientSocket.getInputStream());
      os = new PrintStream(clientSocket.getOutputStream());
      socket = new DatagramSocket();
      os.println("Enter your name.");
      String name = is.readLine().trim();
      os.println("ACK - " + name + ": Voce entrou no chat.");
      sendMessage(name + " entrou no chat!");

      while (true) {
        String line = is.readLine();
        if (line == null)
          continue;
        if (line.startsWith("/quit"))
          break;
        sendMessage("<" + name + "> - " + line);
      }
      sendMessage(name + " saiu do chat! ");
      os.println("/quit");
      // Close the output stream, close the input stream, close the socket.
      is.close();
      os.close();
      clientSocket.close();

    } catch (IOException e) {
    }
  }

}
