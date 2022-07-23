package models;

public class Account {

	//private ClientModel client;
	private int id;
	private int cid;
	private int tid;
	private double fines;
	 
	//private ArrayList<Transaction> transactions;
	
	public Account() {
		 
	}
    public Account(int cid, double fines) {
 
		this.cid = cid;
 		this.fines = fines;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
 	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getTid() {
		return tid;
	}
	 
	public Double getBalance() {
		return fines;
	}

	public void setBalance(Double balance) {
		this.fines = balance;
	}
  
}