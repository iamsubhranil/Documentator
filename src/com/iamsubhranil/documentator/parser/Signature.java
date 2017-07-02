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

    public Signature() {
        tokens = new ArrayList<>();
    }

    public void addToken(Token t) {
        tokens.add(t);
    }

    public boolean isSignature(String text) {
        String[] parts = text.split(" ");
        if (parts.length == 0)
            return false;
        int i = 0;
        for (String part : parts) {
            ListIterator<Token> t1 = tokens.listIterator(i);
            int j = i;
            while (t1.hasNext()) {
                Token t = t1.next();
                if (t.isValid(part)) {
                    i = j + 1;
                    break;
                } else if (!t.isOptional()) {
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
