package ui.register;

import javafx.beans.Observable;
import javafx.beans.property.*;

public class RegisterVM
{
  private final StringProperty usernameProp = new SimpleStringProperty();
  private final StringProperty emailProp = new SimpleStringProperty();
  private final StringProperty pwProp = new SimpleStringProperty();
  private final StringProperty repeatProp = new SimpleStringProperty();
  private final StringProperty msgProp = new SimpleStringProperty();
  private final BooleanProperty enableRegisterButtonProp = new SimpleBooleanProperty(
      true);

  public RegisterVM()
  {
    usernameProp.addListener(this::updateRegisterButtonState);
    emailProp.addListener(this::updateRegisterButtonState);
    pwProp.addListener(this::updateRegisterButtonState);
    repeatProp.addListener(this::updateRegisterButtonState);
  }

  private void updateRegisterButtonState(Observable observable)
  {
    boolean shouldDisable = emailProp.get() == null || emailProp.get().isEmpty()
        || pwProp.get() == null || pwProp.get().isEmpty()
        || repeatProp.get() == null || repeatProp.get().isEmpty();
    enableRegisterButtonProp.set(shouldDisable);
  }

  public void register()
  {
    String username = usernameProp.get();
    String email = emailProp.get();
    String password = pwProp.get();
    String repeat = repeatProp.get();

    //    TODO fix this
//    String resultMsg = client.registerUser(
//        new User(username, email, password));
    String resultMsg = "Ok";
    if (resultMsg.equals("Ok"))
    {
      System.out.println("User registered");
      msgProp.set("Success");
      //clear fields
      emailProp.set("");
      pwProp.set("");
      repeatProp.set("");
    }
    else
    {
      msgProp.set(resultMsg);
    }
  }

  public StringProperty emailProperty()
  {
    return emailProp;
  }

  public StringProperty passwordProperty()
  {
    return pwProp;
  }

  public StringProperty repeatProperty()
  {
    return repeatProp;
  }

  public StringProperty messageProperty()
  {
    return msgProp;
  }

  public BooleanProperty enableRegisterButtonProperty()
  {
    return enableRegisterButtonProp;
  }
}
