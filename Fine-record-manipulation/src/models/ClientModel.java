package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Dao.DBConnect;

public class ClientModel extends DBConnect implements User<Manager>
{

	private int cid;
	private int tid;
	private double fines;

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	Manager cliManager; // set up Bank object

	public ClientModel()
	{

		conn = new DBConnect();

		// simulate bank data affiliation of client
		cliManager = new Manager();
		cliManager.setManagerId(100);
		cliManager.setManagerName("Springfield County Council");
		cliManager.setManagerAddress("13 Liberty Rd, Sydney, NSW 2113 AUSTRALIA");
	}

	/* getters & setters */

	public int getCid()
	{
		return cid;
	}

	public void setCid(int cid)
	{
		this.cid = cid;
	}

	public void setTid(int tid)
	{
		this.tid = tid;
	}

	public int getTid()
	{
		return tid;
	}

	public Double getFines()
	{
		return fines;
	}

	public void setFines(Double fines)
	{
		this.fines = fines;
	}

	// INSERT INTO METHOD
	public void insertRecord(int cid, double bal)
	{

		try
		{
			setCid(cid);
			// Execute a query
			System.out.println("Inserting client fine payment record into the database...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include data to the database table

			sql = " insert into sf_accounts(cid, fines) values('" + cid + "', '" + bal + "')";

			stmt.executeUpdate(sql);
			//conn.getConnection().close();

			System.out.println("Balance record inserted $" + bal + " for ClientID " + cid);

		} catch (SQLException se)
		{
			se.printStackTrace();
		}
	}

	public List<ClientModel> getAccounts(int cid)
	{
		List<ClientModel> accounts = new ArrayList<>();
		String query = "SELECT tid,fines FROM sf_accounts WHERE cid = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query))
		{
			statement.setInt(1, cid);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				ClientModel account = new ClientModel();
				// grab record data by table field name into ClientModel account object
				account.setTid(resultSet.getInt("tid"));
				account.setFines(resultSet.getDouble("fines"));
				accounts.add(account); // add account data to arraylist
			}
		} catch (SQLException e)
		{
			System.out.println("Error fetching Accounts: " + e);
		}
		return accounts; // return arraylist
	}

	@Override
	public Manager getClientInfo()
	{
		// TODO Auto-generated method stub
		return cliManager;
	}
}