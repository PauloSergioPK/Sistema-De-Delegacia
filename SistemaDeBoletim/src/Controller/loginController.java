package Controller;

import Exec.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class loginController {
    private String usuario = "Admin";
    private String senha = "Admin";

    @FXML
    private Button btSair;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label loginInvalido;

    @FXML
    void logar(ActionEvent event) throws IOException, InterruptedException {
        String loginDigitado = loginField.getText();
        String senhaDigitado = passwordField.getText();
        if(loginDigitado.equals(usuario) == true && senhaDigitado.equals(senha) == true){
            loginInvalido.setText("   Bem-Vindo(a)!!!");
            Parent scene = FXMLLoader.load(getClass().getResource("../View/principalScreen.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Main.changeScreen(new Scene(scene));
        }
        else {
            loginInvalido.setText("Usuario ou Senha invalidos!!!");
        }

    }

    @FXML
    void sair(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

}