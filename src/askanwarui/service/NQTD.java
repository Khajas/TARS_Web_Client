package askanwarui.service;

import apis.ProcessServerRequest;
import apis.Browser;
import NQTD.Intents;
import apis.LocalApi;
import NQTD.NamedEntityRecognition;
import java.io.IOException;

public class NQTD {
    private final String query;
    private final Browser ba;
    private final ProcessServerRequest pa;
    public NQTD(String query){
        this.query=query;
        ba=new Browser();
        pa=new ProcessServerRequest(query);
    }

    public void printCommands(){
 //       LocalApi.print_commands();
    }

    public String detectServiceType(){
        NamedEntityRecognition ner=new NamedEntityRecognition(query);
        String query_words=ner.getEnglishEntity();
        Intents i=LocalApi.searchIntent(query_words);
        if(i==null) return null;
        if(i.getCategory().equals("browserservice")){
            return ba.openBrowser();
        }
        else{
            try {
                return pa.processQuery();
            } catch (IOException ex) {
                return "Server Error";
            }
        }
    }
}
