package ui.specifyDates;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import ui.viewHandler.ViewHandler;

public class SpecifyDatesController
{
  private @FXML DatePicker startDate;
  private @FXML DatePicker endDate;
  private @FXML Label errMsg;
  private @FXML Button submitButton;

  private SpecifyDatesVM specifyDatesVM;
  private ViewHandler viewHandler;

  public SpecifyDatesController()
  {
  }

  public void initialize(SpecifyDatesVM specifyDatesVM, ViewHandler viewHandler)
  {
    this.specifyDatesVM = specifyDatesVM;
    this.viewHandler = viewHandler;

    startDate.setValue(specifyDatesVM.getStartDate());
    endDate.setValue(specifyDatesVM.getEndDate());
    errMsg.textProperty().bind(specifyDatesVM.getErrorMsgProperty());
    // Disable submit button if there's an error
    submitButton.disableProperty().bind(specifyDatesVM.isValidProperty().not());

    // Live validation as the user changes the dates
    startDate.valueProperty().addListener((obs, oldVal, newVal) -> {
      specifyDatesVM.setStartDate(newVal);
    });

    endDate.valueProperty().addListener((obs, oldVal, newVal) -> {
      specifyDatesVM.setEndDate(newVal);
    });
  }

  public void onSubmitButtonClicked()
  {
    specifyDatesVM.setStartDate(startDate.getValue());
    System.out.println("Start date: " + startDate.getValue());
    specifyDatesVM.setEndDate(endDate.getValue());
    System.out.println("End date: " + endDate.getValue());
    if (endDate.getValue().isBefore(startDate.getValue()))
    {
      specifyDatesVM.getErrorMsgProperty().set("End date cannot be before start date");
      return;
    }
    viewHandler.openPropertyListView();
  }
}
