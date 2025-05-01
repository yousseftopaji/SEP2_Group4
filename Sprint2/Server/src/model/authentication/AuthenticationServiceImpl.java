package model.authentication;

import dtos.LoginRequest;
import dtos.User;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationServiceImpl implements AuthenticationService{
   private final List<User> users = new ArrayList<>();

    @Override
    public String loginUser(LoginRequest request) {
        for(User user: users){
            String email = user.getEmail();
            String password = user.getPassword();
        if(email!=null && email.equals(request.getEmail())){
                if(password!=null && password.equals(request.getPassword())){
                    return "Ok";
                }
                return "Incorrect password.";
            }
        }
        return "Email not found.";
    }
    @Override
    public String registerUser(User newUser) {
        String newEmail = newUser.getEmail();
        String newPassword = newUser.getPassword();
        if (newEmail.isEmpty() || newEmail==null) {
            return "Email cannot be empty";
        }
        if (!newEmail.contains("@") && !newEmail.endsWith(".com")) {
            return "Email must be in a correct format.";
        }
        for(User user: users) {
            if (user.getEmail() != null && newEmail.equals(user.getEmail())) {
                return "Email not available.";
            }
        }
        if (newPassword.isEmpty() && newPassword==null) {
            return "Password cannot be empty";
        }
        if (newPassword.length() < 8) {
            return "Passwords must be 8 or greater character length.";
        }
        if (!containsUpperCaseAndLowerCase(newPassword)) {
            return "Passwords must have atleast one upper and atleast one lower character.";
        }
        if(!containsNumberLetterAndSymbol(newPassword)){
            return "password must have at least one number, one letter and one symbol";
        }
        users.add(newUser);
        return "Ok";
    }

    private boolean containsUpperCaseAndLowerCase(String password) {
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
            if (hasUpperCase && hasLowerCase) {
                return true;
            }
        }
        return false;
    }
    private boolean containsNumberLetterAndSymbol(String pw) {
        boolean hasNumber = false;
        boolean hasSymbol = false;
        boolean hasLetter = false;
        for(char c: pw.toCharArray()){
            if(Character.isDigit(c)){
                hasNumber = true;
            }
            if(Character.isLetter(c)){
                hasLetter = true;
            }
            if(!Character.isLetterOrDigit(c)){
                hasSymbol = true;
            }
            if(hasNumber && hasLetter && hasSymbol){
                return true;
            }
        }
        return false;
    }
}
