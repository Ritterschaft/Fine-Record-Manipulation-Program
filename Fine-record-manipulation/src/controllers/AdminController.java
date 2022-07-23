package controllers;

import java.sql.SQLException;
import java.sql.Statement;

import Dao.DBConnect;
import application.DynamicTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AdminController {

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtAddress;
	@FXML 
	private TextField tidtxt;	//for update input
	@FXML 
	private TextField tidtxt1;	//foe delete input
	@FXML 
	private TextField updFinetxt;
	
	
	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public AdminController() {
		conn = new DBConnect();
	}

	public void viewInfo() {	// View Info

		DynamicTable d = new DynamicTable();
		// call method from DynamicTable class and pass some arbitrary query string
		d.buildData("Select tid,cid,fines from sf_accounts");
	}

	public void updateInfo() {		// updateInfo

		
		pane1.setVisible(true);
		pane2.setVisible(false);
		pane3.setVisible(false);

	}

	public void deleteInfo() {		// deleteInfo

		pane1.setVisible(false);
		pane2.setVisible(true);
		pane3.setVisible(false);
	}

	public void addOffInfo() {		// addOffInfo

		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(true);

	}

	public void submitInfo() {		// submitInfo

		// inserts into admin database
		try {
			// Execute a query
			System.out.println("Inserting staff info records into the database...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table

			sql = "insert into sf_staff(name,address) values ('" + txtName.getText() + "','" + txtAddress.getText()
					+ "')";
			stmt.executeUpdate(sql);
			System.out.println("Staff Info Record created!");

			//conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void submitUpdate() {	// update Record Method
		
		try {
			System.out.println("Updating fine records from the database...");
			stmt = conn.getConnection().createStatement();
			String sql = null;
			//System.out.println(tidtxt.getText());
			//sql = "UPDATE sf_accounts" + "SET fines = " + updFinetxt.getText() + ""  + "WHERE tid = " + tidtxt.getText() + " ";	// !!!!  Unsolved SQL syntax ERROR	!!!!
			sql = "UPDATE sf_accounts " + "SET fines = " + updFinetxt.getText() + " "  + "WHERE tid = " + tidtxt.getText() + " ";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			System.out.println("Fine records updated!");
			DynamicTable d = new DynamicTable();
			d.buildData("Select tid,cid,fines from sf_accounts");
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

	public void submitDelete() {	// Delete record Method
		try {
			System.out.println("Deleting records from the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;
			sql = " DELETE FROM sf_accounts WHERE tid = " + tidtxt1.getText() + " ";	
			System.out.println("Fine Record Deleted!");
			stmt.executeUpdate(sql);
			DynamicTable d = new DynamicTable();
			d.buildData("Select tid,cid,fines from sf_accounts");
		}catch(SQLException se) {
			se.printStackTrace();
		}

	}

}
