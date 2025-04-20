package model;

import persistence.daos.properties.PropertyDAO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class PropertyListModelManager implements PropertyListModel
{
  private ArrayList<Property> properties = new ArrayList<>();
  private PropertyDAO propertyDAO;
  private PropertyChangeSupport support;

  public PropertyListModelManager(PropertyDAO propertyDAO)
  {
    this.propertyDAO = propertyDAO;
    this.support = new PropertyChangeSupport(this);
    this.support = new PropertyChangeSupport(this);
  }

  @Override public Property getByID(int id)
  {
    //TODO: Watch out for the difference between id and index (ID does not represent the index of the list)
    return properties.get(id);
  }

  @Override public boolean isAvailable(Date startDate, Date endDate, int id)
  {
    Property property = getByID(id);
    if (property != null)
    {
//      TODO : Remove the available method from the Property class
    }
    return false;
  }

  public void getAvailableProperties(Date startDate, Date endDate) throws SQLException
  {
    properties = (ArrayList<Property>) propertyDAO.getAvailableProperties(startDate, endDate);
    support.firePropertyChange("propertyList", null, properties);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void addPropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null || eventName.isEmpty())
    {
      addPropertyChangeListener(listener);
    }
    else
    {
      support.addPropertyChangeListener(eventName, listener);
    }
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null || eventName.isEmpty())
    {
      removePropertyChangeListener(listener);
    }
    else
    {
      support.removePropertyChangeListener(eventName, listener);
    }
  }
}