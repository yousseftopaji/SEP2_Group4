package model;

import dtos.Property;
import Observer.PropertyChangeSubject;

import java.sql.SQLException;
import java.sql.Date;

public interface PropertyListModel extends PropertyChangeSubject
{
    Property getByID(int id);
    void isAvailable(Date startDate, Date endDate, int id);
    void getAvailableProperties(Date startDate, Date endDate) throws SQLException;
}