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

/**
 *
 * @author Anwar
 */
public class QueryTypeDetector {
    private final String query;
    QueryTypeDetector(String query){
        this.query=query;
    }
    public boolean isLocal(){
        if(localTimeRequest())
            return true;
        else if(browserRequest())
            return true;
        return false;
    }
    // Let's list all the local methods here
    public boolean localTimeRequest(){
        return query.contains("time now");
    }
    public boolean browserRequest(){
        return query.contains("open browser");
    }
}
