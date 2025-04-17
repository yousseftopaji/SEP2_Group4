package ui.propertyList;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Property;
import model.PropertyListModel;
import model.PropertyListModelManager;

public class PropertyListVM
{
  private ObservableList<Property> properties;
  private IntegerProperty propertyID;
  private PropertyListModel bookingModel;
  private SimpleObjectProperty<Property> selectedProperty;
  private StringProperty errorMsg;

  public PropertyListVM(PropertyListModel bookingModel)
  {
    this.bookingModel = bookingModel;
    this.properties = FXCollections.observableArrayList();
    this.propertyID = new SimpleIntegerProperty(-1);
    this.selectedProperty = new SimpleObjectProperty<>();
    this.errorMsg = new SimpleStringProperty();
    loadProperties();
  }

  public void loadProperties()
  {
    properties.clear();
    if (bookingModel instanceof PropertyListModelManager)
    {
      properties.addAll(bookingModel.getAllProperties());
    }
  }

  public ObservableList<Property> getPropertyList()
  {
    return properties;
  }

  public IntegerProperty getSelectedIndexProperty()
  {
    return propertyID;
  }

  public void bindSelectedProperty(ReadOnlyObjectProperty<Property> selectedPropertyFromTable) {
    selectedProperty.bind(selectedPropertyFromTable);
  }

  public SimpleObjectProperty<Property> getSelectedProperty()
  {
    if (selectedProperty.getValue() == null)
    {
      errorMsg.set("No property selected");
    }
    return selectedProperty;
  }

  public StringProperty getErrorMsgProperty()
  {
    return errorMsg;
  }
}