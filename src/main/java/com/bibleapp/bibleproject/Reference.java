package com.bibleapp.bibleproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reference {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String book;
	private String chapter;
	private String verse;
	
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public String getVerse() {
		return verse;
	}
	public void setVerse(String verse) {
		this.verse = verse;
	}
	@Override
	public String toString() {
		return book + " " + chapter + ":" + verse;
	}
	public Reference(String book, String chapter, String verse) {
		super();
		this.book = book;
		this.chapter = chapter;
		this.verse = verse;
	}
	public Reference() {
		// TODO Auto-generated constructor stub
	}
}
