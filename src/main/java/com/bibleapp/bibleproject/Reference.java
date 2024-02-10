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
		return "Reference [book=" + book + ", chapter=" + chapter + ", verse=" + verse + "]";
	}
}
