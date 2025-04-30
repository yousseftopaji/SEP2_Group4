package networking.auth;

public interface Authentication
{
  void registerUser(String email, String password, String firstName, String lastName,
      boolean isBlacklisted, boolean isAdmin);
  void loginUser(String email, String password);
}
