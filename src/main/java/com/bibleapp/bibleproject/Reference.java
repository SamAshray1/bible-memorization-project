package com.bibleapp.bibleproject;

public class Reference {
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
}
