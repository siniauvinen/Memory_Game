package application.views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.dataAccess.ScoreinfoManager;
import application.entities.Scoreinfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class LeaderboardController {
	
	// Create new tableview
	@FXML private TableView<Scoreinfo> tblScore = new TableView<Scoreinfo>();
	
	// Create observablelist for tableview
	ObservableList<Scoreinfo> data = FXCollections.observableArrayList();

	// Create columns for tableview
	@FXML private TableColumn<Scoreinfo, String> colID = new TableColumn<>("    #"); 
	@FXML private TableColumn<Scoreinfo, String> colName = new TableColumn<>("NIMIMERKKI");
    @FXML private TableColumn<Scoreinfo, String> colScore = new TableColumn<>("PISTEET");

	
	@FXML private TextField textName;
    @FXML private TextField textScore;
    
     
    // location and resources will be automatically injected by the FXML loader
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
     
    // Add a public no-args constructor
    public LeaderboardController() 
    {
    }
     
    @FXML
    private void initialize() {
    	
    	// Initializes tableview columns with data properties from scoreinfo
    	colID.setCellValueFactory(new PropertyValueFactory<Scoreinfo, String>("id"));
    	colName.setCellValueFactory(new PropertyValueFactory<Scoreinfo, String>("user_name"));
    	colScore.setCellValueFactory(new PropertyValueFactory<Scoreinfo, String>("score"));
    	
    	
    	tblScore.setItems(data); // set empty observablelist to tableview 
    	tblScore.getColumns().addAll(colID, colName, colScore); // add columns to tableview
    	
    	getTopTen(); //Get top 10 players
    }
    
    private void getTopTen() {
    	ScoreinfoManager scoreinfoManager = new ScoreinfoManager(); // Create new scoreinfoManager object
    	scoreinfoManager.setup(); // Load Hibernate Session factory
    	data.clear(); //Clear list
    	data.addAll(scoreinfoManager.getAll()); //Get top 10 from DB
        for (int i=0; i < data.size(); i++) { //Set rankings
        	data.get(i).setId(i+1);
        }
        scoreinfoManager.exit();
    }
    
    
	@FXML
    private void printOutput() {
		if ((textName.getText().isBlank() || textName.getText().isEmpty())){
			textName.setText(null);
    	} else {
    		ScoreinfoManager scoreinfoManager = new ScoreinfoManager(); //Database object
    		scoreinfoManager.setup(); //Open DB connection
    		scoreinfoManager.create(textName.getText(), Integer.valueOf(textScore.getText())); //Create new entry to DB
    		scoreinfoManager.exit(); //Close DB connection
    		getTopTen(); //Get top 10 players
    	}
	}
}
