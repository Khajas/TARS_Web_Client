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
package apis;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * To request service from TARS server
 * @author Anwar
 */
public class ProcessServerRequest extends LocalApi{
    private boolean debugMode;
    private final String query;
    public ProcessServerRequest(String query){
        this.query=query.replaceAll(" ","+");
    }
    /**
     * Process a user request and returns server response
     * @return server_response
     * @throws MalformedURLException
     * @throws IOException 
     */
    public String processQuery() throws MalformedURLException, IOException{
        String server_response="";
        String httpQuery="http://localhost:8080/TARS/webapi/query/"+query;
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
            server_response=this.readResponse(connection);
        }
        connection.disconnect();
        return server_response;
    }        
    /**
     * Reads response from the given HttpURLConnection
     * @param connection
     * @return server_response
     * @throws IOException 
     */
    private String readResponse(HttpURLConnection connection ) throws IOException{
        String server_response="";
        InputStream response=(InputStream)connection.getContent();
        int numBytes=response.available();
        for(int i=0;i<numBytes;++i)
           server_response+=((char)response.read());
        return server_response;
    }
    /**
     * Makes connection to server for the given URL
     * @param url
     * @return HttpURLConnection
     */
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
    /**
     * Serves the purpose(or desired action) of API
     * @param append
     * @return response
     */
    @Override
    public String serve(String append) {
        try {
            return this.processQuery();
        } catch (IOException ex) {
            return "Server Error";
        }
    }
}
//////////////////////  END OF SOURCE FILE  ////////////////////////////////////
