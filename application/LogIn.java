package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogIn {
	   @FXML
	    private PasswordField passwordLogIn;

	    @FXML
	    private TextField usernameLogIn;
	    
	    @FXML
	    private PasswordField passwordSignUp;

	    @FXML
	    private TextField usernameSignUp;
	    
	    Scene scene;
		Parent root;
		Stage stage;
		
		public void changeScene(ActionEvent event,String fxmlFile) throws IOException {
			 root=FXMLLoader.load(getClass().getResource(fxmlFile));
	   	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	         scene=new Scene(root);
	         stage.setScene(scene);
	         stage.show();
		}
	    
	  
	    @FXML
	    void SIGNUP(ActionEvent event) throws IOException {
	    	  boolean ugjet=false;
	      	String username_2=usernameSignUp.getText();
	      	String password_2=passwordSignUp.getText();
	      	SQLConnect lidhjevar = new SQLConnect();
	          Connection lidhje = lidhjevar.getConnection();
	      	try {
	  			PreparedStatement Pstatement = lidhje.prepareStatement("insert into anetare(username,password) values(?,?)");
	  			Statement statement=lidhje.createStatement();
	  	        ResultSet data=statement.executeQuery("select * from anetare");
	  	        
	  	        while(data.next()) {
	  	        		if(username_2.equals(data.getString("username"))){
	  	        	    ugjet=true;
	  	        		Alert alert=new Alert(Alert.AlertType.INFORMATION);
	  		   		    alert.setTitle("Username i perdorur! ");
	  		            alert.setContentText("Vendosni nje username tjeter");
	  		 	        alert.showAndWait();
	  		 	        break;
	  		 	       
	  	            	
	  	        	}
	  	
	  	        }
	  	        
	  	       	if(username_2.equals("") || password_2.equals("")) {
	  	       	 ugjet=true;
	          		Alert alert=new Alert(Alert.AlertType.INFORMATION);
	  	   		    alert.setTitle("Jo te gjitha fushat e plotesuara");
	  	            alert.setContentText("Plotesoni te gjitha fushat.");
	  	 	        alert.showAndWait();
	          	}
	  	       	if(!ugjet){
	  	        	Pstatement.setString(1, username_2);
	  	        	Pstatement.setString(2, password_2);
	  	            Pstatement.execute();
	  	        	Alert alert=new Alert(Alert.AlertType.INFORMATION);
	  	   		    alert.setHeaderText("U regjistruat ne aplikacion.");
	  	 	        alert.showAndWait();
	  	 	        changeScene(event,"Perdorues.fxml");
	                  ugjet=true;
	                  
	  	 	        
	  	        }
	  			
	  		} catch (SQLException e) {

	  			e.printStackTrace();
	  		}
	    }
	    
	    @FXML
	    void Login(ActionEvent event) throws IOException {
	    	changeScene(event,"Login.fxml");
	    }

	    @FXML
	    void SIGNIN(ActionEvent event) throws IOException {
	        boolean ugjet = false;
	        String username_2 = usernameLogIn.getText();
	        String password_2 = passwordLogIn.getText();

	        SQLConnect lidhjeobj = new SQLConnect();
	        Connection lidhje = lidhjeobj.getConnection();
	        try {
	            Statement statement = lidhje.createStatement();
	            ResultSet executeQ = statement.executeQuery("select * from anetare");

	            while (executeQ.next()) {
	                if (executeQ.getString("username").equals(username_2) && executeQ.getString("password").equals(password_2)) {
	                    if (username_2.equals("Admin_Treni") && password_2.equals("Admin1Admin")) {
	                        changeScene(event, "Administrator.fxml");
	                    } else {
	                        changeScene(event, "Perdorues.fxml");
	                    }
	                    ugjet = true;
	                    break;
	                }
	            }

	            if (!ugjet) {
	                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("Te dhenat te gabuara!");
	                alert.setContentText("Vendosni te dhena te sakta!");
	                alert.showAndWait();
	            }
	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	        }
	    }

	    @FXML
	    void SignUp(ActionEvent event) throws IOException {
	    	changeScene(event,"SignUp.fxml");
	    }
	    
	    public void Continue(ActionEvent event) throws IOException{
	    	changeScene(event,"Perdorues.fxml");
	    }
}
