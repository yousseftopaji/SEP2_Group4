package persistence.daos.properties;

import model.Facilities;
import model.Property;

import java.sql.SQLException;
import java.util.List;

public interface PropertyDAO
{
  Property create(int id, String location, double pricePerNight,
      boolean availability, Facilities facilities) throws SQLException;
  Property readByID(int id) throws SQLException;
  List<Property> readByLocation(String location) throws SQLException;
  void update(Property property) throws SQLException;
  void delete(int id) throws SQLException;
}
