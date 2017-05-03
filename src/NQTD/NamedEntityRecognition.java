/*
 * Copyright (c) 2017 Anwar.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Anwar - initial API and implementation and/or initial documentation
 */
package NQTD;

import apis.LocalApi;
/**
 * The following class is to identify words that doesn't belong to any of the 
 * expected (or defined) commands so far.
 * It takes a user command and separates them into 
 * "Query words(words belonging to intents)" and "Named words(words that doesn't
 * belong a pre-defined command)".
 * @author Anwar
 */
public final class NamedEntityRecognition {
	private final String query;
	private String namedEntity="";
	private String englishEntity="";
        /**
         * Constructor for NamedEntityRecognition
         * @param query 
         */
	public NamedEntityRecognition(String query){
		this.query=query;
//		this.displaySet();
		this.processQuery();
//		System.out.println("Named entity: "+this.namedEntity );
//		System.out.println("English entity: "+this.englishEntity );
	}
        /**
         * Returns the named entity for a given query
         * @return String namedEntity
         */
	public String getNamedEntity(){
		return this.namedEntity;
	}
        /**
         * Returns valid words found in intents for a given query
         * @return String englishEntity/languageEntity
         */
	public String getEnglishEntity(){
		return this.englishEntity;
	}
        /**
         * Processes a query and separates words into named(unknown) and known(found
         * in intents) words.
         */
	public void processQuery(){
		String[] allwords = query.split(" ");
		for(int i=0;i<allwords.length;++i){
			String word=allwords[i];
			if(LocalApi.intent_words.contains(word.toLowerCase()))
				englishEntity+=(word+" ");
			else namedEntity+=(word+" ");
		}
		
	}
        /**
         * Displays the size of words in intents
         */
	public void displaySet(){
		System.out.println("Set size: "+LocalApi.intent_words.size());
		for(String s: LocalApi.intent_words)
			System.out.println("Word: "+s);
	}
}
/////////////////////// END OF SOURCE FILE  /////////////////