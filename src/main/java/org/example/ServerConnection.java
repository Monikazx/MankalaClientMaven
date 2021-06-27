package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable{

    private Socket server;
    private BufferedReader in;
    private PrintWriter out;

    public ServerConnection(Socket server, BufferedReader in, PrintWriter out) throws IOException {
        this.server = server;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {

        try
        {
            while(true)
            {
                String request[] = in.readLine().split(";");
                switch (request[0]) {
                    case Messages.Server.Start: // socket;id;name
                        Stage stage = new Stage();
                        Parent gameForm = FXMLLoader.load(getClass().getResource("gameForm.fxml"));
                        Scene sceneGame = new Scene(gameForm,758,474);
                        stage.setScene(sceneGame);
                        break;
                    case Messages.Server.Logged: // socket1;1 socket2 (tego gracza)  login;id;imie

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




}

