package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Blej {
   
	    @FXML
	    private Label CmimiLabel;

	    @FXML
	    private Label DataLabel;

	    @FXML
	    private Label DestinacioniLabel;

	    @FXML
	    private Label KlasaLabel;

	    @FXML
	    private TextField NumerKarte;

	    @FXML
	    private TextField NumerTelefoni_AdreseEmail;

	    @FXML
	    private Label OraENisjesLabel;

	    @FXML
	    private Label VendeLabel;

	    @FXML
	    private Label burimiLabel;

	    @FXML
	    private TextField emer;

	    @FXML
	    private TextField mbiemer;
	    
	    @FXML
	    private TextField dataSkadences;
	    
	    @FXML
	    private TextField NumerIdentifikimi;
	    
	    @FXML
	    private TextField cvv;

	    @FXML
	    private Label totalVende;
	    @FXML
	    private Label id;

	    @FXML
	    private Label cmimixVend;
	    
	    Scene scene;
	 	Parent root;
	    Stage stage;

	    
	public void updateLabels(String burimiText, String cmimiText, String dataText, String destinacioniText, String klasaText, String kohezgjatjaText, String vende2Text,int vende,int id1,float cmimi) {
        String totalVende1=Integer.toString(vende);
        String idBlej=Integer.toString(id1);
        
        String cmimi1=Float.toString(cmimi);
		
		CmimiLabel.setText(cmimiText);
        DataLabel.setText(dataText);
        DestinacioniLabel.setText(destinacioniText);
        KlasaLabel.setText(klasaText);
        OraENisjesLabel.setText(kohezgjatjaText);
        VendeLabel.setText(vende2Text);
        burimiLabel.setText(burimiText);
        totalVende.setText(totalVende1);
        id.setText(idBlej);
        cmimixVend.setText(cmimi1);
        
    }
	
	public void addToDatabase() {
		SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        
        if(emer.getText().isEmpty()||mbiemer.getText().isEmpty()||NumerTelefoni_AdreseEmail.getText().isEmpty() ||NumerKarte.getText().isEmpty()||dataSkadences.getText().isEmpty()||VendeLabel.getText().isEmpty()
        		||burimiLabel.getText().isEmpty()||DestinacioniLabel.getText().isEmpty()||cvv.getText().isEmpty()||CmimiLabel.getText().isEmpty()||NumerIdentifikimi.getText().isEmpty()||DataLabel.getText().isEmpty()) {
            	  Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Hapesire Bosh");
	            alert.setContentText("Keni lene nje hapesire pa u mbushur.");
	            alert.showAndWait();
        }
        
        Integer id4=Integer.parseInt(id.getText());
        
        Integer vende1=Integer.parseInt(VendeLabel.getText());
        Integer cvv1=Integer.parseInt(cvv.getText());
        Integer VendeTotal2=Integer.parseInt(totalVende.getText());
        
        String cmimi=CmimiLabel.getText();
        String result2=cmimi.replaceAll("\\ €", "");
        Float floatcmimi=Float.parseFloat(result2);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); 
        LocalDateTime dateTime = LocalDateTime.parse(DataLabel.getText(), formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        if (VendeTotal2 >= vende1) {
            
            int remainingSeats = VendeTotal2 - vende1; 
            PreparedStatement updateSeatsStatement;
			try {
				updateSeatsStatement = connect.prepareStatement("UPDATE ticket SET Vende = ? WHERE idticket = ?");
				updateSeatsStatement.setInt(1, remainingSeats);
		        updateSeatsStatement.setInt(2, id4);
		        updateSeatsStatement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          
       
        
        
        try {
            	PreparedStatement st = connect.prepareStatement("insert into klienta (Emer,Mbiemer,NumerTelefoniOseEmail,NumerKreditiOseDebiti,DataSkadences,CVV,Burimi,Destinacioni,Vende,Cmimi,IdNumber,DataOrder,DataNisjes) values (?, ?, ?, ? ,? ,? ,?,?, ?, ?, ? ,? ,? )");
			    st.setString(1, emer.getText());
			    st.setString(2, mbiemer.getText());
		        st.setString(3,NumerTelefoni_AdreseEmail.getText());
		        st.setString(4, NumerKarte.getText());
		        st.setString(5, dataSkadences.getText());
		        st.setInt(6, cvv1);
		        st.setString(7, burimiLabel.getText());
		        st.setString(8,DestinacioniLabel.getText());
		        st.setInt(9,vende1);
		        st.setFloat(10,floatcmimi);
		        st.setString(11, NumerIdentifikimi.getText());
		        st.setObject(12,currentDateTime);
		        st.setObject(13,dateTime);
		        st.execute();
		        
		
	            
	             String billText = 
	            		 "--------------------------------------------------\n" +
	            		 "           *      *   FATURE   *      *           \n" +
	            		 "--------------------------------------------------\n" +
	                     "           Emer       :       "+emer.getText() + "\n" +
	                     "           Mbiemer       :       "+mbiemer.getText() + "\n" +
	                     "           Numer Telefoni / Adrese email       :       "+NumerTelefoni_AdreseEmail.getText()+ "\n" +
	                     "           Burimi       :       "+burimiLabel.getText() + "\n" +
	                     "           Vende       :       "+VendeLabel.getText() + "\n" +
	                     "           Cmimi       :       "+cmimixVend.getText() + "\n" +
	                     
	                     "           Destinacioni       :       "+DestinacioniLabel.getText() + "\n" +
	                     "--------------------------------------------------\n"+
	                     "           TOTALI        :       "+ CmimiLabel.getText()+ "\n"+
	                      "**************************                        \n"+
	                      "    Data :"+DataLabel.getText() ;
	             Text textNode = new Text(billText);
	             printBill(textNode);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        }
    
       
        
	}
	
	
	   private void printBill(Node billText) {
		  
           Printer printer = Printer.getDefaultPrinter();
           PrinterJob job = PrinterJob.createPrinterJob(printer);
           if (job != null) {
               boolean success = job.printPage(billText);
               if (success) {
                   job.endJob();
               }
           }
       }
	   
		public void kthehu(ActionEvent event) throws IOException {
			 root=FXMLLoader.load(getClass().getResource("Klient.fxml"));
	  	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	        scene=new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}
		public void kthehu2(ActionEvent event) throws IOException {
			 root=FXMLLoader.load(getClass().getResource("Klient2.fxml"));
	  	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	        scene=new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}

}
