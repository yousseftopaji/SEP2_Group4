package ui.login;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import dtos.LoginRequest;

public class LoginVM {
    private final StringProperty emailProp = new SimpleStringProperty();
    private final StringProperty pwProp = new SimpleStringProperty();
    private final StringProperty msgProp = new SimpleStringProperty();
    private final BooleanProperty loginBtnEnabledProp = new SimpleBooleanProperty();

    public LoginVM(){
        emailProp.addListener(this::updateLoginButtonState);
        pwProp.addListener(this::updateLoginButtonState);
    }

    private void updateLoginButtonState(Observable observable) {
        boolean shouldDisable = emailProp.get() == null || emailProp.get().isEmpty() || pwProp.get() == null || pwProp.get().isEmpty();
        loginBtnEnabledProp.set(!shouldDisable);
    }

    public void login(){
        String email = emailProp.get();
        String password = pwProp.get();

        if (email == null || email.isEmpty()) {
            msgProp.set("Email cannot be empty");
            return;
        }
        if (password == null || password.isEmpty()) {
            msgProp.set("Password cannot be empty");
            return;
        }
         String resultMsg = "Ok";
         if(resultMsg.equals("Ok")){
             msgProp.set("Success");
             //clearfields
             emailProp.set("");
             pwProp.set("");
         }else {
             msgProp.set(resultMsg);
         }
    }
    public StringProperty emailProperty() {
        return emailProp;
    }
    public StringProperty passwordProperty(){
        return pwProp;
    }
    public StringProperty messageProperty(){
        return msgProp;
    }
    public BooleanProperty getLoginBtnEnabledProp() {
        //   if ("Success".equals(msgProp)) {
            return loginBtnEnabledProp;
    }

}
