package com.techgene.spring.mvc.controller;

import java.util.Map;

import org.bson.BSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;










import com.techgene.spring.mvc.model.Word;
import com.techgene.spring.mvc.service.WordService;

@Controller
public class DocumentController {
	
	protected WordService wordService = new WordService();

	

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String processMongo(@RequestParam("name") String name, Model model) {
		
		Word word=new Word();
		
		word.setWord(name);
		
 wordService.getDocument(word);
		
		
		
		/*word.setBsonkey((String) bs_res.get("word"));
		word.setBson_count((Integer) bs_res.get("count"));*/
	
System.out.println("*************************************************");
		/*System.out.println("key :"+ bs_res);
		
		System.out.println(bs_res);*/
		model.addAttribute("word", word.getWord());
		return "document";
	}

}
