package bean;

public class User {
	//Userを格納する変数
	private String userid;
	//Passを格納する変数
	private String password;
	//emailを格納する変数
	private String email;
	//authorityを格納する変数
	private String authority;

	public User() {
		this.userid = null;
		this.password = null;
		this.email = null;
		this.authority = null;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
