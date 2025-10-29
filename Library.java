package com.Infobeans.assignment_66_ques_1;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Library {
	@Id
	private int libraryId ;
	private String libraryName ;
	private String location ;
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "library")
	private List<Books> books = new ArrayList<Books>();
	public Library() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Library(int libraryId, String libraryName, String location, List<Books> books) {
		super();
		this.libraryId = libraryId;
		this.libraryName = libraryName;
		this.location = location;
		this.books = books;
	}
	public int getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Books> getBooks() {
		return books;
	}
	public void setBooks(List<Books> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "Library [libraryId=" + libraryId + ", libraryName=" + libraryName + ", location=" + location
				+ "]";
	}	

}