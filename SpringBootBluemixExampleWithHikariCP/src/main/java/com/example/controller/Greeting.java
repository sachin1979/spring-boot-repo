package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dataobject.PrintDO;
import com.example.db.DBService;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

@Controller
public class Greeting {
	
	private static final String WATSON_LANG_TRANSLATOR_USERNAME = "91e22795-dcdb-491d-aea8-9f9f2479f384";
	private static final String WATSON_LANG_TRANSLATOR_PASSWORD = "xv3KLBhY54bv";

	@Autowired
	private DBService objDBService;

	@RequestMapping("/greeting")
	@ResponseBody
	public PrintDO greeting() {
		PrintDO obj = objDBService.insertAndDisplaySampleData();
		return obj;
	}

	@RequestMapping(value = "/salesLanguageTranslator", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateServicesFeatures(@RequestBody Map<String, Object> paramValues,
			HttpServletRequest request) {
		Map<String, Object> returnValues = new HashMap<String, Object>();

		String fromLanguage = (String) paramValues.get("fromLanguage");
		String toLanguage = (String) paramValues.get("toLanguage");
		String textToTranslate = (String) paramValues.get("text");

		//returnValues.put("outputText", textToTranslate);
		LanguageTranslator service = new LanguageTranslator();
		service.setUsernameAndPassword(WATSON_LANG_TRANSLATOR_USERNAME, WATSON_LANG_TRANSLATOR_PASSWORD);
		TranslationResult translationResult = service
				.translate(textToTranslate, Language.valueOf(fromLanguage), Language.valueOf(toLanguage)).execute();

		returnValues.put("outputText", translationResult.getFirstTranslation());

		return new ResponseEntity<Map<String, Object>>(returnValues, HttpStatus.OK);

	}
}
