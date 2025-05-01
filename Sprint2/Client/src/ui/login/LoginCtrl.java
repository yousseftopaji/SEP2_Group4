package ui.login;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.viewHandler.ViewHandler;

public class LoginCtrl
{
  @FXML private TextField emailField;
  @FXML private TextField passwordField;
  @FXML private Label messageLabel;

  private LoginVM viewModel;
  private ViewHandler viewHandler;

  public LoginCtrl()
  {
  }

  public void initialize(LoginVM vm, ViewHandler vh)
  {
    this.viewModel = vm;
    this.viewHandler = vh;
    emailField.textProperty().bindBidirectional(viewModel.emailProperty());
    passwordField.textProperty()
        .bindBidirectional(viewModel.passwordProperty());
    messageLabel.textProperty().bind(viewModel.messageProperty());
  }

  public void onBack()
  {
    viewHandler.showView(ViewHandler.ViewType.WELCOME);
  }

  public void onLogin()
  {
    viewModel.login();
  }
}
