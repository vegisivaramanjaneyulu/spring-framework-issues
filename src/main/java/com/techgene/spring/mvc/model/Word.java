package com.techgene.spring.mvc.model;

public class Word {
	private int bson_count;
	private String word;
	private String bsonkey;

	

	
	public int getBson_count() {
		return bson_count;
	}
	public void setBson_count(int bson_count) {
		this.bson_count = bson_count;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
		
		
	}
	public String getBsonkey() {
		return bsonkey;
	}
	public void setBsonkey(String bsonkey) {
		this.bsonkey = bsonkey;
	}
	
}
