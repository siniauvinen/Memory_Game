package application;
	
import java.io.FileInputStream;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
			// Create the FXMLLoader 
        	FXMLLoader loader = new FXMLLoader();
        	
        	// Path to the FXML File
           
            String fxmlDocPath = "src\\main\\java\\application\\views\\fxml\\FxFXML.fxml";  // VIITTAUS FXML-TIEDOSTOON
            FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
            
            // Create the Pane and all Details FROM FXML -FILE
            VBox root = (VBox) loader.load(fxmlStream);       // EKSPLISIITTINEN TYYPPIMUUNNOS (VBox)
		

            //Create the Scene
            Scene scene = new Scene(root);
			
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Image icon = new Image(".\\.\\img\\logo.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("   Muistipeli");
			primaryStage.setResizable(false);
			
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
	}
}
