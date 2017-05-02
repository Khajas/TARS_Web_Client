package NQTD;

import apis.LocalApi;

public final class NamedEntityRecognition {
	private final String query;
	private String namedEntity="";
	private String englishEntity="";
	public NamedEntityRecognition(String query){
		this.query=query;
		this.displayset();
		this.processedQuery();
//		System.out.println("Named entity: "+this.namedEntity );
//		System.out.println("English entity: "+this.englishEntity );
	}
	public String getNamedEntity(){
		return this.namedEntity;
	}
	public String getEnglishEntity(){
		return this.englishEntity;
	}
	public void processedQuery(){
		String[] allwords = query.split(" ");
		for(int i=0;i<allwords.length;++i){
			String word=allwords[i];
			if(LocalApi.intent_words.contains(word.toLowerCase()))
				englishEntity+=(word+" ");
			else namedEntity+=(word+" ");
		}
		
	}
	public void displayset(){
//		System.out.println("Set size: "+LocalApi.intent_words.size());
//		for(String s: LocalApi.intent_words)
//			System.out.println("Word: "+s);
	}
}
