package networking.socketHandlers;

import dtos.PropertyList;
import dtos.Property;
import model.PropertyListModel;
import utils.JsonParser;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class PropertyListHandlerImpl implements PropertyChangeListener, PropertyListHandler
{
  private Socket socket;
  private List<Property> properties;
  private PrintWriter out;
  private BufferedReader in;
  private PropertyListModel propertyListModel;
  private Date[] dates;

  public PropertyListHandlerImpl(Socket socket, PropertyListModel propertyListModel) throws SQLException, IOException
  {
    // Initialize the socket and reader/writer
    this.socket = socket;
    out = new PrintWriter(socket.getOutputStream(), true);
    in = new BufferedReader(
        new java.io.InputStreamReader(socket.getInputStream()));

    // Initialize the property list model and add this handler as a listener
    this.propertyListModel = propertyListModel;
    propertyListModel.addPropertyChangeListener(this);
  }

  @Override
  public void setDates(Date[] dates)
  {
    // Set the dates for the property list model
    this.dates = dates;
  }

  @Override
  public void getAvailableProperties() throws SQLException
  {
    // Get the available properties from the model
    propertyListModel.getAvailableProperties(dates[0], dates[1]);
  }

  @Override
  public void isAvailable(Date startDate, Date endDate, int propertyId)
  {
    // Check if the property is available
    propertyListModel.isAvailable(startDate,endDate,propertyId);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    // Handle the property change event
    String name = evt.getPropertyName();
    if (name.equals("propertyList"))
    {
      // Convert the list of properties to PropertyList DTO
      PropertyList propertyList = (PropertyList) evt.getNewValue();

      // Convert the PropertyList DTO to JSON
      String jsonResponse = JsonParser.propertiesToJson(propertyList);

      // Send the JSON response to the client
      out.println(jsonResponse);
      out.flush();
    }
    else if (name.equals("isAvailable"))
    {
      // Get the availability status
      boolean isAvailable = (boolean) evt.getNewValue();

      // Send the availability status to the client
      out.println(isAvailable);
      out.flush();
    }
    else
    {
      System.out.println("Unknown property change event: " + name);
    }
  }
}
