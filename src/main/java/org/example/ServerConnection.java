package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class ServerConnection implements Runnable{

    private Socket server;
    private BufferedReader in;
    private PrintWriter out;
    private Main main;


    public ServerConnection(Socket server, BufferedReader in, PrintWriter out, Main main) throws IOException {
        this.server = server;
        this.in = in;
        this.out = out;
        this.main = main;
    }



    @Override
    public void run() {
        try
        {
            while(true)
            {
                System.out.println("czekanie");
                String request[] = in.readLine().split(";");
                Arrays.stream(request).forEach(x-> System.out.print(x));
                System.out.println();
                switch (request[0]) {
                    case Messages.Server.Start:
                        try{
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org.example/gameForm.fxml"));
                            Parent root = fxmlLoader.load();
                            Stage window = (Stage)main.joinButton.getScene().getWindow();
                            GameController gc = fxmlLoader.getController();
                            gc.startGame(request[1]);


                            Platform.runLater(new Runnable(){
                                @Override
                                public void run() {
                                    window.setScene(new Scene(root, 700, 700));
                                }
                            });

                        }catch(IOException e) {
                            System.out.println("Nie można otworzyć okna gry...");
                            e.printStackTrace();
                        }
                        break;
                    case Messages.Server.Matches:
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                main.listViewMatches.getItems().clear();
                                main.listViewMatches.getItems().setAll(Arrays.stream(request).skip(1).toArray());
                            }
                        });
                        break;
                    case Messages.Server.Disconnect: // socket1;1 socket2 (tego gracza)  login;id;imie
                            try {
                                in.close();
                            } catch (IOException e) {
                                System.out.println("Zamknieto połączenie");
                            }
                            out.close();

                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                main.rulesButton.setDisable(true);
                                main.joinButton.setDisable(true);
                                main.hostButton.setDisable(true);
                                main.loginButton.setText("Zaloguj");
                                main.loginTextField.setVisible(true);
                                main.passwordTextField.setVisible(true);
                            }
                        });
                        break;
                    default:
                        System.out.println("Nierozpoznana akcja");
                        break;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                in.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        System.out.println("Zatrzymanie ServerConnection");
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

