package ui.register;

import javafx.beans.Observable;
import javafx.beans.property.*;

public class RegisterVM
{
  private final StringProperty emailProp = new SimpleStringProperty();
  private final StringProperty pwProp = new SimpleStringProperty();
  private final StringProperty repeatProp = new SimpleStringProperty();
  private final StringProperty msgProp = new SimpleStringProperty();
  private final BooleanProperty enableRegisterButtonProp = new SimpleBooleanProperty(
      true);

  public RegisterVM()
  {
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

  private boolean containsUpperCaseAndLowerCase(String password)
  {
    boolean hasUpperCase = false;
    boolean hasLowerCase = false;

    for (char c : password.toCharArray())
    {
      if (Character.isUpperCase(c))
      {
        hasUpperCase = true;
      }
      if (Character.isLowerCase(c))
      {
        hasLowerCase = true;
      }
      if (hasUpperCase && hasLowerCase)
      {
        return true;
      }
    }
    return false;
  }

  public void register()
  {
    String email = emailProp.get();
    String password = pwProp.get();
    String repeat = repeatProp.get();
    if (email == null || email.isEmpty())
    {
      msgProp.set("Email cannot be empty");
      return;
    }
    if (!(email.contains("@") && email.endsWith(".com")))
    {
      msgProp.set("Email must be in a correct format.");
      return;
    }
    if (password == null || password.isEmpty())
    {
      msgProp.set("Password cannot be empty");
      return;
    }
    if (!(repeat.equals(password)))
    {
      msgProp.set("Passwords do not match");
      return;
    }
    if (repeat.length() < 8)
    {
      msgProp.set("Passwords must be 8 or greater character length.");
      return;
    }
    if (!containsUpperCaseAndLowerCase(repeat))
    {
      msgProp.set(
          "Passwords must have atleast one upper and atleast one lower character.");
      return;
    }
    if (!containsNumberLetterAndSymbol(repeat))
    {
      msgProp.set(
          "password must have at least one number, one letter and one symbol");
      return;
    }
//    TODO fix this
//    String resultMsg = authService.registerUser(new User(email, password));
    String resultMsg = "Ok"; // Placeholder for the actual registration logic, to be replaced with the actual call to the authentication service
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

  private boolean containsNumberLetterAndSymbol(String pw)
  {
    boolean hasNumber = false;
    boolean hasSymbol = false;
    boolean hasLetter = false;
    for (char c : pw.toCharArray())
    {
      if (Character.isDigit(c))
      {
        hasNumber = true;
      }
      if (Character.isLetter(c))
      {
        hasLetter = true;
      }
      if (!Character.isLetterOrDigit(c))
      {
        hasSymbol = true;
      }
      if (hasNumber && hasLetter && hasSymbol)
      {
        return true;
      }
    }
    return false;
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
