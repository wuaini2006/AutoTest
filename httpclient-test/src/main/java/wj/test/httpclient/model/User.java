package wj.test.httpclient.model;

import lombok.Data;

@Data
public class User {
	private int id;
	private String userName;
	private String password;
	private String sex;
	private int age;
	private String permission;
	private String isDelete;
	
	
	public String toString() {
		
		return ("{id:" + id + "," +
				"userName:" + userName + "," +
				"password:" + password + "," +
				"sex:" + sex + "," +
				"age:" + age + "," +
				"permission:" + permission + "," +
				"isDelete:" + isDelete + "}" 
				);
	}
	
}
