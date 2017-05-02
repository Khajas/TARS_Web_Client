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
package askanwarui.voice;

/**
 *
 * @author Anwar
 */
public class RecognizeQuery {
    public void hearMe(){
        voce.SpeechInterface.init("test", false, true, "test/gram", "digits");
        System.out.println("This is a speech recognition test. " 
                + "Speak digits from 0-9 into the microphone. " 
                + "Speak 'quit' to quit.");

        boolean quit = false;
        while (!quit)
        {
                // Normally, applications would do application-specific things 
                // here.  For this sample, we'll just sleep for a little bit.
                try
                {
                        Thread.sleep(200);
                }
                catch (InterruptedException e)
                {
                }

                while (voce.SpeechInterface.getRecognizerQueueSize() > 0)
                {
                        String s = voce.SpeechInterface.popRecognizedString();

                        // Check if the string contains 'quit'.
                        if (-1 != s.indexOf("quit"))
                        {
                                quit = true;
                        }

                        System.out.println("You said: " + s);
                        //voce.SpeechInterface.synthesize(s);
                }
        }
        voce.SpeechInterface.destroy();
    }
}
