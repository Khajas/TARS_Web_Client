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

import NQTD.Intents;
import NQTD.StringSimilarity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 *
 * @author Anwar
 */
public abstract class LocalApi {
    private static final ArrayList<Intents> INTENTS=new ArrayList<>();
    public static Set<String> intent_words=new HashSet<String>();

    public boolean addIntent(String command, String category, String response){
        for(Intents i: INTENTS){
            if(i.getCommand().equals(command))
                return false; // Similar command already exists
        }
        INTENTS.add(new Intents(command.toLowerCase(),category.toLowerCase(), response));
        String[] allwords=command.toLowerCase().split(" ");
        for(String s: allwords){
                intent_words.add(s);
        }
        return true;
    }
    public abstract String serve(String append);

    public static void print_commands() {
        System.out.println("Total intents: "+INTENTS.size());
        for(Intents i: INTENTS)
                System.out.println(i.getCommand());
    }

    public static Intents searchIntent(String command){
        Map<Intents, Double> intentScore=new HashMap<>();
        double highscore=-10000;
//        System.out.println("\tLooking for: "+command+" in total intents: "+INTENTS.size());
        for(Intents i: INTENTS){
                double score=StringSimilarity.similarity(i.getCommand().toLowerCase(),
                                command.toLowerCase());
  //              System.out.println("Intent: "+i.getCommand()+" score: "+score);
                if(score>highscore) highscore=score;
                intentScore.put(i, score);
        }
        for(Entry<Intents,Double> e: intentScore.entrySet()){
                if(e.getValue()==highscore)
                        return e.getKey();
        }
        return null;
    }
}
