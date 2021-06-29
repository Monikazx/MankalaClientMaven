package org.example;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    public static Thread clientThread;




    @Override
    public void start(Stage primaryStage) throws Exception{


        primaryStage.setTitle("Main form");
        primaryStage.setScene(new Scene(loadFXML("/org.example/mainForm"), 751, 458));
        primaryStage.show();

    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public TextField loginTextField;
    public TextField passwordTextField;
    public  Button loginButton;
    public  Button hostButton;
    public  Button joinButton;
    public  Button rulesButton;
    public ListView listViewMatches;

    public void joinButton(ActionEvent actionEvent) {
        String playerName = listViewMatches.getSelectionModel().selectedItemProperty().get().toString();
        System.out.println("playername - > "+playerName);
        out.println(Messages.Client.Join+";"+playerName);
    }

    public void hostButton(ActionEvent actionEvent) {
        if(!joinButton.isDisable()) {
            joinButton.setDisable(true);
            out.println(Messages.Client.Host);
        }
        else {
            out.println(Messages.Client.Host);
            joinButton.setDisable(false);
        }
    }

    public void loginButton(ActionEvent actionEvent) {

        if(loginButton.getText().equals("Zaloguj")){
            listViewMatches.getItems().clear();
            try {
                client = new Socket(SERVER_IP, SERVER_PORT);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(),true);

                ServerConnection serverConnection = new ServerConnection(client,in,out,this);

                clientThread = new Thread(serverConnection);
                clientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String text = ";" + loginTextField.getText() + ";" +  passwordTextField.getText();
            out.println(Messages.Client.Login + text);

            loginButton.setText("Wyloguj");
            rulesButton.setDisable(false);
            joinButton.setDisable(false);
            hostButton.setDisable(false);
            loginTextField.setVisible(false);
            passwordTextField.setVisible(false);

        }
        else {
            listViewMatches.getItems().clear();
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clientThread.interrupt();

            rulesButton.setDisable(true);
            joinButton.setDisable(true);
            hostButton.setDisable(true);
            loginButton.setText("Zaloguj");
            loginTextField.setVisible(true);
            passwordTextField.setVisible(true);
        }

    }

    public static void Logout()
    {

    }

    public void gamePrinciplesButton(ActionEvent actionEvent) {
    }


}
