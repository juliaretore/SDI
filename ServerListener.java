import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    // The server socket.
    private static ServerSocket serverSocket = null;
    // The client socket.
    private static Socket clientSocket = null;
    private static final int maxClientsCount = 10;
    private static final ClientThread[] threads = new ClientThread[maxClientsCount];
    private static int cont = 0;

    /**
     * @param args
     */
    public static void main(String[] args) {
        int portNumber = 2223;
        if (args.length < 1) {
            System.out.println("Server - Now using port number=" + portNumber);
        } else {
            portNumber = Integer.valueOf(args[0]).intValue();
        }

        /*
         * Open a server socket on the portNumber (default 2222). Note that we can
         * not choose a port less than 1023 if we are not privileged users (root).
         */
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }

        /*
         * Create a client socket for each connection and pass it to a new client
         * thread.
         */
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                (threads[cont] = new ClientThread(clientSocket)).start();
                cont++;
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}