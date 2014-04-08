package com.techgene.spring.mvc.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.techgene.spring.mvc.model.Word;


public class WordService {

 MongoHadoop md=new MongoHadoop();
	

	public void getDocument(Word w){
		
		String[] inputCollections={"spark.input1","spark.input2","spark.input3","spark.input4","spark.input5","spark.input6","spark.input7","spark.input8","spark.input9","spark.input10"};
		String outputCollection="spark.sparkoutput1";
		
		String name=w.getWord();
		System.out.println("hisiva");
	 md.mongoDocumentProcess(inputCollections,outputCollection,name);
	 
		
	 
		//return bs;
	}
	
	

}
