package ui.viewHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Property;
import model.PropertyListModel;
import ui.booking.BookingController;
import ui.booking.BookingVM;
import ui.propertyList.PropertyListController;
import ui.propertyList.PropertyListVM;

public class ViewHandler
{
  private PropertyListVM propertyListVM;
  private BookingVM bookingVM;
  private Stage mainStage;

  public ViewHandler(PropertyListModel propertyListModel)
  {
    propertyListVM = new PropertyListVM(propertyListModel);
    bookingVM = new BookingVM(propertyListModel);
    mainStage = new Stage();
  }

  public void start()
  {
    openPropertyListView();
    mainStage.show();
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
  public void openBookingView(Property property)
  {
    bookingVM.updateProperty(property.getId());
    try
    {
      if (bookingScene == null)
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
            "/ui/booking/Booking.fxml"));
        Parent root = loader.load();
        BookingController bookingController = loader.getController();

        //TODO initialize the controller with the current selected property.
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
}
