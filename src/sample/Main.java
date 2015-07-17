package sample;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {


    public enum Status { s1, s2, s4 }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("box.fxml"));
        primaryStage.setTitle("AT-Test");
        primaryStage.setScene(new Scene(root, 960, 600));
        primaryStage.show();




    }


    public static void main(String[] args) throws Exception{
        launch(args);

    }





}
