/**
* Tato třída se stará o start aplikace.
 * Zároven take ukazuje Splashcreen, ktery vazne nepatri k nejlepsim resenim
*/

package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {

    private SplashScreen splashScreenController = new SplashScreen();
    private Dialogy dialogy = new Dialogy();
    protected static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.stage = primaryStage;
        splashScreenController.init();
        splashScreenController.start(new Stage());
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Slovíčka");
        primaryStage.setScene(new Scene(root));
        Platform.setImplicitExit(false);
        primaryStage.show();
        // handler na ukonceni appky
        primaryStage.setOnCloseRequest(event -> {
            ButtonType buttonType = dialogy.Confirm("Aplikace", "Opravdu chcete ukoncit aplikaci?").get();
            if (buttonType == ButtonType.OK){
                Platform.exit();
            } else {
                event.consume();
            }
        });
        splashScreenController.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
