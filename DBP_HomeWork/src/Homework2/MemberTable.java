package Homework2;

public class MemberTable {
	private String id;
	private String pwd;
	private String name;
	private String tel;
	private String email;
	private String dept;
	private String sex;
	private String birth;
	private String introduction;
	
	public MemberTable(String id, String pwd, String name, String tel, String dept, String sex, String birth,
			String introduction, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.tel = tel;
		this.dept = dept;
		this.sex = sex;
		this.birth = birth;
		this.introduction = introduction;
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
