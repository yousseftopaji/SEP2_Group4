package startup;

import model.PropertyListModel;
import model.PropertyListModelManager;
import networking.MainSocketHandler;
import persistence.daos.properties.PropertyDAO;
import persistence.daos.properties.PropertyDAOImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server
{
  final static int PORT = 8080;

  public static void main(String[] args) throws SQLException, IOException
  {
    System.out.println("Server started on port: " + PORT);

    //Create a server socket
    ServerSocket serverSocket = new ServerSocket(PORT);

    while(true)
    {
      System.out.println("Waiting for client connection...");

      //Accept a client connection
      Socket socket = serverSocket.accept();
      String clientAddress = socket.getInetAddress().getHostAddress();
      System.out.println("Client connected from: " + clientAddress);

      //Create a property list model
      PropertyDAO propertyDAO = PropertyDAOImpl.getInstance();
      PropertyListModel propertyListModel = new PropertyListModelManager(
          propertyDAO);

      Thread clientThread = new Thread(new MainSocketHandler(socket,
          propertyListModel));
      clientThread.start();
    }
  }
}
