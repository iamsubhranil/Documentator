package com.iamsubhranil.documentator.parser;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Author : Subhranil Mukherjee
 * Date : 2/7/17
 * Time : 1:46 AM
 * Project : Documentator
 */
public class Signature {

    private final ArrayList<Token> tokens;
    private boolean validated = false;

    public Signature() {
        tokens = new ArrayList<>();
    }

    public void addToken(Token t) {
        tokens.add(t);
    }

    public void validate() {
        if (!validated) {
            boolean[] checks = {false, false, false, false, false, false};
            tokens.forEach(token -> {
                if (token.getName().equals("method_name"))
                    checks[0] = true;
                else if (token.getName().equals("param_start")) {
                    checks[1] = true;
                    if (token.isOptional())
                        checks[2] = true;
                } else if (token.getName().equals("param_end")) {
                    checks[3] = true;
                    if (token.isOptional())
                        checks[4] = true;
                }
            });
            if (!checks[0] || (checks[1] && !checks[3]) || (checks[2] && !checks[4]))
                throw new IllegalArgumentException("Given signature is invalid!");
            validated = true;
        }
    }

    public boolean isSignature(String text) {
        validate();
        String[] parts = text.split(" ");
        if (parts.length == 0)
            return false;
        int i = 0;
        int k;
        for (k = 0; k < parts.length; k++) {
            String part = parts[k];
            ListIterator<Token> t1 = tokens.listIterator(i);
            int j = i;
            while (t1.hasNext()) {
                Token t = t1.next();
                if (t.isValid(part)) {
                    i = j + 1;
                    if (t.getName().equals("param_start")) {
                        Token paramEnd = t1.next();
                        String params = "";
                        k++;
                        part = parts[k];
                        while (!paramEnd.isValid(part)) {
                            k++;
                            if (k == parts.length)
                                return false;
                            params += " " + part;
                            part = parts[k];
                        }
                        //    if(params.length()>0)
                        //      System.out.println("Params : "+params);
                    }
                    break;
                } else if (!t.isOptional()) {
                    //  System.out.println("Non-optional value not found : "+t+" in signature "+text+", part : "+part);
                    return false;
                }
                j++;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Signature[parts=" + tokens.size() + "]");
        tokens.forEach(token -> sb.append("\n").append(token.toString()));
        return sb.toString();
    }

}
