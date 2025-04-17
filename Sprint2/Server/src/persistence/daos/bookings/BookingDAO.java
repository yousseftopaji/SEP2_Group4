package persistence.daos.bookings;

import model.Booking;

import java.sql.Date;
import java.util.List;

public interface BookingDAO
{
  Booking create(Date startDate, Date endDate, int propertyId, int userId) throws Exception;
  Booking read(Date startDate, int propertyId, int userId) throws Exception;
  Booking update(Date startDate,Date endDate, int propertyId, int userId) throws Exception;
  void delete(Date startDate, int propertyId, int userId) throws Exception;
  List<Booking> getAllBookings() throws Exception;
}
