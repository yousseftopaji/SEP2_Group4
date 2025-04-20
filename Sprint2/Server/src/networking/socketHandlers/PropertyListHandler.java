package networking.socketHandlers;

import com.google.gson.Gson;
import model.Property;
import model.PropertyListModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class PropertyListHandler implements PropertyChangeListener
{
  private Socket socket;
  private List<Property> properties;
  private Gson gson;
  private PrintWriter out;
  private BufferedReader in;
  private PropertyListModel propertyListModel;

  public PropertyListHandler(Socket socket, PropertyListModel propertyListModel) throws SQLException, IOException
  {
    // Initialize the socket and reader/writer
    this.socket = socket;
    gson = new Gson();
    out = new PrintWriter(socket.getOutputStream(), true);
    in = new BufferedReader(
        new java.io.InputStreamReader(socket.getInputStream()));

    // Initialize the property list model and add this handler as a listener
    this.propertyListModel = propertyListModel;
    propertyListModel.addPropertyChangeListener(this);

    // Get the initial list of properties (stimulate a change event)
//    TODO : put startDate and endDate in the constructor
    propertyListModel.getAvailableProperties(null, null);
  }

  public void run() throws SQLException
  {
    //Listen to the change event
    String jsonResponse = gson.toJson(properties);
    out.println(jsonResponse);
    out.flush();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    // Handle the property change event
    String name = evt.getPropertyName();
    if (name.equals("propertyList"))
    {
      properties = (List<Property>) evt.getNewValue();
      try
      {
        run();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      System.out.println("Unknown property change event: " + name);
    }
  }
}
