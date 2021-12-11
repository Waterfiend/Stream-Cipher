package edu.aub;

import edu.aub.security.a5.A51Cipher;
import edu.aub.security.rc4.RC4;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;

/**
 * The main class which we used to launch our JavaFX application, used to create a GUI in order to encrypt/decrypt keys using the RC4 and A5/1 stream ciphers.
 *
 * @author Abdallah M. Wahidi
 * @author Karim Obeid
 * @author Youhanna Abou Jaoudeh
 */
public class Main extends Application {

    /**
     * The main entry point of our Java application, used to launch the JavaFX gui in this context.
     *
     * @param args The arguments we pass.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

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
            primaryStage.setScene(sceneRC);
        });

        Button btn2 = new Button();
        btn2.setText("A5 Cipher");
        btn2.setMinSize(200, 100);
        btn2.setOnAction(e -> {
            primaryStage.setScene(sceneA);
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
            byte[] inp = input1.getText().getBytes(StandardCharsets.UTF_8);
            byte[] key = input2.getText().getBytes(StandardCharsets.UTF_8);
            byte[] res = RC4.encrypt(inp, key);

            StringBuilder sb = new StringBuilder();
            for (byte b : res) {
                sb.append(String.format("%02X ", b));
            }
            rcResult.setText(sb.toString());

        });

        Button backButton1 = new Button();
        backButton1.setText("Go Back");
        grid1.add(backButton1, 2, 8);
        backButton1.setOnAction(e -> {
            primaryStage.setScene(scene);
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
        A51Cipher a51 = new A51Cipher();
        aEncrypt.setText("Encrypt");
        aEncrypt.setMinWidth(80);
        grid2.add(aEncrypt, 0, 7);
        GridPane.setHalignment(aEncrypt, HPos.RIGHT);
        String input = Ainput1.getText();
        aEncrypt.setOnAction(e -> {
            aResult.setText(a51.encrypt(input) + "\u001B[0m");
        });

        Button aDecrypt = new Button();
        aDecrypt.setText("Decrypt");
        aDecrypt.setMinWidth(80);
        grid2.add(aDecrypt, 2, 7);
        String in2 = Ainput2.getText();
        aDecrypt.setOnAction(e -> {
            aResult.setText(a51.decrypt(in2) + "\u001B[0m");
        });

        Button backButton2 = new Button();
        backButton2.setText("Go Back");
        backButton2.setMinWidth(80);
        grid2.add(backButton2, 2, 8);
        backButton2.setOnAction(e -> {
            primaryStage.setScene(scene);
        });

        root.getChildren().addAll(btn1, btn2);

        primaryStage.setTitle("RC-4 and A5 Encryption-Decryption Tool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
