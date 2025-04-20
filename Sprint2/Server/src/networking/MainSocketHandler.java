package networking;

import model.PropertyListModel;
import networking.socketHandlers.PropertyListHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class MainSocketHandler implements Runnable
{
  private final Socket socket;
  private final PropertyListModel propertyListModel;
  private final BufferedReader in;
  private final PrintWriter out;

  public MainSocketHandler(Socket socket, PropertyListModel propertyListModel) throws
      IOException
  {
    // Initialize the socket and property list model
    this.socket = socket;
    this.propertyListModel = propertyListModel;
    in = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
  }

  @Override public void run()
  {
    try
    {
      String clientRequest = in.readLine();
      if (clientRequest.equals("getAllProperties"))
      {
        PropertyListHandler propertyListHandler = new PropertyListHandler(socket, propertyListModel);
      }
    }
    catch (IOException | SQLException e)
    {
      throw new RuntimeException(e);
    }
  }
}
