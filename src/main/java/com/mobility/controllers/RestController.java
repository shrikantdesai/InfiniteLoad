package com.mobility.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobility.model.Board;
import com.mobility.rest.multipleBoardResponse;
import com.mobility.rest.restResponse;
import com.mobility.dao.BoardRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RestController {
	@Autowired
	private BoardRepository repo;
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Default Home REST page. The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "status";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@ResponseBody
	public restResponse create(Locale locale, Model model) {
	//	logger.info("Default Home REST page. The client locale is {}.", locale);
		restResponse extResp;
		repo.createBoardCollection();
		int count = 0;
		try {
			count = repo.insertBoard();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extResp= new restResponse(count,true, "Collection created ");
		return extResp;
	}
	
	@RequestMapping(value = "/clean", method = RequestMethod.GET)
	@ResponseBody
	public restResponse clean(Locale locale, Model model) {
	//	logger.info("Default Home REST page. The client locale is {}.", locale);
		restResponse extResp;
		repo.dropBoardCollection();
		extResp= new restResponse(0,true, "Collection Dropped ");
		return extResp;
	}
	
	
	@RequestMapping(value="/details", method=RequestMethod.GET)
	@ResponseBody
	public multipleBoardResponse getAllBoardsOne() {
		logger.info("Inside getAllBoards() method...");
		int p=1,s=10;
		List<Board> allBoards = repo.pagebyPage(p,s);
		multipleBoardResponse extResp=null;
		if(allBoards.size()==s){
		 extResp = new multipleBoardResponse(p,true,true, allBoards);
		}else
		{
		 extResp = new multipleBoardResponse(p,false,true, allBoards);
		}
		
		return extResp;
	}
	
	@RequestMapping(value="/details/all", method=RequestMethod.GET)
	@ResponseBody
	public multipleBoardResponse getAllBoardsall() {
		logger.info("Inside getAllBoards() method...");

		List<Board> allBoards = repo.pagebyPage(9999,9999);
		multipleBoardResponse extResp=null;
		 extResp = new multipleBoardResponse(allBoards.size(),true,true, allBoards);
		
		return extResp;
	}
	
	@RequestMapping(value="/details/{page}/{size}", method=RequestMethod.GET)
	@ResponseBody
	public multipleBoardResponse getAllBoards(@PathVariable("page") String page,@PathVariable("size") String size) {
		logger.info("Inside getAllBoards() method...");
		int p=1,s=10;
		if(page==null) {
		 p=1;
		}else{
		 p= Integer.parseInt(page);
		}
		
		if(size==null){
			s=10;
		}else{
			s=Integer.parseInt(size);
		}
		List<Board> allBoards = repo.pagebyPage(p,s);
		multipleBoardResponse extResp=null;
		if(allBoards.size()==s){
		 extResp = new multipleBoardResponse(p,true,true, allBoards);
		}else
		{
		 extResp = new multipleBoardResponse(p,false,true, allBoards);
		}
		
		return extResp;
	}
	
}
