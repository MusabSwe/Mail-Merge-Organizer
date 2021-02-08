import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;




public class PasswordDialog extends Application {
	
	String s="";
	public void read(String file_name,String s) throws IOException{

		this.s=s;

		String row ;
		List<String> ar = new ArrayList<String>();
		   int n = 0;
		BufferedReader csvReader = new BufferedReader(new FileReader(file_name));
		while ((row = csvReader.readLine()) != null) {
			ar.add(n,row);
			n++;
		}
		ar.remove(0);
			co = new Contact[ar.size()];
		for(int i=0 ; i<ar.size() ; i++){

			co[i] = new Contact(ar.get(i).split(","));

		//	System.out.println(co[i].toString());
		}
		csvReader.close();
		}
	
//	public static void main(String[] args){
//		Application.launch(args);
//	}
	static LoginCredentials login;
	Files fl = new Files();
	Contact[] co;
	@Override
	
	
	public void start(Stage primaryStage) {
		Dialog<LoginCredentials> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Enter your portal username and password:");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new LoginCredentials(username.getText(), password.getText());
		    }
		    return null;
		});

		Optional<LoginCredentials> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
			login = usernamePassword;
		});
		
		String userName = PasswordDialog.login.getUserName();
    	String password1 = PasswordDialog.login.getPassword();

    	//type a recipient email, it could be any valid email
   // 	String[] toEmail = {"s201783410@kfupm.edu.sa","s201724290@kfupm.edu.sa","s201783410@kfupm.edu.sa"};
    //	ArrayList<String> s = new ArrayList<String>();
//    	for(int i=0;i<fl.s.length;i++){
//    	//	s[i].equals(fl.s[i]);
//    	}

    	String subject = "A message from a caring friend";


    	/*
		 * messageToBeSent is a string.
		*/
		String messageToBeSent = "Hello,\n\n If you get this, it means that you have successfully sent an email from your java program\n\n Thank you!";


    	//This prompt the user to enter a password
    	//password = new PasswordDialog().getPasswordField().toString();

		//sends an email
		
//	String [] a =	userInterface.s;
		for(int i=0;i<co.length;i++){
			
		SendEmailOffice365 mailer =  new SendEmailOffice365(userName, password1 , co[i].getEmail(), subject, s);
		if (mailer.sendEmail())
			
			System.out.println("Email was sent to: " + co[i].getEmail() + "  Successfully");
		
		else
			System.out.println("Error, email wasn't sent to " + co[i].getEmail());
			
	}
	}
}