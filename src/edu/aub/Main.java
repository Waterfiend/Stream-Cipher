package edu.aub;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Youhanna
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Stage window = primaryStage;

        HBox root = new HBox(20);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 500);
        
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(25, 25, 25, 25));
        Scene sceneRC = new Scene(grid1, 600, 500);
        
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));
        Scene sceneA = new Scene(grid2, 600, 500);
        
        Button btn1 = new Button();
        btn1.setText("RC4 Cipher");
        btn1.setMinSize(200, 100);
        btn1.setOnAction(e -> {
            window.setScene(sceneRC);
        });
        
        Button btn2 = new Button();
        btn2.setText("A5 Cipher");
        btn2.setMinSize(200, 100);
        btn2.setOnAction(e -> {
            window.setScene(sceneA);
        });

              
        
        //RC4 section
        Label rcLabel1 = new Label("Enter the message or cipher:");
        GridPane.setHalignment(rcLabel1, HPos.RIGHT);
        grid1.add(rcLabel1, 0, 1, 1, 1);
        TextField input1 = new TextField();
        grid1.add(input1, 2, 1);
        Label rcLabel2 = new Label("Enter the RC4 key:");
        GridPane.setHalignment(rcLabel2, HPos.RIGHT);
        grid1.add(rcLabel2, 0, 2);
        TextField input2 = new TextField();
        grid1.add(input2, 2, 2);
        Label rcLabel3 = new Label("Result:");
        GridPane.setHalignment(rcLabel3, HPos.RIGHT);
        grid1.add(rcLabel3, 0, 5);
        //This is where the result of the encryption/decryption goes
        TextField rcResult = new TextField();
        rcResult.setEditable(false);
        grid1.add(rcResult, 2, 5);
        
        Button rcDEncrypt = new Button();
        rcDEncrypt.setText("Encrypt/Decrypt");
        grid1.add(rcDEncrypt, 2, 7);
        rcDEncrypt.setOnAction(e -> {
            
        });
        
        Button backButton1 = new Button();
        backButton1.setText("Go Back");
        grid1.add(backButton1, 2, 8);
        backButton1.setOnAction(e -> {
            window.setScene(scene);
        });  
        
        
        
        //A5 section
        Label aLabel1 = new Label("Enter the message or cipher:");
        GridPane.setHalignment(aLabel1, HPos.RIGHT);
        grid2.add(aLabel1, 0, 1, 1, 1);
        TextField Ainput1 = new TextField();
        grid2.add(Ainput1, 2, 1);
        Label aLabel2 = new Label("Enter the A5 key:");
        GridPane.setHalignment(aLabel2, HPos.RIGHT);
        grid2.add(aLabel2, 0, 2);
        TextField Ainput2 = new TextField();
        grid2.add(Ainput2, 2, 2);
        Label aLabel3 = new Label("Result:");
        GridPane.setHalignment(aLabel3, HPos.RIGHT);
        grid2.add(aLabel3, 0, 5);
        //This is where the result of the encryption/decryption goes
        TextField aResult = new TextField();
        aResult.setEditable(false);
        grid2.add(aResult, 2, 5);
        
        Button aEncrypt = new Button();
        aEncrypt.setText("Encrypt");
        aEncrypt.setMinWidth(80);
        grid2.add(aEncrypt, 0, 7);
        GridPane.setHalignment(aEncrypt, HPos.RIGHT);
        aEncrypt.setOnAction(e -> {
            
        });
        
        Button aDecrypt = new Button();
        aDecrypt.setText("Decrypt");
        aDecrypt.setMinWidth(80);
        grid2.add(aDecrypt, 2, 7);
        aDecrypt.setOnAction(e -> {
            
        });
        
        Button backButton2 = new Button();
        backButton2.setText("Go Back");
        backButton2.setMinWidth(80);
        grid2.add(backButton2, 2, 8);
        backButton2.setOnAction(e -> {
            window.setScene(scene);
        });  
        
        //
        //
        
        root.getChildren().addAll(btn1,btn2);
  
        window.setTitle("RC-4 and A5 Encryption-Decryption Tool");
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
