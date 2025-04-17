package ui.propertyList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Property;
import ui.viewHandler.ViewHandler;

public class PropertyListController
{
  @FXML private TableView<Property> table;
  @FXML private TableColumn<Property, String> locationColumn;
  @FXML private TableColumn<Property, Double> pricePerNightColumn;
  @FXML private TableColumn<Property, String> facilitiesColumn;
  @FXML private Button selectButton;
  @FXML private Label errorMsg;

  private PropertyListVM propertyListVM;
  private ViewHandler viewHandler;

  public PropertyListController()
  {
  }

  public void initialize(PropertyListVM propertyListVM, ViewHandler viewHandler)
  {
    this.propertyListVM = propertyListVM;
    this.viewHandler = viewHandler;

    // Bind the TableView to the ViewModel
    table.setItems(propertyListVM.getPropertyList());

    // Set up the columns
    locationColumn.setCellValueFactory(
        data -> new SimpleStringProperty(data.getValue().getLocation()));
    pricePerNightColumn.setCellValueFactory(data -> new SimpleDoubleProperty(
        data.getValue().getPricePerNight()).asObject());
    facilitiesColumn.setCellValueFactory(data -> new SimpleStringProperty(
        data.getValue().getFacilities().toString()));

    // Bind the selected property to the ViewModel
    propertyListVM.bindSelectedProperty(table.getSelectionModel().selectedItemProperty());

    // Bind the error message to the ViewModel
    errorMsg.textProperty().bind(propertyListVM.getErrorMsgProperty());
  }

  public void onSelectProperty()
  {
    Property selected = propertyListVM.getSelectedProperty().get();

    if (selected != null)
    {
      viewHandler.openBookingView(selected);
    }
  }
}
