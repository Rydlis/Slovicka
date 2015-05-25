/**
* Tato třída se stará o start aplikace.
*/

package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Slovíčka");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        // handler na ukonceni appky
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Dialogy dialogy = new Dialogy();
                ButtonType buttonType = dialogy.Confirm("Aplikace", "Opravdu chcete ukoncit aplikaci?").get();
                if (buttonType == ButtonType.OK){
                    Platform.exit();
                }
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
