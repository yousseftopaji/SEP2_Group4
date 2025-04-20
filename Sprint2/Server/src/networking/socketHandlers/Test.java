package networking.socketHandlers;


import model.Facilities;
import persistence.daos.properties.PropertyDAO;
import persistence.daos.properties.PropertyDAOImpl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

public class Test
{
  public static void main(String[] args) throws SQLException
  {
    PropertyDAO propertyDAO = PropertyDAOImpl.getInstance();
  }
}
