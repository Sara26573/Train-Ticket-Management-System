package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Administrator {
	  Scene scene;
		Parent root;
		Stage stage;
		
		public void Bli(ActionEvent event) throws IOException {
			 root=FXMLLoader.load(getClass().getResource("Klient2.fxml"));
	  	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	        scene=new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}
	   
		public void Anullo(ActionEvent event) throws IOException {
			 root=FXMLLoader.load(getClass().getResource("AnulloFature2.fxml"));
	  	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	        scene=new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}
		
		   
			public void loginKthehu(ActionEvent event) throws IOException {
				 root=FXMLLoader.load(getClass().getResource("Login.fxml"));
		  	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		        scene=new Scene(root);
		        stage.setScene(scene);
		        stage.show();
			}
			public void ShtoBileta(ActionEvent event) throws IOException {
				 root=FXMLLoader.load(getClass().getResource("Tickets.fxml"));
		  	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		        scene=new Scene(root);
		        stage.setScene(scene);
		        stage.show();
			}
			public void ShtoKlienta(ActionEvent event) throws IOException {
				 root=FXMLLoader.load(getClass().getResource("Shto1Klienta.fxml"));
		  	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		        scene=new Scene(root);
		        stage.setScene(scene);
		        stage.show();
			}

	 
}
