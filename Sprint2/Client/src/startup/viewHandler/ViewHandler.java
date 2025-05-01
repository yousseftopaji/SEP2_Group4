package startup.viewHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dtos.Property;
import ui.booking.BookingController;
import ui.booking.BookingVM;
import ui.login.LoginCtrl;
import ui.login.LoginVM;
import ui.propertyList.PropertyListController;
import ui.propertyList.PropertyListVM;
import ui.register.RegisterCtrl;
import ui.register.RegisterVM;
import ui.specifyDates.SpecifyDatesController;
import ui.specifyDates.SpecifyDatesVM;
import ui.welcome.FrontViewCtrl;

import java.sql.Date;

public class ViewHandler
{
  private final SpecifyDatesVM specifyDatesVM;
  private final PropertyListVM propertyListVM;
  private final BookingVM bookingVM;
  private final RegisterVM registerVM;
  private final LoginVM loginVM;
  private final Stage mainStage;


  public ViewHandler()
  {
    specifyDatesVM = new SpecifyDatesVM();
    propertyListVM = new PropertyListVM();
    bookingVM = new BookingVM();
    registerVM = new RegisterVM();
    loginVM = new LoginVM();
    mainStage = new Stage();
  }

  public void start()
  {
    showView(ViewType.WELCOME);
    mainStage.show();
  }

  public void showView(ViewType view){
    try{
      switch (view){
        case WELCOME -> showFrontView();
        case REGISTER -> showRegisterView();
        case LOGIN -> showLoginView();
        case PROPERTY_LIST -> openPropertyListView();
        case BOOKING -> openBookingView(propertyListVM.getSelectedProperty().get());
        case SPECIFY_DATES -> openSpecifyDatesView();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Scene frontScene;
  public void showFrontView() throws Exception
  {
    if (frontScene == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(
          "/ui/welcome/FrontViewCtrl.fxml"));
      Parent root = loader.load();
      FrontViewCtrl frontViewController = loader.getController();
      frontViewController.initialize(this);
      frontScene = new Scene(root);
    }
    mainStage.setTitle("Welcome");
    mainStage.setScene(frontScene);
  }

  private Scene registerScene;
  public void showRegisterView() throws Exception
  {
    if (registerScene == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(
          "/ui/register/RegisterCtrl.fxml"));
      Parent root = loader.load();
      RegisterCtrl registerController = loader.getController();
      registerController.initialize(registerVM, this);
      registerScene = new Scene(root);
    }
    mainStage.setTitle("Register");
    mainStage.setScene(registerScene);
  }

  private Scene loginScene;
  public void showLoginView() throws Exception
  {
    if (loginScene == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(
          "/ui/login/LoginCtrl.fxml"));
      Parent root = loader.load();
      LoginCtrl loginController = loader.getController();
      loginController.initialize(loginVM, this);
      loginScene = new Scene(root);
    }
    mainStage.setTitle("Login");
    mainStage.setScene(loginScene);
  }

  private Scene specifyDatesScene;
  public void openSpecifyDatesView()
  {
    try
    {
      if (specifyDatesScene == null)
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
            "/ui/specifyDates/SpecifyDates.fxml"));
        Parent root = loader.load();
        SpecifyDatesController specifyDatesController = loader.getController();
        specifyDatesController.initialize(specifyDatesVM, this);
        specifyDatesScene = new Scene(root);
      }
      mainStage.setTitle("Specify Dates");
      mainStage.setScene(specifyDatesScene);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void setDates(Date startDate, Date endDate)
  {
    propertyListVM.setDates(startDate, endDate);
    bookingVM.setDates(startDate, endDate);
  }

  private Scene propertyListScene;
  public void openPropertyListView()
  {
    try
    {
      if (propertyListScene == null)
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
            "/ui/propertyList/PropertyList.fxml"));
        Parent root = loader.load();
        PropertyListController propertyListController = loader.getController();
        propertyListController.initialize(propertyListVM, this);
        propertyListScene = new Scene(root);
      }
      mainStage.setTitle("Property List");
      mainStage.setScene(propertyListScene);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private Scene bookingScene;
  public void openBookingView(Property property) throws Exception
  {
    bookingVM.updateProperty(property);
    try
    {
      if (bookingScene == null)
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
            "/ui/booking/Booking.fxml"));
        Parent root = loader.load();
        BookingController bookingController = loader.getController();

        bookingController.initialize(bookingVM, this);
        bookingScene = new Scene(root);
      }
      mainStage.setTitle("Booking");
      mainStage.setScene(bookingScene);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public enum ViewType {
    WELCOME,
    REGISTER,
    LOGIN,
    PROPERTY_LIST,
    BOOKING,
    SPECIFY_DATES
  }
}
