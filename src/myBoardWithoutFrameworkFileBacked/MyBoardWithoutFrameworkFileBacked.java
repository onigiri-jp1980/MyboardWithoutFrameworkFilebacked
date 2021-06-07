package myBoardWithoutFrameworkFileBacked;

import java.io.Serializable;
import java.sql.Timestamp;

public class MyBoardWithoutFrameworkFileBacked implements Serializable {

	int id;
	String name;
	String email;
	String msg;
	Timestamp postedAt;
	Timestamp updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Timestamp getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(Timestamp postedAt) {
		this.postedAt = postedAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
