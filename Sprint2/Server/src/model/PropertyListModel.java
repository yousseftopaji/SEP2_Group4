package model;

import Observer.PropertyChangeSubject;

import java.sql.SQLException;
import java.sql.Date;

public interface PropertyListModel extends PropertyChangeSubject
{
    Property getByID(int id);
    boolean isAvailable(Date startDate, Date endDate, int id);
    void getAvailableProperties(Date startDate, Date endDate) throws SQLException;
}