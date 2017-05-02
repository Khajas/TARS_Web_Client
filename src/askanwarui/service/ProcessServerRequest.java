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
package askanwarui.service;

import apis.LocalApi;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Anwar
 */
public class ProcessServerRequest extends LocalApi{
    private boolean debugMode;
    private String query;
    public ProcessServerRequest(){
        // Local Weather information
        super.addIntent("What's today's forecast ", "weatherinfo", "It's seems to be ");
        super.addIntent("What's today's weather ", "weatherinfo", "It's seems to be ");
        super.addIntent("Today's forecast ", "weatherinfo", "It's seems to be ");
        super.addIntent("Weather info ", "weatherinfo", "It's seems to be ");
        super.addIntent("weather ", "weatherinfo", "It's ");
        // Remote Weather information
        super.addIntent("What's today's forecast in ", "weatherinforemote", "It's ");
        super.addIntent("What's today's weather in ", "weatherinforemote", "It's ");
        super.addIntent("What's today's forecast of ", "weatherinforemote", "It's ");
        super.addIntent("What's today's weather of ", "weatherinforemote", "It's ");
        super.addIntent("How is weather in ", "weatherinforemote", "It's ");
        super.addIntent("weather in ", "weatherinforemote", "It's ");
        super.addIntent("weather of ", "weatherinforemote", "It's ");
        super.addIntent("forecast of ", "weatherinforemote", "It's ");
        // Wiki Api
        super.addIntent("What's ", "wikiapi", "");
        super.addIntent("What's a ", "wikiapi", "");
        super.addIntent("What is ", "wikiapi", "");
        super.addIntent("What is a ", "wikiapi", "");
        super.addIntent("Who is ", "wikiapi", "");
        // Location Service
        super.addIntent("what's my location", "locationservice","You're location in ");
        super.addIntent("my location", "locationservice","It seems you're in ");
        super.addIntent("where am I now", "locationservice","I guess you're at ");
        super.addIntent("where am I located", "locationservice","I guess you're at ");
        // Local news
        super.addIntent("what's local news ", "localnews", "Local Headlines: ");
        super.addIntent("what's today's news ", "localnews", "Local Headlines: ");
        super.addIntent("what's local headlines ", "localnews", "Local Headlines: ");
        super.addIntent("what's today's headlines ", "localnews", "Local Headlines: ");
        super.addIntent("headlines ", "localnews", "Local Headlines: ");
        super.addIntent("news ", "localnews", "Local Headlines: ");
        // Indian News
        super.addIntent("what's indian news", "indiannews", "Indian Headlines: ");
        super.addIntent("what's news of india", "indiannews", "Indian Headlines: ");
        super.addIntent("what's today's news of india", "indiannews", "Indian Headlines: ");
        super.addIntent("what's headlines at indian ", "indiannews", "Indian Headlines: ");
        super.addIntent("what's today's indian headlines ", "indiannews", "Indian Headlines: ");
        super.addIntent("headlines of india", "indiannews", "Indian Headlines: ");
        super.addIntent("indian headlines", "indiannews", "Indian Headlines: ");
        super.addIntent("news of india", "indiannews", "Indian Headlines: ");
        // Technical news
        super.addIntent("what's technical news ", "technicalnews", "Technical Headlines: ");
        super.addIntent("what's today's technical news ", "technicalnews", "Technical Headlines: ");
        super.addIntent("what's technical headlines ", "technicalnews", "Technical Headlines: ");
        super.addIntent("what's today's technical headlines ", "technicalnews", "Technical Headlines: ");
        super.addIntent("headlines technical ", "technicalnews", "Technical Headlines: ");
        super.addIntent("news technical ", "technicalnews", "Technical Headlines: ");
        // Sports news
        super.addIntent("what's sports news ", "sportsnews", "Sports Headlines: ");
        super.addIntent("what's today's sports news ", "sportsnews", "Sports Headlines: ");
        super.addIntent("what's sports headlines ", "sportsnews", "Sports Headlines: ");
        super.addIntent("what's today's sports headlines ", "sportsnews", "Sports Headlines: ");
        super.addIntent("headlines sports ", "sportsnews", "Sports Headlines: ");
        super.addIntent("news sports ", "sportsnews", "Sports Headlines: ");
        
    }
    public ProcessServerRequest(String query){
        this();
        this.query=query.replaceAll(" ","+");
    }
    public String processQuery() throws MalformedURLException, IOException{
        String message="";
        String httpQuery="http://localhost:8080/TARS/webapi/who/"+query;
        if(debugMode) System.out.println("Http query: "+httpQuery);
        URL url;
        try{
            url = new URL(httpQuery); 
        }catch(MalformedURLException e){
            throw new MalformedURLException("Invalid query!");
        }
        HttpURLConnection connection = connectServer(url); 
        String responseCode=connection.getResponseMessage();
        if(debugMode) System.out.println("Respose code: "+responseCode);
        if(responseCode.equals("OK")){
            message=this.readResponse(connection);
        }
        connection.disconnect();
        return message;
    }        
     
    private String readResponse(HttpURLConnection connection ) throws IOException{
        String message="";
        InputStream response=(InputStream)connection.getContent();
        int numBytes=response.available();
        for(int i=0;i<numBytes;++i)
           message+=((char)response.read());
        return message;
    }
     
    private HttpURLConnection connectServer(URL url){
         HttpURLConnection connection;
         try{
             connection = (HttpURLConnection) url.openConnection(); 
             connection.setDoOutput(true); 
             connection.setInstanceFollowRedirects(false); 
             connection.setRequestMethod("GET");
         }catch(IOException e){
             throw new RuntimeException(e);
         }
         return connection;
     }

    @Override
    public String serve(String append) {
        try {
            return this.processQuery();
        } catch (IOException ex) {
            return "Server Error";
        }
    }
}
