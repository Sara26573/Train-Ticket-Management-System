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
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CRUDklienta {

    @FXML
    private TableColumn<KlientaData, String> DataSkadences;

    @FXML
    private TextField DataSkadencesTextfield;

    @FXML
    private TextField NrTelefoniEmailtextfield;

    @FXML
    private TableColumn<KlientaData, String> NumerKrediti;

    @FXML
    private TextField NumerKreditiTextfield;

    @FXML
    private TableColumn<KlientaData, String> NumerTelefoni;

    @FXML
    private TableColumn<KlientaData, Float> cmimi;

    @FXML
    private TextField cmimitextfield;

    @FXML
    private TableColumn<KlientaData, Integer> cvv;

    @FXML
    private TextField cvvTextfield;
    @FXML
    private TextField burimitextfield;

    @FXML
    private TableColumn<KlientaData, String> burimi;

    @FXML
    private TableColumn<KlientaData, Date> dataRezervimit;

    @FXML
    private TextField dataRezervimitTextField;

    @FXML
    private TableColumn<KlientaData, String> destiinacioni;

    @FXML
    private TextField destinacioniTextfield;

    @FXML
    private TableColumn<KlientaData, String> emer;

    @FXML
    private TextField emertextfield;

    @FXML
    private Label id;

    @FXML
    private TableColumn<KlientaData, String> mbiemer;

    @FXML
    private TextField mbiemertextfield;

    @FXML
    private TableView<KlientaData> table;

    @FXML
    private TableColumn<KlientaData, Integer> vende;
    
    @FXML
    private TableColumn<KlientaData, Date> dataNisjes;
    
    @FXML
    private TextField DataNisjesTextField;
    @FXML
    private TableColumn<KlientaData, String> NumerId;

    @FXML
    private TextField vendetextfield;
    @FXML
    private TextField numerIdTextfield;

    
 public void initialize() throws SQLException {
        
	    emer.setCellValueFactory(new PropertyValueFactory<KlientaData, String>("emer"));
	    mbiemer.setCellValueFactory(new PropertyValueFactory<KlientaData, String>("mbiemer"));
	    NumerTelefoni.setCellValueFactory(new PropertyValueFactory<KlientaData, String>("numerTelefoni"));
	    NumerKrediti.setCellValueFactory(new PropertyValueFactory<KlientaData, String>("NumerKrediti"));
	    DataSkadences.setCellValueFactory(new PropertyValueFactory<KlientaData, String>("daatSkadences"));
	    cvv.setCellValueFactory(new PropertyValueFactory<KlientaData, Integer>("cvv"));
	    burimi.setCellValueFactory(new PropertyValueFactory<KlientaData, String>("burimi"));
	    destiinacioni.setCellValueFactory(new PropertyValueFactory<KlientaData, String>("destinacioni"));
	    vende.setCellValueFactory(new PropertyValueFactory<KlientaData, Integer>("vende"));
	    cmimi.setCellValueFactory(new PropertyValueFactory<KlientaData, Float>("cmimi"));
	    NumerId.setCellValueFactory(new PropertyValueFactory<KlientaData, String>("idNumber"));
	    dataRezervimit.setCellValueFactory(new PropertyValueFactory<KlientaData, Date>("dataeRezervimit"));
	    dataNisjes.setCellValueFactory(new PropertyValueFactory<KlientaData, Date>("dataeNisjes"));
	    
	      
    	popullo();
    }
    
    public void popullo() throws SQLException {
    	ObservableList<KlientaData> tedhenatabele = FXCollections.observableArrayList();
    	SQLConnect lidhje = new SQLConnect();
        Connection connect = lidhje.getConnection();
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("select * from klienta");
        while (rs.next()) {
        	Timestamp timestamp = rs.getTimestamp("DataOrder");
 	        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        	Timestamp timestamp1 = rs.getTimestamp("DataNisjes");
 	        LocalDateTime localDateTime1 = timestamp1.toLocalDateTime();
        	tedhenatabele.add(new KlientaData( rs.getString("Emer"),rs.getString("Mbiemer"),rs.getString("NumerTelefoniOseEmail"),rs.getString("NumerKreditiOseDebiti"),rs.getString("DataSkadences"),rs.getInt("CVV"),rs.getString("Burimi")
        			,rs.getString("Destinacioni"),rs.getInt("Vende"),rs.getFloat("Cmimi"),rs.getString("IdNumber"),localDateTime,localDateTime1,rs.getInt("idklienta")));
        }
        table.setItems(tedhenatabele);

    }
    
    public void perditeso(MouseEvent ngjarje) {
    	
    	
    	KlientaData u = table.getSelectionModel().getSelectedItem();
    	DataSkadencesTextfield.setText(u.getDaatSkadences());
    	NrTelefoniEmailtextfield.setText(u.getNumerTelefoni());
    	NumerKreditiTextfield.setText(u.getNumerKrediti());
    	cmimitextfield.setText(Float.toString(u.getCmimi()));
    	cvvTextfield.setText(Integer.toString(u.getCvv()));
    	burimitextfield.setText(u.getBurimi());
    	dataRezervimitTextField.setText(String.valueOf(u.getDataeRezervimit()));
    	destinacioniTextfield.setText(u.getDestinacioni());
    	emertextfield.setText(u.getEmer());
    	mbiemertextfield.setText(u.getMbiemer());
    	id.setText(String.valueOf(u.getId()));
    	DataNisjesTextField.setText(String.valueOf(u.getDataeNisjes()));
    	numerIdTextfield.setText(u.getIdNumber());
    	vendetextfield.setText(String.valueOf(u.getVende()));
    }     
    
    public void shto(ActionEvent ngjarje) throws SQLException {
  
   	  SQLConnect connectioncon = new SQLConnect();
      Connection connect = connectioncon.getConnection();
      
      Integer cvv1=Integer.parseInt(cvvTextfield.getText());
      Integer vende=Integer.parseInt(vendetextfield.getText());
      Float cmimi=Float.parseFloat(cmimitextfield.getText());
      
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
      LocalDateTime dateTime = LocalDateTime.parse(dataRezervimitTextField.getText(), formatter);
      DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
      LocalDateTime dateTime1 = LocalDateTime.parse(dataRezervimitTextField.getText(), formatter1);
   
      PreparedStatement st = connect.prepareStatement("insert into klienta (Emer,Mbiemer,NumerTelefoniOseEmail,NumerKreditiOseDebiti,DataSkadences,CVV,Burimi,Destinacioni,Vende,Cmimi,IdNumber,DataOrder,DataNisjes) values (?, ?, ?, ? ,? ,? ,?,?, ?, ?, ? ,? ,? )");
      st.setString(1, emertextfield.getText());
      st.setString(2, mbiemertextfield.getText());
      st.setString(3, NrTelefoniEmailtextfield.getText());
      st.setString(4, NumerKreditiTextfield.getText());
      st.setString(5, DataSkadencesTextfield.getText());
      st.setInt(6, cvv1);
      st.setString(7, burimitextfield.getText());
      st.setString(8, destinacioniTextfield.getText());
      st.setInt(9, vende);
      st.setFloat(10, cmimi);
      st.setString(11,numerIdTextfield.getText());
      st.setTimestamp(12, Timestamp.valueOf(dateTime) );
      st.setTimestamp(13, Timestamp.valueOf(dateTime1) );
     
      st.execute();
      
      popullo();
    
  }
    public void fshi(ActionEvent ngjarje) throws SQLException {
        
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        PreparedStatement st = connect.prepareStatement("delete from klienta where idklienta  = ?"); 	
        st.setString(1, id.getText());
        st.execute();
        popullo();
    }    
    public void modifiko(ActionEvent event) throws SQLException{
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        Integer cvv1=Integer.parseInt(cvvTextfield.getText());
        Integer vende=Integer.parseInt(vendetextfield.getText());
        Float cmimi=Float.parseFloat(cmimitextfield.getText());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dataRezervimitTextField.getText(), formatter);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(dataRezervimitTextField.getText(), formatter1);
   
    	 
    	PreparedStatement st = connect.prepareStatement("UPDATE  klienta SET Emer = ?, Mbiemer = ?, NumerTelefoniOseEmail = ?, NumerKreditiOseDebiti = ?,DataSkadences = ?,CVV = ?, Burimi= ? ,Destinacioni=?,Vende=?,Cmimi=?,IdNumber=?,DataOrder=?,DataNisjes=? WHERE idklienta = ?");
    	 st.setString(1, emertextfield.getText());
         st.setString(2, mbiemertextfield.getText());
         st.setString(3, NrTelefoniEmailtextfield.getText());
         st.setString(4, NumerKreditiTextfield.getText());
         st.setString(5, DataSkadencesTextfield.getText());
         st.setInt(6, cvv1);
         st.setString(7, burimitextfield.getText());
         st.setString(8, destinacioniTextfield.getText());
         st.setInt(9, vende);
         st.setFloat(10, cmimi);
         st.setString(11,numerIdTextfield.getText());
         st.setTimestamp(12, Timestamp.valueOf(dateTime) );
         st.setTimestamp(13, Timestamp.valueOf(dateTime1) );
         st.setString(14, id.getText());
        
        
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
