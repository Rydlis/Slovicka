package sample;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SplashScreen extends Preloader{

    @Override
    public void start(Stage stage) {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("splash.fxml")));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e){
                System.out.println("Nemohl byt nacten SplashScreen");
            } catch (IllegalStateException e){
                e.printStackTrace();
            }
        close(stage);
        }

    public void close(Stage stage){
        stage.close();
    }

    }
