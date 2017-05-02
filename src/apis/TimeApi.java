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

/**
 *
 * @author Anwar
 */
public class TimeApi extends LocalApi{
    private String city;
    public TimeApi(){
        super.addIntent("time of", "remotetime", "It's ");
        super.addIntent("what time is in", "remotetime", "It's ");
        super.addIntent("local time of", "remotetime", "It's ");

        super.addIntent("local time", "localtime", "It's ");
        super.addIntent("what's time now", "localtime", "It's ");
        super.addIntent("time now", "localtime", "It's ");
        super.addIntent("time", "localtime", "It's ");
    }
    public void setCity(String city){
        this.city=city;
    }
    @Override
    public String serve(String append) {
        return "Request time of city";
    }    
}
