package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.ClientModel;
 
public class ClientController implements Initializable {
	
	static int userid;
	ClientModel cm;
	
	/***** TABLEVIEW intel *********************************************************************/

	@FXML
	private TableView<ClientModel> tblAccounts;
	@FXML
	private TableColumn<ClientModel, String> tid;
	@FXML
	private TableColumn<ClientModel, String> fines;

	public void initialize(URL location, ResourceBundle resources) {
		tid.setCellValueFactory(new PropertyValueFactory<ClientModel, String>("tid"));
		fines.setCellValueFactory(new PropertyValueFactory<ClientModel, String>("fines"));

		// auto adjust width of columns depending on their content
		tblAccounts.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblAccounts));

		tblAccounts.setVisible(false); // set invisible initially
	}

    public void customResize(TableView<?> view) {

        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }
    
	public void viewAccounts() throws IOException {

		tblAccounts.getItems().setAll(cm.getAccounts(userid)); // load table data from ClientModel List
		tblAccounts.setVisible(true); // set tableview to visible if not
		
		System.out.println(cm.getClientInfo());

	}

	/***** End TABLEVIEW intel *********************************************************************/

	public void logout() {
		// System.exit(0);
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/styles.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public void payFines() {

		TextInputDialog dialog = new TextInputDialog("Enter dollar amount");
		dialog.setTitle("Fine Payment Entry Portal");
		dialog.setHeaderText("Enter Transaction");
		dialog.setContentText("Please enter your Fine Balance:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("Fine Balance Entry : $AUD " + result.get());
			cm.insertRecord(userid,Double.parseDouble(result.get()));
		}

		// The Java 8 way to get the response value (with lambda expression).
		result.ifPresent(fines -> System.out.println("Fine Balance Entry: " + fines));

	}

	public static void setUserid(int user_id) {
		userid = user_id;
		System.out.println("Welcome id " + userid);
	}

	public ClientController() {

		
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("Welcome Panel");
		 alert.setHeaderText("Springfield County Residency Information management System - NSW,Australia");
		 alert.setContentText("Welcome !"); alert.showAndWait();
		 

		cm = new ClientModel();

	}

}
