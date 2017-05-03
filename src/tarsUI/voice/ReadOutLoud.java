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
 *
 * @author Anwar
 */
public class ReadOutLoud {
     public void readMessage(String message){
         voce.SpeechInterface.synthesize(message);
     }

    public void initVoiceRecognition(String name){
         voce.SpeechInterface.init("../../../lib", true, false, "", "");
         readMessage("Hello "+name);
    }
    public void closeVoiceRecognition(){
        voce.SpeechInterface.destroy();
    }
}
