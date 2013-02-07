package classes;

import java.io.Serializable;
import java.util.ArrayList;


public class Student implements Serializable {
	private String ID;
	private String firstName;
	private String lastName;
	private String favoriteColor;
	private ArrayList<Student> friends;
	
	public String getID(){
		return ID;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getFavoriteColor(){
		return favoriteColor;
	}
	
	public ArrayList<Student> getFriends(){
		return friends;
	}
	
	public void setID(String ID){
		this.ID = ID;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void setFavoriteColor(String favoriteColor){
		this.favoriteColor = favoriteColor;
	}
	
	public void setFriends(ArrayList<Student> friends){
		this.friends = friends;
	}
	
	public String toString(){
		String s = "";
		s += "ID: " + ID + "\n";
		s += "FirstName: " + firstName + "\n";
		s += "LastName: " + lastName + "\n";
		s += "FavoriteColor: " + favoriteColor + "\n";
		s += "Friends: ";
		for (int i = 0; i < friends.size(); i++)
			s += friends.get(i).getID();
		s+= "\n";
		return s;
	}

}