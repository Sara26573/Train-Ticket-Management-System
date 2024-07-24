package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Klient {
	 @FXML
	    private Label burimi;

	    @FXML
	    private TableColumn<GetData, String> burimiKolone;

	    @FXML
	    private Label cmimi;

	    @FXML
	    private TableColumn<GetData, Float> cmimiKolone;

	    @FXML
	    private Label data;

	    @FXML
	    private TableColumn<GetData, LocalDateTime> dataKolone;

	    @FXML
	    private Label destinacioni;

	    @FXML
	    private TableColumn<GetData, String> destinacioniKolone;

	    @FXML
	    private Label klasa;

	    @FXML
	    private TableColumn<GetData, String> klasaKolone;

	    @FXML
	    private Label kohezgjatja;

	    @FXML
	    private TableColumn<GetData, String> kohezgjatjaKolone;

	    @FXML
	    private TableView<GetData> table;

	    @FXML
	    private ComboBox<Integer> vende;

	    @FXML
	    private TableColumn<GetData, Integer> vendeKolone;
	    
	    @FXML
	    private Label idticket;

	    @FXML
	    private Label vende2;
   
	   
	    
	    ObservableList <Integer>vende1=FXCollections.observableArrayList();
	    
	    public void initialize() throws SQLException {
	    	burimiKolone.setCellValueFactory(new PropertyValueFactory<GetData, String>("burimi"));
	    	destinacioniKolone.setCellValueFactory(new PropertyValueFactory<GetData, String>("destinacioni"));
	    	vendeKolone.setCellValueFactory(new PropertyValueFactory<GetData, Integer>("vende"));  
	    	klasaKolone.setCellValueFactory(new PropertyValueFactory<GetData, String>("klasa"));  
	    	dataKolone.setCellValueFactory(new PropertyValueFactory<GetData, LocalDateTime>("data"));
	    	cmimiKolone.setCellValueFactory(new PropertyValueFactory<GetData, Float>("cmimi"));
	    	kohezgjatjaKolone.setCellValueFactory(new PropertyValueFactory<GetData, String>("kohezgjatja"));  
	    	
	    	vende.setItems(vende1);
	    	
	    	popullo();
	        
	    }
	    
	   
	    public void popullo() throws SQLException {
	    	ObservableList<GetData> tedhenatabele = FXCollections.observableArrayList();
	    	SQLConnect lidhje = new SQLConnect();
	        Connection connect = lidhje.getConnection();
	        Statement st = connect.createStatement();
	        ResultSet rs = st.executeQuery("select * from ticket");
	        while (rs.next()) {
	        	Timestamp timestamp = rs.getTimestamp("Data");
	 	        LocalDateTime localDateTime = timestamp.toLocalDateTime();
	        	tedhenatabele.add(new GetData(rs.getInt("idticket"), rs.getString("Burimi"), rs.getString("Destinacioni"), rs.getInt("Vende"), rs.getString("Klasa"),localDateTime,  rs.getFloat("Cmimi"), rs.getString("Kohezgjatja")));
	        }
	        table.setItems(tedhenatabele);

	    }
	    
	    float totalPrice;
	    @FXML
	     void update() {
	    	GetData u = table.getSelectionModel().getSelectedItem();
	    	for(int i=1;i<=u.getVende();i++) {
	    		vende1.add(i);
	    	}
	    	cmimi.setText(String.valueOf(u.getCmimi())+" €");
	    	  vende.setOnAction(event->{              
	                int SeatNumber=vende.getSelectionModel().getSelectedItem();
	                totalPrice=SeatNumber*u.getCmimi();
	                cmimi.setText(Float.toString(totalPrice)+" €");
	                vende2.setText(Integer.toString(SeatNumber));
	            });
	  
	    	
	    	data.setText(String.valueOf(u.getData()));
	      
	     	destinacioni.setText(u.getDestinacioni());
	     	burimi.setText(u.getBurimi());
	     	kohezgjatja.setText(u.getKohezgjatja());
	     
	     	idticket.setText(String.valueOf(u.getId()));
	     	klasa.setText(u.getKlasa());
	      
	    }     
	    Scene scene;
		Parent root;
		Stage stage;
	    public void buy(ActionEvent event,String a) throws IOException {
	    	GetData u = table.getSelectionModel().getSelectedItem();
	    	Integer Vende123=Integer.parseInt(vende2.getText());
	    	int CurrentVende=u.getVende();
	    	int id=u.getId();
	    	
	         if(CurrentVende>=Vende123) {
	    	FXMLLoader loader=new FXMLLoader(getClass().getResource(a));
			root=loader.load();
			
			Blej scenecontroller=loader.getController();
			scenecontroller.updateLabels(burimi.getText(), cmimi.getText(), data.getText(), destinacioni.getText(), klasa.getText(), kohezgjatja.getText(), vende2.getText(),CurrentVende,id,u.getCmimi());
   		
      	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();}
	         else {
	        	 Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setTitle("Error");
	             alert.setHeaderText("Nuk ka mjaftueshëm vende të lira.");
	             alert.setContentText("Ju lutem zgjidhni një numër më të vogël të vendeve.");
	             alert.showAndWait();
	         }
	       
	         
	    }
		   @FXML
		   private void buy1(ActionEvent event) throws IOException {
		              buy(event,"Blej.fxml");
		   }
		   @FXML
		   private void buy2(ActionEvent event) throws IOException {
		              buy(event,"Blej2.fxml");
		   }
		   @FXML
		   private void ContinueToAnullo(ActionEvent event) throws IOException {
			   FXMLLoader loader=new FXMLLoader(getClass().getResource("AnulloFature.fxml"));
				root=loader.load();
	  		
	     	   stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	           scene=new Scene(root);
	           stage.setScene(scene);
	           stage.show();
		   }
		public void Kthehu(ActionEvent event) throws IOException {
			   FXMLLoader loader=new FXMLLoader(getClass().getResource("Perdorues.fxml"));
				root=loader.load();
	  		
	     	   stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	           scene=new Scene(root);
	           stage.setScene(scene);
	           stage.show();
		   }
		
		public void kthehuAdmin(ActionEvent event) throws IOException {
			   FXMLLoader loader=new FXMLLoader(getClass().getResource("Administrator.fxml"));
				root=loader.load();
	  		
	     	   stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	           scene=new Scene(root);
	           stage.setScene(scene);
	           stage.show();
		   }

	
}
