package persistence.daos.properties;

import model.Facilities;
import model.Property;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAOImpl implements PropertyDAO
{
  private static PropertyDAOImpl instance;

  private PropertyDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized PropertyDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new PropertyDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5433/postgres?currentSchema=summerhouse_rental_system",
        "postgres", "viaviavia");
  }

  @Override public Property create(int id, String location,
      double pricePerNight, boolean availability, Facilities facilities) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      //Check if the connection is established
      if (connection == null || connection.isClosed())
      {
        throw new SQLException(
            "Failed to establish a connection to the database.");
      }

      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO property (location, pricePerNight, availability, kitchen, internet, swimmingPool, dishwasher, laundryMachine) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, location);
      statement.setDouble(2, pricePerNight);
      statement.setBoolean(3, availability);
      statement.setBoolean(4, facilities.getKitchen());
      statement.setBoolean(5, facilities.getInternet());
      statement.setBoolean(6, facilities.getSwimmingPool());
      statement.setBoolean(7, facilities.getDishWasher());
      statement.setBoolean(8, facilities.getLaundryMachine());
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        System.out.println("id: " + keys.getInt("propertyid"));
        return new Property(keys.getInt("propertyid"), location, pricePerNight,
            availability, facilities);
      }
      else
      {
        throw new SQLException("Failed to create property, no ID generated.");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw e;
    }
  }

  @Override public Property readByID(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "Select * FROM property Where propertyid = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String location = resultSet.getString("location");
        double pricePerNight = resultSet.getDouble("pricepernight");
        boolean availability = resultSet.getBoolean("availability");
        boolean kitchen = resultSet.getBoolean("kitchen");
        boolean internet = resultSet.getBoolean("internet");
        boolean swimmingPool = resultSet.getBoolean("swimmingPool");
        boolean dishWasher = resultSet.getBoolean("dishWasher");
        boolean laundryMachine = resultSet.getBoolean("laundryMachine");
        Property property = new Property(id, location, pricePerNight,
            availability, new Facilities(kitchen, internet, dishWasher, laundryMachine, swimmingPool));
        return property;
      }
      else
      {
        return null;
      }
    }
  }

  @Override public List<Property> readByLocation(String location)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM property WHERE location ILIKE ?");
      statement.setString(1, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Property> properties = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("propertyid");
        String loc = resultSet.getString("location");
        double pricePerNight = resultSet.getDouble("pricepernight");
        boolean availability = resultSet.getBoolean("availability");
        boolean kitchen = resultSet.getBoolean("kitchen");
        boolean internet = resultSet.getBoolean("internet");
        boolean swimmingPool = resultSet.getBoolean("swimmingPool");
        boolean dishWasher = resultSet.getBoolean("dishWasher");
        boolean laundryMachine = resultSet.getBoolean("laundryMachine");
        Property property = new Property(id, loc, pricePerNight, availability,
            new Facilities(kitchen, internet, dishWasher, laundryMachine, swimmingPool));
        properties.add(property);
      }
      return properties;
    }
  }

  @Override public void update(Property property) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE property SET location = ?, pricePerNight = ?, availability = ?, kitchen = ?, internet = ?, swimmingPool = ?, dishwasher = ?, laundryMachine = ? WHERE propertyid = ?");
      statement.setString(1, property.getLocation());
      statement.setDouble(2, property.getPricePerNight());
      statement.setBoolean(3, property.getAvailability());
      statement.setBoolean(4, property.getFacilities().getKitchen());
      statement.setBoolean(5, property.getFacilities().getInternet());
      statement.setBoolean(6, property.getFacilities().getSwimmingPool());
      statement.setBoolean(7, property.getFacilities().getDishWasher());
      statement.setBoolean(8,
          property.getFacilities().getLaundryMachine());
      statement.setInt(9, property.getId());
      statement.executeUpdate();
    }
  }

  @Override public void delete(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if (readByID(id) != null)
      {
        PreparedStatement statement = connection.prepareStatement(
            "DELETE FROM property WHERE propertyid = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
      }
      else
      {
        System.out.println("Property with id " + id + " does not exist.");
      }
    }
  }
}
