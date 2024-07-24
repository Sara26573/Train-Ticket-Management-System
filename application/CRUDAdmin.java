package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CRUDAdmin {
	@FXML
    private TextField burimi;

    @FXML
    private TableColumn<GetData,String> burimiKolone;

    @FXML
    private TextField cmimi;

    @FXML
    private TableColumn<GetData, Float> cmimiKolone;

    @FXML
    private DatePicker data;

    @FXML
    private TableColumn<GetData, Date> dataKolone;

    @FXML
    private TextField destinacioni;

    @FXML
    private TableColumn<GetData, String> destinacioniKolone;

    @FXML
    private TableColumn<GetData, String> klasaKolone;

    @FXML
    private TextField kohezgjatja;

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
    private ComboBox<String> klasa;
    
    @FXML
    private Spinner<Integer> SpinnerMinutes;
    

    @FXML
    private Spinner<Integer> spinnerHours;
    
    ObservableList <Integer>vende1=FXCollections.observableArrayList();
    ObservableList <String>klasa1=FXCollections.observableArrayList("First Class","Business","Premium Economy","Economy");
 public void PopulloCombo() {
	for(int i=1;i<=300;i++) {
		vende1.add(i);
	}
 }
 public void initialize() throws SQLException {
        
    	burimiKolone.setCellValueFactory(new PropertyValueFactory<GetData, String>("burimi"));
    	destinacioniKolone.setCellValueFactory(new PropertyValueFactory<GetData, String>("destinacioni"));
    	vendeKolone.setCellValueFactory(new PropertyValueFactory<GetData, Integer>("vende"));  
    	klasaKolone.setCellValueFactory(new PropertyValueFactory<GetData, String>("klasa"));  
    	dataKolone.setCellValueFactory(new PropertyValueFactory<GetData, Date>("data"));
    	cmimiKolone.setCellValueFactory(new PropertyValueFactory<GetData, Float>("cmimi"));
    	kohezgjatjaKolone.setCellValueFactory(new PropertyValueFactory<GetData, String>("kohezgjatja"));  
    	PopulloCombo();
    	vende.setItems(vende1);
    	klasa.setItems(klasa1);
    	
    	 SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
         spinnerHours.setValueFactory(hourValueFactory);

         SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
         SpinnerMinutes.setValueFactory(minuteValueFactory);
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
        	tedhenatabele.add(new GetData(rs.getInt("idticket"), rs.getString("Burimi"), rs.getString("Destinacioni"), rs.getInt("Vende"), rs.getString("Klasa"), localDateTime,  rs.getFloat("Cmimi"), rs.getString("Kohezgjatja")));
        }
        table.setItems(tedhenatabele);

    }
    
    public void perditeso(MouseEvent ngjarje) {
    	GetData u = table.getSelectionModel().getSelectedItem();
    	
    	vende.getSelectionModel().select(u.getVende()-1);
    	
    	cmimi.setText(Float.toString(u.getCmimi()));
    	
    	LocalDate localDate = u.getData().toLocalDate();
        data.setValue(localDate);
        int hour = u.getData().getHour();
        int minute = u.getData().getMinute();


        data.setValue(localDate);
        spinnerHours.getValueFactory().setValue(hour);
        SpinnerMinutes.getValueFactory().setValue(minute);
      
     	destinacioni.setText(u.getDestinacioni());
     	burimi.setText(u.getBurimi());
     	kohezgjatja.setText(u.getKohezgjatja());
     
     	idticket.setText(String.valueOf(u.getId()));
     	klasa.getSelectionModel().select(u.getKlasa());
      
    }     
    
    public void shto(ActionEvent ngjarje) throws SQLException {
    	  Float cmimi1 = Float.parseFloat(cmimi.getText());
    	  LocalDate selectedDate = data.getValue();
    	  int hours = spinnerHours.getValue();
    	  int minutes = SpinnerMinutes.getValue();
    	  LocalDateTime dateTime = LocalDateTime.of(selectedDate, LocalTime.of(hours, minutes));
    
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        
        PreparedStatement st = connect.prepareStatement("insert into ticket (Burimi,Destinacioni,Vende,Klasa,Data,Cmimi,Kohezgjatja) values (?, ?, ?, ? ,? ,? ,?)");
        st.setString(1, burimi.getText());
        st.setString(2, destinacioni.getText());
        st.setInt(3, vende.getSelectionModel().getSelectedItem());
        st.setString(4, klasa.getSelectionModel().getSelectedItem());
        st.setTimestamp(5, Timestamp.valueOf(dateTime) );
        st.setFloat(6,cmimi1 );
        st.setString(7,kohezgjatja.getText());
        st.execute();
        
        popullo();
      
    }
    
    public void modifiko(ActionEvent event) throws SQLException{
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        Float cmimi1=Float.parseFloat(cmimi.getText());
        LocalDate selectedDate = data.getValue();
  	  int hours = spinnerHours.getValue();
  	  int minutes = SpinnerMinutes.getValue();
  	  LocalDateTime dateTime = LocalDateTime.of(selectedDate, LocalTime.of(hours, minutes));
    	
    	 
    	PreparedStatement st = connect.prepareStatement("UPDATE  ticket SET Burimi = ?, Destinacioni = ?, Vende = ?, Klasa = ?,Data = ?,Cmimi = ?, Kohezgjatja= ? WHERE idticket = ?");
    	  st.setString(1, burimi.getText());
          st.setString(2, destinacioni.getText());
          st.setInt(3, vende.getSelectionModel().getSelectedItem());
          st.setString(4, klasa.getSelectionModel().getSelectedItem());
          st.setTimestamp(5,Timestamp.valueOf(dateTime) );
          st.setFloat(6,cmimi1 );
          st.setString(7,kohezgjatja.getText());
          st.setString(8, idticket.getText());
        
        st.execute();
        popullo();
    
    }
    
    public void fshi(ActionEvent ngjarje) throws SQLException {
        
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        PreparedStatement st = connect.prepareStatement("delete from ticket where idticket  = ?"); 	
        st.setString(1, idticket.getText());
        st.execute();
        popullo();
    }
	  Scene scene;
		Parent root;
		Stage stage;
    public void kthehu(ActionEvent event) throws IOException {
	   root=FXMLLoader.load(getClass().getResource("Administrator.fxml"));
 	   stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       scene=new Scene(root);
       stage.setScene(scene);
       stage.show();
	}
}
