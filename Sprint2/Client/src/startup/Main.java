package startup;

import javafx.application.Application;
import javafx.stage.Stage;
import model.PropertyListModel;
import model.PropertyListModelManager;
import ui.viewHandler.ViewHandler;

public class Main extends Application {
    @Override public void start(Stage primaryStage) throws Exception
    {
        PropertyListModel propertyListModel = new PropertyListModelManager();
        ViewHandler vh = new ViewHandler(propertyListModel);
        vh.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}