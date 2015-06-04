/**
 * Tato tridaslouzi k zobrazovani SplashScreenu pri nacitani programu.
 * TODO vymyslet nacitani vzhledu z fxml, udelat jako nove api
 */

package sample;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SplashScreen extends Preloader{

    private Stage stage;

    @Override
    public void start(Stage stage) {
            try {
                this.stage = stage;
                Parent root = FXMLLoader.load(getClass().getResource("splash.fxml"));
                this.stage.initStyle(StageStyle.UNDECORATED);
                this.stage.setTitle("Slovíčka");
                this.stage.setScene(new Scene(root));
                this.stage.show();
            } catch (IOException e){
                System.out.println("Nemohl byt nacten SplashScreen");
            } catch (IllegalStateException e){
                e.printStackTrace();
            }
        }

    public void close(){
        this.stage.close();
    }
}
