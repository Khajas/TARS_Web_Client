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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The following class is to open the default browser on a user system.
 * @author Anwar
 */
public class Browser extends LocalApi{
    private String url="https://www.google.com";
    public Browser(){
        super.addIntent("open browser", "browserservice", "Opening browser");
        super.addIntent("open mozilla", "browserservice", "Opening browser");
        super.addIntent("let's browse", "browserservice", "opening browser");
        super.addIntent("browser", "browserservice", "Opening browser");
        super.addIntent("open mozilla firefox", "browserservice", "Opening browser");
    }
    /**
     * Constructor for browser class
     * @param url 
     */
    public Browser(String url){
        this();
        this.url=url;
    }
    /**
     * Opens browser
     * @return 
     */
    public String openBrowser(){
        String responseMessage="okay, ";
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                responseMessage=e.getMessage();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
               responseMessage=e.getMessage();
            }
        }
        return responseMessage;
    }
    /**
     * Serves the purpose(or desired action) of API
     * @param append
     * @return response
     */
    @Override
    public String serve(String append) {
        return this.openBrowser()+append;
    }
}
/////////////////////// END OF SOURCE FILE  /////////////////