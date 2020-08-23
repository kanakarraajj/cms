package source;

public class FormData {

	private String id;
	private String name;
	private String password;
	private String dob;
	private String married;
	private String qualification;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	@Override
	public String toString() {
		return "FormData [id=" + id + ", name=" + name + ", password=" + password + ", dob=" + dob + ", married="
				+ married + ", qualification=" + qualification + "]";
	}
	public FormData(String id, String name, String password, String dob, String married, String qualification) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.dob = dob;
		this.married = married;
		this.qualification = qualification;
	}
	public FormData() {
	}
	
}
