package application;

import javax.persistence.*;

@Entity
@Table(name = "scoreinfo")
public class Scoreinfo {
	private int id;
	private String user_name;
	private int score;
	
	
	public Scoreinfo() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}



