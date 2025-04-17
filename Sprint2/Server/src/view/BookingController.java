package view;

import viewModel.BookingVM;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BookingController
{
  @FXML private TextField propertyIdField;

  @FXML private TextField customerNameField;

  @FXML private TextField bookingDateField;

  @FXML private TextArea location;

  @FXML private Button submitButton;

  private final BookingVM bookingVM;

  public BookingController(BookingVM bookingVM)
  {
    this.bookingVM = bookingVM;
  }

  // Add methods to bind data or handle events
}