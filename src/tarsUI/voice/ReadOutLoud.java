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
package tarsUI.voice;

/**
 * Reads an user input loud
 * @author Anwar
 */
public class ReadOutLoud {
    /**
     * Read a message out loud
     * @param message 
     */
    public void readMessage(String message){
         voce.SpeechInterface.synthesize(message);
     }
    /**
     * Initialize the speechInterface and set the user name
     * @param name 
     */
    public void initVoiceRecognition(String name){
         voce.SpeechInterface.init("../../../lib", true, false, "", "");
         readMessage("Hello "+name);
    }
    /**
     * Shuts the speech interface
     */
    public void closeVoiceRecognition(){
        voce.SpeechInterface.destroy();
    }
}
