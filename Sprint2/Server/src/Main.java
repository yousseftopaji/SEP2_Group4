import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.PropertyListModel;
import model.PropertyListModelManager;
import viewModel.BookingVM;
import viewModel.PropertyListVM;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize the model
        PropertyListModel model = new PropertyListModelManager();
        
        // Initialize the ViewModels
        BookingVM bookingVM = new BookingVM(model);
        PropertyListVM propertyListVM = new PropertyListVM(model);
        
        // Link the ViewModels
        propertyListVM.getSelectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                bookingVM.propertyID().set(newValue.intValue());
            }
        });
        
        // Load the FXML files
        FXMLLoader propertyListLoader = new FXMLLoader(getClass().getResource("view/PropertyList.fxml"));
        Parent propertyListRoot = propertyListLoader.load();
        
        FXMLLoader bookingLoader = new FXMLLoader(getClass().getResource("view/Booking.fxml"));
        Parent bookingRoot = bookingLoader.load();
        
        // Create the scenes
        Scene propertyListScene = new Scene(propertyListRoot, 600, 400);
        Scene bookingScene = new Scene(bookingRoot, 600, 400);
        
        // Set up the primary stage
        primaryStage.setTitle("Property Booking System");
        primaryStage.setScene(propertyListScene);
        primaryStage.show();
        
        // Create a secondary stage for booking
        Stage bookingStage = new Stage();
        bookingStage.setTitle("Property Booking");
        bookingStage.setScene(bookingScene);
        bookingStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}