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

/**
 * The following class is store an intent(a command, the category it belongs to
 * and the response that should be appended to the result)
 * @author Anwar
 */
public class Intents {
    private final String command;
    private final String category;
    private final String response;
    private static int id;
    /**
     * Constructor for Intents class
     * @param command
     * @param category
     * @param response 
     */
    public Intents(String command, String category, String response) {
            super();
            this.command = command;
            this.category = category;
            this.response=response;
    }
    /**
     * Copy constructor
     * @param intent
     */
    public Intents(Intents intent) {
            this(intent.getCommand(), intent.getCategory(), intent.getResponse());
    }
    /**
     * Returns the command of an intent
     * @return command 
     */
    public String getCommand() {
            return command;
    }
    /**
     * Returns the category of an intent
     * @return category
     */
    public String getCategory() {
            return category;
    }
    /**
     * Returns the response of an intent
     * @return response
     */
    public String getResponse(){
            return this.response;
    }
    /**
     * Returns the id of an intent
     * @return id
     */
    public static int getId() {
            return id;
    }
    /**
     * Sets the id of an intent
     * @param id
     */
    public static void setId(int id) {
            Intents.id = id;
    }
}
/////////////////////// END OF SOURCE FILE  /////////////////