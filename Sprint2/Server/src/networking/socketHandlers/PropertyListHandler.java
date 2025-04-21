package networking.socketHandlers;

import java.sql.Date;
import java.sql.SQLException;

public interface PropertyListHandler
{
  void getAvailableProperties() throws SQLException;

  void isAvailable(Date startDate, Date endDate, int propertyId);

  void setDates(Date[] dates);
}
