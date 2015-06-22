package com.mobility.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mobility.model.Board;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
/**
 * Repository for {@link Person}s
 */
@Repository
public class BoardRepository {

	static final Logger logger = LoggerFactory.getLogger(BoardRepository.class);

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired private MongoOperations mongoOps;

    public void logAllBoards() {
        List<Board> results = mongoTemplate.findAll(Board.class);
        logger.info("Total amount of Boards: {}", results.size());
        logger.info("Results: {}", results);
    }
    
    public List<Board> findall() {
    	
        List<Board> results = mongoTemplate.findAll(Board.class);
        System.out.println(results);
        return results;

    }
    
    public List<Board> pagebyPage(int page, int size){
    	
    	if(page == 9999 && size==9999){
    		List<Board> popBetween = mongoOps.findAll(Board.class);
    		return popBetween;
    	}else{
    	 Query between = query(where("_id").gt(page*size-size).lte(page*size));
         List<Board> popBetween = mongoOps.find(between, Board.class);
    	return popBetween;
    	}
    }
    
    
    public int insertBoard() throws IOException {

    	ClassLoader classLoader = getClass().getClassLoader();
    	File file = new File(classLoader.getResource("data.csv").getFile());
    	String thisLine;
    	int i=1;
    	 BufferedReader br = new BufferedReader(new FileReader(file));
    	//try (Scanner scanner = new Scanner(file)) {
    		List<Board> lbs= new ArrayList<Board>();
    		while ((thisLine = br.readLine()) != null) { 
    			String line[] = thisLine.split(",");
    			lbs.add(new Board ( i,line[0],line[1],line[2],line[3],line[4])); 
    			i++;
    		}
    		 mongoTemplate.insertAll(lbs);
    		 return lbs.size();
    	}
     
 
    

    public void createBoardCollection() {
    	logger.info("Repo created");
        if (!mongoTemplate.collectionExists(Board.class)) {
            mongoTemplate.createCollection(Board.class);
        }
    }

    /**
     * Drops the {@link Board} collection if the collection does already exists
     */
    public void dropBoardCollection() {
        if (mongoTemplate.collectionExists(Board.class)) {
            mongoTemplate.dropCollection(Board.class);
        }
    }
  
}
