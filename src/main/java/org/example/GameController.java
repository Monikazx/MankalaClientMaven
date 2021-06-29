package org.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController  {
    public ImageView fieldImageBox0;
    public ImageView fieldImageBox1;
    public ImageView fieldImageBox2;
    public ImageView fieldImageBox3;
    public ImageView fieldImageBox4;
    public ImageView fieldImageBox5;
    public ImageView fieldImageBox6;
    public ImageView fieldImageBox7;
    public ImageView fieldImageBox8;
    public ImageView fieldImageBox9;
    public ImageView fieldImageBox10;
    public ImageView fieldImageBox11;
    public ImageView fieldImageBox12;
    public ImageView fieldImageBox13;
    public Label whiteLabel0;
    public Label whiteLabel1;
    public Label whiteLabel2;
    public Label whiteLabel3;
    public Label whiteLabel4;
    public Label whiteLabel5;
    public Label whiteLabel6;
    public Label blackLabel0;
    public Label blackLabel1;
    public Label blackLabel2;
    public Label blackLabel3;
    public Label blackLabel4;
    public Label blackLabel5;
    public Label blackLabel6;

    public Label statusLabel;


    ArrayList<Label> labelList;
    List<ImageView> imageViewList;
    List<Image> imageList;
    String playerColor;

    //@Override
   // public void initialize(URL url, ResourceBundle resourceBundle) {


    public void startGame(String playerColor){
        this.playerColor = playerColor;
        labelList = new ArrayList<Label>();

        labelList.add(whiteLabel0);
        labelList.add(whiteLabel1);
        labelList.add(whiteLabel2);
        labelList.add(whiteLabel3);
        labelList.add(whiteLabel4);
        labelList.add(whiteLabel5);
        labelList.add(whiteLabel6);
        labelList.add(blackLabel0);
        labelList.add(blackLabel1);
        labelList.add(blackLabel2);
        labelList.add(blackLabel3);
        labelList.add(blackLabel4);
        labelList.add(blackLabel5);
        labelList.add(blackLabel6);


        if (playerColor.equals("BLACK"))
        {
            for (Label l : labelList)
            {
                l.setDisable(true);
            }

            statusLabel.setText("TURA PRZECIWNIKA");
        }
        else
        {
            for (int i = 7; i < 14; i++)
            {
                labelList.get(i).setDisable(true);
            }
            statusLabel.setText("TWOJA TURA");
        }
        imageViewList = new ArrayList<ImageView>();
        imageViewList.add(fieldImageBox0);
        imageViewList.add(fieldImageBox1);
        imageViewList.add(fieldImageBox2);
        imageViewList.add(fieldImageBox3);
        imageViewList.add(fieldImageBox4);
        imageViewList.add(fieldImageBox5);
        imageViewList.add(fieldImageBox6);
        imageViewList.add(fieldImageBox7);
        imageViewList.add(fieldImageBox8);
        imageViewList.add(fieldImageBox9);
        imageViewList.add(fieldImageBox10);
        imageViewList.add(fieldImageBox11);
        imageViewList.add(fieldImageBox12);
        imageViewList.add(fieldImageBox13);

        imageList = new ArrayList<>();
        imageList.add(new Image(Main.class.getResource("/images/00.png").toString()));
        imageList.add(new Image(Main.class.getResource("/images/1.png").toString()));
        imageList.add(new Image(Main.class.getResource("/images/2.png").toString()));
        imageList.add(new Image(Main.class.getResource("/images/3.png").toString()));
        imageList.add(new Image(Main.class.getResource("/images/4.png").toString()));
        imageList.add(new Image(Main.class.getResource("/images/5.png").toString()));
        setPictures();
    }

    /*private void exitButton_Click(object sender, EventArgs e)
    {
        mainForm.client.Send(Messages.Client.Exit);
    }*/



    public void enemyMoveFromServer(int labelIndex)
    {
        int iMaxValue = Integer.parseInt(labelList.get(labelIndex).getText());
        int labelIndexValue = Integer.parseInt(labelList.get(labelIndex).getText());
        for (int i = 1; i <= iMaxValue; i++)
        {
            //###CAPTURE###
            if (Integer.parseInt(labelList.get((labelIndex + labelIndexValue) % 14).getText()) == 0
                    && ((labelIndex + labelIndexValue) % 14) != 6
                    && ((labelIndex + labelIndexValue) % 14) != 13
                    && i == iMaxValue)
            {
                if (playerColor.equals("BLACK") && ((labelIndex + labelIndexValue) % 14) > 6)
                {

                }
                else if (playerColor.equals("WHITE") && ((labelIndex + labelIndexValue) % 14) < 6)
                {

                }
                else
                {
                    if (playerColor.equals("BLACK"))
                    {
                        if (Integer.parseInt(labelList.get(13 - 1 - ((labelIndex + labelIndexValue) % 7)).getText()) != 0)
                        {
                            labelList.get((labelIndex + labelIndexValue) % 14).getText().equals("0");

                            labelList.get(6).setText(String.valueOf(Integer.parseInt(labelList.get(6).getText()) + 1 + Integer.parseInt(labelList.get(13 - 1 - ((labelIndex + labelIndexValue) % 7)).getText())));
                            labelList.get(Math.abs(13 - 1 - ((labelIndex + labelIndexValue) % 7))).setText("0");

                            break;
                        }
                    }
                    else
                    {
                        if (Integer.parseInt(labelList.get(6 - 1 - ((labelIndex + labelIndexValue) % 7)).getText()) != 0)
                        {
                            labelList.get((labelIndex + labelIndexValue) % 14).setText("0");

                            labelList.get(13).setText(String.valueOf(Integer.parseInt(labelList.get(13).getText()) + 1 + Integer.parseInt(labelList.get(6 - 1 - ((labelIndex + labelIndexValue) % 7)).getText())));
                            labelList.get(Math.abs(6 - 1 - ((labelIndex + labelIndexValue) % 7))).setText("0");

                            break;
                        }
                    }
                }
            }
            //###CAPTURE###

            if (((labelIndex + i) % 14) != 6 && ((labelIndex + i) % 14) != 13)
            {
                labelList.get((labelIndex + i) % 14).setText(String.valueOf(Integer.parseInt(labelList.get((labelIndex + i) % 14).getText()) + 1));
            }
            else if (((labelIndex + i) % 14) == 6)
            {
                if (playerColor.equals("BLACK"))
                {
                    labelList.get((labelIndex + i) % 14).setText(String.valueOf(Integer.parseInt(labelList.get((labelIndex + i) % 14).getText()) + 1));
                }
                else
                {
                    iMaxValue++;
                }
            }
            else if (((labelIndex + i) % 14) == 13)
            {
                if (playerColor.equals("WHITE"))
                {
                    labelList.get((labelIndex + i) % 14).setText(String.valueOf(Integer.parseInt(labelList.get((labelIndex + i) % 14).getText()) + 1));
                }
                else
                {
                    iMaxValue++;
                }
            }
        }

        //###EXTRA MOVE###
        if ((labelIndex + Integer.parseInt(labelList.get(labelIndex).getText())) % 14 == 6 || (labelIndex + Integer.parseInt(labelList.get(labelIndex).getText())) % 14 == 13)
        {
            statusLabel.setText("TURA PRZECIWNIKA");
            labelList.get(labelIndex).setText("0");
            return;
        }
        //###EXTRA MOVE###

        labelList.get(labelIndex).setText("0");

        if (playerColor.equals("WHITE"))
        {
            for (int i = 0; i < 7; i++)
            {
                labelList.get(i).setDisable(false);
            }
        }
        else
        {
            for (int i = 7; i < 13; i++)
            {
                labelList.get(i).setDisable(false);
            }
        }
        setPictures();
    }


    private void yourMove(int labelIndex)
    {

        if (labelList.get(labelIndex).getText() == "0")
        {
            return;
        }
        int iMaxValue = Integer.parseInt(labelList.get(labelIndex).getText());
        int labelIndexValue = Integer.parseInt(labelList.get(labelIndex).getText());
        for (int i = 1; i <= iMaxValue; i++)
        {
            //###CAPTURE###
            if (Integer.parseInt(labelList.get((labelIndex + labelIndexValue) % 14).getText()) == 0
                    && ((labelIndex + labelIndexValue) % 14) != 6
                    && ((labelIndex + labelIndexValue) % 14) != 13
                    && i == iMaxValue)
            {
                if (playerColor.equals("WHITE") && ((labelIndex + labelIndexValue) % 14) > 6)
                {

                }
                else if (playerColor.equals("BLACK") && ((labelIndex + labelIndexValue) % 14) < 6)
                {

                }
                else
                {
                    if (playerColor.equals("WHITE"))
                    {
                        if (Integer.parseInt(labelList.get(13 - 1 - ((labelIndex + labelIndexValue) % 7)).getText()) != 0)
                        {
                            labelList.get((labelIndex + labelIndexValue) % 14).setText("0");

                            labelList.get(6).setText(String.valueOf(Integer.parseInt(labelList.get(6).getText()) + 1 + Integer.parseInt(labelList.get(13 - 1 - ((labelIndex + labelIndexValue) % 7)).getText())));
                            labelList.get(Math.abs(13 - 1 - ((labelIndex + labelIndexValue) % 7))).setText("0");

                            break;
                        }
                    }
                    else
                    {
                        if (Integer.parseInt(labelList.get(6 - 1 - ((labelIndex + labelIndexValue) % 7)).getText()) != 0)
                        {
                            labelList.get((labelIndex + labelIndexValue) % 14).setText("0");

                            labelList.get(13).setText(String.valueOf(Integer.parseInt(labelList.get(13).getText()) + 1 + Integer.parseInt(labelList.get(6 - 1 - ((labelIndex + labelIndexValue) % 7)).getText())));
                            labelList.get(Math.abs(6 - 1 - ((labelIndex + labelIndexValue) % 7))).setText("0");

                            break;
                        }
                    }
                }

            }
            //###CAPTURE###

            if (((labelIndex + i) % 14) != 6 && ((labelIndex + i) % 14) != 13)
            {
                labelList.get((labelIndex + i) % 14).setText(String.valueOf(Integer.parseInt(labelList.get((labelIndex + i) % 14).getText()) + 1));
            }
            else if (((labelIndex + i) % 14) == 6)
            {
                if (playerColor.equals("WHITE"))
                {
                    labelList.get((labelIndex + i) % 14).setText(String.valueOf(Integer.parseInt(labelList.get((labelIndex + i) % 14).getText()) + 1));
                }
                else
                {
                    iMaxValue++;
                }
            }
            else if (((labelIndex + i) % 14) == 13)
            {
                if (playerColor.equals("BLACK"))
                {
                    labelList.get((labelIndex + i) % 14).setText(String.valueOf(Integer.parseInt(labelList.get((labelIndex + i) % 14).getText()) + 1));
                }
                else
                {
                    iMaxValue++;
                }
            }
        }


        if ((labelIndex + labelIndexValue) % 14 == 6 || (labelIndex + labelIndexValue) % 14 == 13)//###EXTRA MOVE###
        {
           // mainForm.client.Send(Messages.Client.Move + $":{labelIndex}");
            /**
             *
             *  POPRAWIC
             *
             */
        }
        else
        {
            for (int i = 0; i < 14; i++)
            {
                labelList.get(i).setDisable(true);
            }
            statusLabel.setText("TURA PRZECIWNIKA");
            //mainForm.client.Send(Messages.Client.Move + $":{labelIndex}");
            /**
             *
             *  I TU
             */
        }
        labelList.get(labelIndex).setText("0");
        setPictures();

        checkEndGame();
    }

    private void endingGame()
    {
        if(Integer.parseInt(whiteLabel6.getText()) > Integer.parseInt(blackLabel6.getText()))
        {
            //mainForm.client.Send(Messages.Client.EndGame + ":WHITE");
        }
        else
        {
            //mainForm.client.Send(Messages.Client.EndGame + ":BLACK");
        }
    }

    private void checkEndGame()
    {
        int count = 0;
        for(int i = 0; i <= 5; i++)
        {
            if (labelList.get(i).getText().equals("0"))
                count++;
        }
        if (count == 6)
        {
            endingGame();
            return;
        }

        count = 0;
        for (int i = 7; i <= 12; i++)
        {
            if (labelList.get(i).getText().equals("0"))
                count++;
        }
        if (count == 6)
        {
            endingGame();
        }
    }

    private void setPictures()
    {
        int index = 0;
        for (ImageView p : imageViewList)
        {
            if (Integer.parseInt(labelList.get(index).getText()) < 5)
            {
                p.setImage(imageList.get(Integer.parseInt(labelList.get(index).getText())));
            }
            else
            {
                p.setImage(imageList.get(5));
            }
            index++;
        }
    }

    private void setColorWhite(int labelIndex, int labelTextNumber)
    {
        setWhiteEnabled();
        int maxValue = labelIndex + labelTextNumber;
        for (int i = labelIndex+1; i <= maxValue; i++)
        {
            if (i % 14 == 13)
            {
                labelList.get(i).setTextFill(Color.BLACK);
                maxValue++;
            }
            else
            {
                labelList.get(i % 14).setDisable(false);
                labelList.get(i%14).setTextFill(Color.PINK);
            }
        }
    }
    private void setColorBlack(int labelIndex, int labelTextNumber)
    {
        setBlackEnabled();
        int maxValue = labelIndex + labelTextNumber;
        for (int i = labelIndex + 1; i <= maxValue; i++)
        {
            if (i % 14 == 6)
            {
                if (i > 14)
                {
                    labelList.get(14-(14-(i%14))).setTextFill(Color.BLACK);
                }
                else
                {
                    labelList.get(i).setTextFill(Color.BLACK);
                }

                maxValue++;
            }
            else
            {
                labelList.get(i % 14).setDisable(false);
                labelList.get(i % 14).setTextFill(Color.PINK);
            }
        }
    }
    private void setAllBlack()
    {
        for (Label l : labelList)
        {
            l.setTextFill(Color.BLACK);
        }
    }
    private void setBlackDisabled()
    {
        for (Label l : labelList)
        {
            if (l.getId().substring(0, 5).equals("black"))
            {
                l.setDisable(true);
            }
        }
    }
    private void setBlackEnabled()
    {
        for (Label l : labelList)
        {
            if (l.getId().substring(0, 5).equals("black"))
            {
                l.setDisable(false);
            }
        }
    }

    private void setWhiteDisabled()
    {
        for (Label l : labelList)
        {
            if (l.getId().substring(0, 5).equals("white"))
            {
                l.setDisable(true);
            }
        }
    }
    private void setWhiteEnabled()
    {
        for (Label l : labelList)
        {
            if (l.getId().substring(0, 5).equals("white"))
            {
                l.setDisable(false);
            }
        }
    }

    public void endingGame_Click(MouseEvent mouseEvent) {
        endingGame();
    }

    public void whiteLabel0_Clicked(MouseEvent mouseEvent) {
        yourMove(0);
    }
    public void whiteLabel1_Clicked(MouseEvent mouseEvent) {
        yourMove(1);
    }
    public void whiteLabel2_Clicked(MouseEvent mouseEvent) {
        yourMove(2);
    }
    public void whiteLabel3_Clicked(MouseEvent mouseEvent) {
        yourMove(3);
    }
    public void whiteLabel4_Clicked(MouseEvent mouseEvent) {
        yourMove(4);
    }
    public void whiteLabel5_Clicked(MouseEvent mouseEvent) {
        yourMove(5);
    }
    public void blackLabel0_Clicked(MouseEvent mouseEvent) {
        yourMove(7);
    }
    public void blackLabel1_Clicked(MouseEvent mouseEvent) {
        yourMove(8);
    }
    public void blackLabel2_Clicked(MouseEvent mouseEvent) {
        yourMove(9);
    }
    public void blackLabel3_Clicked(MouseEvent mouseEvent) {
        yourMove(10);
    }
    public void blackLabel4_Clicked(MouseEvent mouseEvent) {
        yourMove(11);
    }
    public void blackLabel5_Clicked(MouseEvent mouseEvent) {
        yourMove(12);
    }

    public void whiteLabel0_Entered(MouseEvent mouseEvent) {
        int textNumber = Integer.parseInt(whiteLabel0.getText());
        int labelNumber = 0;
        setColorWhite(labelNumber, textNumber);
    }
    public void whiteLabel1_Entered(MouseEvent mouseEvent) {
        int textNumber = Integer.parseInt(whiteLabel1.getText());
        int labelNumber = 1;
        setColorWhite(labelNumber, textNumber);
    }
    public void whiteLabel2_Entered(MouseEvent mouseEvent) {
        int textNumber = Integer.parseInt(whiteLabel2.getText());
        int labelNumber = 2;
        setColorWhite(labelNumber, textNumber);
    }
    public void whiteLabel3_Entered(MouseEvent mouseEvent) {
        int textNumber = Integer.parseInt(whiteLabel3.getText());
        int labelNumber = 3;
        setColorWhite(labelNumber, textNumber);
    }
    public void whiteLabel4_Entered(MouseEvent mouseEvent) {
        int textNumber = Integer.parseInt(whiteLabel4.getText());
        int labelNumber = 4;
        setColorWhite(labelNumber, textNumber);
    }
    public void whiteLabel5_Entered(MouseEvent mouseEvent) {
        int textNumber = Integer.parseInt(whiteLabel5.getText());
        int labelNumber = 5;
        setColorWhite(labelNumber, textNumber);
    }
    public void blackLabel0_Entered(MouseEvent mouseEvent) {
        int labelNumber = 7;
        int textNumber = Integer.parseInt(blackLabel0.getText());
        setColorBlack(labelNumber, textNumber);
    }
    public void blackLabel1_Entered(MouseEvent mouseEvent) {
        int labelNumber = 8;
        int textNumber = Integer.parseInt(blackLabel1.getText());
        setColorBlack(labelNumber, textNumber);
    }
    public void blackLabel2_Entered(MouseEvent mouseEvent) {
        int labelNumber = 9;
        int textNumber = Integer.parseInt(blackLabel2.getText());
        setColorBlack(labelNumber, textNumber);
    }
    public void blackLabel3_Entered(MouseEvent mouseEvent) {
        int labelNumber = 10;
        int textNumber = Integer.parseInt(blackLabel3.getText());
        setColorBlack(labelNumber, textNumber);
    }
    public void blackLabel4_Entered(MouseEvent mouseEvent) {
        int labelNumber = 11;
        int textNumber = Integer.parseInt(blackLabel4.getText());
        setColorBlack(labelNumber, textNumber);
    }
    public void blackLabel5_Entered(MouseEvent mouseEvent) {
        int labelNumber = 12;
        int textNumber = Integer.parseInt(blackLabel5.getText());
        setColorBlack(labelNumber, textNumber);
    }


    public void whiteLabel0_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setBlackDisabled();
    }
    public void whiteLabel1_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setBlackDisabled();
    }
    public void whiteLabel2_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setBlackDisabled();
    }
    public void whiteLabel3_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setBlackDisabled();
    }
    public void whiteLabel4_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setBlackDisabled();
    }
    public void whiteLabel5_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setBlackDisabled();
    }
    public void blacklLabel0_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setWhiteDisabled();
    }
    public void blacklLabel1_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setWhiteDisabled();
    }
    public void blacklLabel2_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setWhiteDisabled();
    }
    public void blacklLabel3_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setWhiteDisabled();
    }
    public void blacklLabel4_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setWhiteDisabled();
    }
    public void blacklLabel5_Exited(MouseEvent mouseEvent) {
        setAllBlack();
        setWhiteDisabled();
    }



}
