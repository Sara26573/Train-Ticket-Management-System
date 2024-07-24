package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Anullo {
	
	Scene scene;
    Parent root;
    Stage stage;

    @FXML
    private TextField NumriIdentifikues;


    public void anullo() {
        SQLConnect lidhjevar = new SQLConnect();
        Connection lidhje = lidhjevar.getConnection();
        try {
            Statement statement = lidhje.createStatement();
            ResultSet data = statement.executeQuery("SELECT * FROM klienta");
            boolean ugjet = false;
            while (data.next()) {
                if (NumriIdentifikues.getText().equals(data.getString("idNumber"))) {
                    ugjet = true;
                    LocalDateTime orderTime = data.getTimestamp("DataOrder").toLocalDateTime(); 
                    LocalDateTime departureTime = data.getTimestamp("DataNisjes").toLocalDateTime();
                  
                    Duration durationFromOrder = Duration.between(orderTime, departureTime);
                    long daysUntilDeparture = durationFromOrder.toDays();
                    long hours=durationFromOrder.toHours();

                    if (hours>=3||daysUntilDeparture >= 1) {
                        cancelTicket(data.getInt("idklienta"));
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Bileta nuk u anullua");
                        alert.setContentText("Bileta juaj nuk u anullua sepse e kaloi afatin prej 1 dit ose 3 ore");
                        alert.showAndWait();
                    }

                    break;
                }

            }
            if (!ugjet) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Bileta nuk u anullua");
                alert.setContentText("Numri juaj i identifikimit nuk gjendet.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	 private void cancelTicket(int ticketId) {
	        SQLConnect connectioncon = new SQLConnect();
	        Connection connect = connectioncon.getConnection();

	        try {
	            PreparedStatement cancelStatement = connect.prepareStatement("DELETE FROM klienta WHERE idklienta = ?");
	            cancelStatement.setInt(1, ticketId);
	            cancelStatement.execute();
	           
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Bileta u anullua");
	            alert.setContentText("Bileta juaj u anullua sepse nuk e kaloi afatin prej 3 oresh.");
	            alert.showAndWait();
	        } catch (SQLException e) {
	            
	            e.printStackTrace();
	        }
	    }
		public void kthehu(ActionEvent event) throws IOException {
			 root=FXMLLoader.load(getClass().getResource("Perdorues.fxml"));
	  	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	        scene=new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}
		public void kthehu2(ActionEvent event) throws IOException {
			 root=FXMLLoader.load(getClass().getResource("Administrator.fxml"));
	  	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	        scene=new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}

}
