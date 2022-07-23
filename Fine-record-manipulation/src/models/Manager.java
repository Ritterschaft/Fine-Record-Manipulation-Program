package models;

public class Manager {

	int managerId;
	String managerName;
	String managerAddress;

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerAddress() {
		return managerAddress;
	}

	public void setManagerAddress(String managerAddress) {
		this.managerAddress = managerAddress;
	}

	public Manager(Integer id, String name, String address) {
		this.managerId = id;
		this.managerName = name;
		this.managerAddress = address;
	}

	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {

		String managerData = "Management System user Affiliation:\nID- " + managerId;
		managerData += "\nName- " + managerName;
		managerData += "\nAddress-" + managerAddress + "\n";

		return managerData;
	}

}
