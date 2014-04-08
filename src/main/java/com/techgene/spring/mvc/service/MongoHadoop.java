package com.techgene.spring.mvc.service;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;

import scala.Tuple2;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoURI;
import com.mongodb.hadoop.MongoOutputFormat;
import com.mongodb.hadoop.splitter.MultiCollectionSplitBuilder;
import com.mongodb.hadoop.splitter.MultiMongoCollectionSplitter;
import com.mongodb.hadoop.util.MongoConfigUtil;

public class MongoHadoop implements Serializable{
	 private static final String SPARK_HOME = "/home/master/apps/spark-0.9.0-incubating-bin-hadoop2";
	public  void mongoDocumentProcess(String[] collection,String output,final String name){
		
		
		
		JavaSparkContext sc = new JavaSparkContext("local", "Simple App",
			      SPARK_HOME, new String[]{"/home/master/mongoworkspace/Springsparkmongo/target/sp.war"});
			    
		Configuration config = new Configuration();
		
	
		
		
		MultiCollectionSplitBuilder mcsb=new MultiCollectionSplitBuilder();
		BasicDBObject query=new BasicDBObject();
		List<BasicDBObject> queries=new ArrayList<BasicDBObject>();
		queries.add(new BasicDBObject("name.first", name));
		queries.add(new BasicDBObject("name.last", name));
		query.put("$or", queries);
		mcsb.add(new MongoURI("mongodb://localhost:27017/"+collection[1]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null)
		    .add(new MongoURI("mongodb://localhost:27017/"+collection[2]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null)
		    .add(new MongoURI("mongodb://localhost:27017/"+collection[3]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null)
		    .add(new MongoURI("mongodb://localhost:27017/"+collection[4]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null)
		    .add(new MongoURI("mongodb://localhost:27017/"+collection[5]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null)
		    .add(new MongoURI("mongodb://localhost:27017/"+collection[6]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null)
		    .add(new MongoURI("mongodb://localhost:27017/"+collection[7]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null)
		    .add(new MongoURI("mongodb://localhost:27017/"+collection[8]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null)
		    .add(new MongoURI("mongodb://localhost:27017/"+collection[9]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null)
		    .add(new MongoURI("mongodb://localhost:27017/"+collection[0]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null)
		    .add(new MongoURI("mongodb://localhost:27017/"+collection[2]), (MongoURI)null, true, (DBObject)null,(DBObject)null, query, false, null);
		   
		
        config.set(MultiMongoCollectionSplitter.MULTI_COLLECTION_CONF_KEY, mcsb.toJSON());
        MongoConfigUtil.setSplitterClass(config, MultiMongoCollectionSplitter.class);
        
       // DBObject query=QueryBuilder.start("first").is("siva").get();
       // MongoConfigUtil.setQuery(config, query);
       

       
      
		MongoConfigUtil.setOutputURI(config,"mongodb://localhost:27017/"+output );
		
       
		JavaPairRDD<Object, BSONObject> mongoRDD = sc.newAPIHadoopRDD(config,
				com.mongodb.hadoop.MongoInputFormat.class, Object.class,
				BSONObject.class);
		
		
		
		// Input contains tuples of (ObjectId, BSONObject)
		JavaRDD<String> words = mongoRDD
				.flatMap(new FlatMapFunction<Tuple2<Object, BSONObject>, String>() {

					@Override
					public Iterable<String> call(Tuple2<Object, BSONObject> arg) {
						
						System.out.println(arg._2);
						//System.exit(1);
						
						DBObject nameDB=(DBObject) arg._2.get("name");
											
						String first=nameDB.get("first").toString();
						String last=nameDB.get("last").toString(); 
						String text="";
						if(first.equals(name)){text=first;}
						else if(last.equals(name)){text=last;}
						else{System.out.println("Search word not Found!!!");System.exit(1);}
						
						
						Object o = text;
						if (o instanceof String) {
							String str = (String) o;
							str = str.toLowerCase().replaceAll("[.,!?\n]", " ");

							return Arrays.asList(str.split(" "));
						} else {
							return Collections.emptyList();
						}
					}

				});

		JavaPairRDD<String, Integer> ones = words
				.map(new PairFunction<String, String, Integer>() {
					public Tuple2<String, Integer> call(String s) {
						
						
						
						return new Tuple2<String, Integer>(s, 1);
					}
				});

		JavaPairRDD<String, Integer> counts = ones
				.reduceByKey(new Function2<Integer, Integer, Integer>() {
					public Integer call(Integer i1, Integer i2) {
						return i1 + i2;
					}
				});

		// Output contains tuples of (null, BSONObject) - ObjectId will be
		// generated by Mongo driver if null
		JavaPairRDD<Object, BSONObject> save = counts
				.map(new PairFunction<Tuple2<String, Integer>, Object, BSONObject>() {
					@Override
					public Tuple2<Object, BSONObject> call(
							Tuple2<String, Integer> tuple) {
						BSONObject bson = new BasicBSONObject();
						bson.put("word", tuple._1);
						bson.put("count", tuple._2);
						System.out.println(tuple._1+"  "+tuple._2);
						
						return new Tuple2<Object, BSONObject>(null, bson);
					}
				});

		// Only MongoOutputFormat and config are relevant
		save.saveAsNewAPIHadoopFile("file:///empty", Object.class,
				Object.class, MongoOutputFormat.class, config);
	}

	

}



















