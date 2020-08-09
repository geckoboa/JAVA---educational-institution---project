package hr.java.vjezbe;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;
    
    public void prijaviMe() {
    	
    	System.out.println("Prijava");
    }
	
}
