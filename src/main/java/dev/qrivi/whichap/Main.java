package dev.qrivi.whichap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    private Map<String, String> networks;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        loadNetworks();

        String bssid = Utils.getBSSID();
        Text text = new Text(networks.getOrDefault(bssid, bssid));
        StackPane root = new StackPane();
        root.getChildren().add(text);

        stage.setTitle("WhichAP");
        stage.setScene(new Scene(root, 300, 70));
        stage.show();
    }

    private void loadNetworks() {
        networks = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./networks.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(" ") && line.charAt(0) != '#') {
                    int splitter = line.indexOf(" ");
                    networks.put(line.substring(0, splitter).toUpperCase(), line.substring(splitter + 1).trim());
                }
            }
        } catch (Exception e) {
            System.out.println("No networks to load, or something wrong with file.");
        }

        for (Map.Entry<String, String> entry : networks.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
