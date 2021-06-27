package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Main extends Application {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8001;

    private static Socket client = null;
    private static BufferedReader in = null;
    public static PrintWriter out = null;




    @Override
    public void start(Stage primaryStage) throws Exception{


        primaryStage.setTitle("Main form");
        primaryStage.setScene(new Scene(loadFXML("/org.example/mainForm"), 751, 458));
        primaryStage.show();

    }


    public static void main(String[] args) throws IOException {

        client = new Socket(SERVER_IP, SERVER_PORT);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(client.getOutputStream(),true);

        ServerConnection serverConnection = new ServerConnection(client,in,out);
        new Thread(serverConnection).start();

        launch(args);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public TextField loginTextField;
    public TextField passwordTextField;

    public void joinButton(ActionEvent actionEvent) {
    }

    public void hostButton(ActionEvent actionEvent) {
    }

    public void loginButton(ActionEvent actionEvent) {


        String text = ";" + loginTextField.getText() + ";" +  passwordTextField.getText();
        out.println(Messages.Client.Login + text);
    }

    public void gamePrinciplesButton(ActionEvent actionEvent) {
    }


}
