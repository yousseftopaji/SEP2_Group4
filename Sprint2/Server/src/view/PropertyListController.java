package view;

import viewModel.PropertyListVM;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PropertyListController
{
    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> locationColumn;

    @FXML
    private TableColumn<?, ?> pricePerNightColumn;

    @FXML
    private TableColumn<?, ?> facilitiesColumn;

    private final PropertyListVM propertyListVM;

    public PropertyListController(PropertyListVM propertyListVM) {
        this.propertyListVM = propertyListVM;
    }

    // Add methods to bind data or handle events
}