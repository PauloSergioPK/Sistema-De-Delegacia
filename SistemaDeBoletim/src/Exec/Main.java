package Exec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class Main extends Application {
    private static Stage stage;
    private static Scene login;
    //private static Scene principalScreen;
    public static int totalCidadao = -1;
    public static int totalBoletins = -1;
    public static int totalDelitos = -1;
    public static int totalEndereco = -1;
    public static int totalTelefones = -1;
    public static int totalSuspeitos = -1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            stage = primaryStage;
            primaryStage.setTitle("Bem vindo ao sistema de boletins de ocorrencia R.P.D");
            Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../View/loginScreen.fxml"));
            //Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("../View/principalScreen.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            login = new Scene(fxmlLogin);
            //principalScreen = new Scene(fxmlPrincipal);
            primaryStage.setScene(login);
            primaryStage.show();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void changeScreen(Scene cena){
        stage.setScene(cena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
