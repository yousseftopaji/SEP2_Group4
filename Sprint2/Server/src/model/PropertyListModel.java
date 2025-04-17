package model;

import java.util.Date;
import java.util.List;

public interface PropertyListModel
{
    Property getByID(int id);
    boolean isAvailable(Date startDate, Date endDate, int id);
    List<Property> getAllProperties();
}