package viewModel;

import javafx.beans.property.*;
import model.Property;
import model.PropertyListModel;

import java.util.Date;

public class BookingVM  {
    private IntegerProperty propertyID;
    private StringProperty location;
    private StringProperty propertyFacilities;
    private DoubleProperty pricePerNight;
    private ObjectProperty<Date> changeEndDate;
    private BooleanProperty availability;
    private PropertyListModel bookingModel;

    public BookingVM(PropertyListModel bookingModel) {
        this.bookingModel = bookingModel;
        propertyID = new SimpleIntegerProperty();
        location = new SimpleStringProperty();
        propertyFacilities = new SimpleStringProperty();
        pricePerNight = new SimpleDoubleProperty();
        changeEndDate = new SimpleObjectProperty<>();
        availability = new SimpleBooleanProperty();

        // Bind property changes
    }

    private void updateProperty(int id) {
        Property property = bookingModel.getByID(id);
        if (property != null) {
            location.set(property.getLocation());
            propertyFacilities.set(property.getFacilities().toString());
            pricePerNight.set(property.getPricePerNight());
            availability.set(property.getAvailability());
        }
    }

    public IntegerProperty propertyID() {
        return propertyID;
    }

    public StringProperty location() {
        return location;
    }

    public StringProperty propertyFacilities() {
        return propertyFacilities;
    }

    public DoubleProperty pricePerNight() {
        return pricePerNight;
    }

    public ObjectProperty<Date> changeEndDate() {
        return changeEndDate;
    }

    public BooleanProperty availability() {
        return availability;
    }
}