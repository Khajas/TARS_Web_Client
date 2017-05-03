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

import tarsUI.voice.ReadOutLoud;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
/**
 * Main class for TARS_UI client user interface
 * @author Anwar
 */
public class TARS_UI {
    private static boolean readLoud=true;
    public static PrintWriter stdout = new PrintWriter(
        new OutputStreamWriter(System.out, StandardCharsets.UTF_8),
    true);
    public static void main(String[] argv) throws IOException{
        Scanner sc=new Scanner(System.in);
        ReadOutLoud rol=new ReadOutLoud();
        if(readLoud)
            rol.initVoiceRecognition("anwar");        
        System.out.print("What you'd like to know?: ");
        String userQuery=sc.nextLine();
        while(!userQuery.equals("quit")){
//            System.out.println("Looking for: "+userQuery);
            NQTD qt=new NQTD(userQuery);
            String response=qt.detectServiceType();
            stdout.println(response);
            rol.readMessage(response);
            System.out.print("Want to know more?: ");
            userQuery=sc.nextLine();
        }
        rol.closeVoiceRecognition();
    }
}
//////////////////////  END OF SOURCE FILE  ////////////////////////////////////