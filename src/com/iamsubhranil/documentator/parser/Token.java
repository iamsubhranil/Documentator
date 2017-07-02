package com.iamsubhranil.documentator.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

/**
 * Author : Subhranil Mukherjee
 * Date : 2/7/17
 * Time : 1:47 AM
 * Project : Documentator
 */
public class Token {

    private final ArrayList<String> values;
    private final String name;
    private final boolean isOptional;
    private final boolean isSpecific;
    private final String origValue;
    private String possibleValues = "";

    public Token(String token, Properties p) {
        origValue = token;
        isOptional = token.startsWith("_");
        if (isOptional())
            token = token.substring(1);
        isSpecific = token.charAt(0) == '"';
        if (isSpecific) {
            if (token.charAt(token.length() - 1) != '"')
                throw new IllegalArgumentException("Illegal token : " + token);
        }
        name = token.replace("\"", "");
        values = new ArrayList<>(1);
        if (isSpecific) {
            values.add(name);
            possibleValues = name;
        } else {
            if (name.equals("return_type") || name.equals("method_name"))
                possibleValues = "any_word";
            else
                possibleValues = p.getProperty(name);
            if (possibleValues == null || possibleValues.isEmpty())
                throw new IllegalArgumentException("No definition of token : " + name + "[" + token + "]");
            Collections.addAll(values, possibleValues.split("\\|"));
        }
    }

    public boolean isOptional() {
        return isOptional;
    }

    public boolean isSpecific() {
        return isSpecific;
    }

    public boolean isValid(String text) {
        if (text.length() == 1 && !Character.isLetter(text.charAt(0)))
            return values.get(0).equals(text);
        else
            return values.get(0).equals("any_word") || values.contains(text);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Token : \"" + name + "\"[defined_as=" + origValue + ", isSpecific=" + isSpecific + ", isOptional=" + isOptional + ", values=" + possibleValues + "]";
    }
}