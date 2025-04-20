package persistence.daos;

import persistence.daos.bookings.BookingDAO;
import persistence.daos.bookings.BookingDAOImpl;
import persistence.daos.properties.PropertyDAO;
import persistence.daos.properties.PropertyDAOImpl;

import java.sql.Date;
import java.sql.SQLException;

public class Test
{
  public static void main(String[] args) throws SQLException
  {
    BookingDAO bookingDAO = BookingDAOImpl.getInstance();
    PropertyDAO propertyDAO = PropertyDAOImpl.getInstance();
  }
}
