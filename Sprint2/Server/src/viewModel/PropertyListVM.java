package viewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Property;
import model.PropertyListModel;
import model.PropertyListModelManager;

public class PropertyListVM {
    private ObservableList<Property> properties;
    private IntegerProperty propertyID;
    private PropertyListModel bookingModel;

    public PropertyListVM(PropertyListModel bookingModel) {
        this.bookingModel = bookingModel;
        this.properties = FXCollections.observableArrayList();
        this.propertyID = new SimpleIntegerProperty(-1);
        loadProperties();
    }

    public void loadProperties() {
        properties.clear();
        if (bookingModel instanceof PropertyListModelManager) {
            properties.addAll(((PropertyListModelManager) bookingModel).getAllProperties());
        }
    }

    public ObservableList<Property> getPropertyList() {
        return properties;
    }

    public IntegerProperty getSelectedIndexProperty() {
        return propertyID;
    }
}