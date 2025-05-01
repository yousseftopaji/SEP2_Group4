package ui.register;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import startup.viewHandler.ViewHandler;

public class RegisterCtrl
{
  @FXML private TextField emailField;
  @FXML private TextField passwordField;
  @FXML private TextField repeatField;
  @FXML private Label messageLabel;
  @FXML private Button buttonRegister;

  private RegisterVM viewModel;
  private ViewHandler viewHandler;

  public RegisterCtrl()
  {
  }

  public void initialize(RegisterVM vm, ViewHandler vh)
  {
    this.viewModel = vm;
    this.viewHandler = vh;
    emailField.textProperty().bindBidirectional(viewModel.emailProperty());
    passwordField.textProperty()
        .bindBidirectional(viewModel.passwordProperty());
    repeatField.textProperty().bindBidirectional(viewModel.repeatProperty());
    messageLabel.textProperty().bind(viewModel.messageProperty());
    buttonRegister.disableProperty()
        .bind(viewModel.enableRegisterButtonProperty());
  }

  public void onBack()
  {
    viewHandler.showView(ViewHandler.ViewType.WELCOME);
  }

  public void onRegister()
  {
    viewModel.register();
    viewHandler.showView(ViewHandler.ViewType.LOGIN);
  }
}
