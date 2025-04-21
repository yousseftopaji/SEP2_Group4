package networking;


import networking.socketHandlers.PropertyListHandler;
import utils.JsonParser;
import model.PropertyListModel;
import networking.socketHandlers.PropertyListHandlerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;

public class MainSocketHandler implements Runnable
{
  private final Socket socket;
  private final PropertyListModel propertyListModel;
  private final BufferedReader in;
  private final PrintWriter out;
  PropertyListHandler propertyListHandler;

  public MainSocketHandler(Socket socket, PropertyListModel propertyListModel)
      throws IOException, SQLException
  {
    // Initialize the socket and property list model
    this.socket = socket;
    this.propertyListModel = propertyListModel;
    in = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    propertyListHandler = new PropertyListHandlerImpl(socket, propertyListModel);
  }

  @Override public void run()
  {
    try
    {
      String clientRequest = in.readLine();
      if (clientRequest.equals("getAllProperties"))
      {
        // Read the dates from the client
        String datesJson = in.readLine();
        // Parse the dates from JSON
        Date[] dates = JsonParser.jsonToDates(datesJson);

        propertyListHandler.setDates(dates);
        propertyListHandler.getAvailableProperties();
      }
      else if (clientRequest.equals("isAvailable"))
      {
        int propertyID = Integer.parseInt(in.readLine());

        // Read the start and end dates from the client
        String datesJson = in.readLine();

        // Parse the dates from JSON
        Date[] dates = JsonParser.jsonToDates(datesJson);

        // Check if the property is available
        propertyListHandler.isAvailable(dates[0], dates[1], propertyID);
      }
      else if (clientRequest.equals("getPropertyByID"))
      {
        //read the property ID from the client
        String propertyID = in.readLine();

        //TODO get the property by ID
      }
    }
    catch (IOException | SQLException e)
    {
      throw new RuntimeException(e);
    }
  }
}
