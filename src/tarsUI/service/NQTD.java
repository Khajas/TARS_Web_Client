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
package tarsUI.service;

import apis.ProcessServerRequest;
import apis.Browser;
import NQTD.Intents;
import apis.LocalApi;
import NQTD.NamedEntityRecognition;
import java.io.IOException;
/**
 * NQTD(New Query Type Detector)
 * Detects the type of a query(decides the skills that a user query belongs to)
 * @author Anwar
 */
public class NQTD {
    private final String query;
    private final Browser ba;
    private final ProcessServerRequest pa;
    public NQTD(String query){
        this.query=query;
        ba=new Browser();
        pa=new ProcessServerRequest(query);
    }
    /**
     * prints all the valid commands
     */
    public void printCommands(){
        LocalApi.print_commands();
    }
    /**
     * Detects the type of service and returns the response after processing request
     * @return response
     */
    public String detectServiceType(){
        NamedEntityRecognition ner=new NamedEntityRecognition(query);
        String query_words=ner.getEnglishEntity();
        Intents i=LocalApi.searchIntent(query_words);
        if(i==null){
            try {
                return pa.processQuery();
            } catch (IOException ex) {
                return "Server Error";
            }
        }
        else if(i.getCategory().equals("browserservice")){
            return ba.openBrowser();
        }
        else{
            return null;
        }
    }
}
//////////////////////  END OF SOURCE FILE  ////////////////////////////////////
