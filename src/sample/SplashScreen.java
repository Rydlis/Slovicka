package sample;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SplashScreen extends Preloader{

    private Stage stage1;

    @Override
    public void start(Stage stage) {
            try {
                stage1 = stage;
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("splash.fxml")));
                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.setScene(scene);
                stage1.show();
            } catch (IOException e){
                System.out.println("Nemohl byt nacten SplashScreen");
            } catch (IllegalStateException e){
                e.printStackTrace();
            }
        }

    public void close(){
    stage1.close();
    }
}
