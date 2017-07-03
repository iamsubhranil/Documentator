package com.iamsubhranil.documentator.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Author : Subhranil Mukherjee
 * Date : 2/7/17
 * Time : 1:46 AM
 * Project : Documentator
 */
public class SignatureParser {

    private static final ArrayList<Signature> signatures = new ArrayList<>();

    public static Signature loadSignature(String loc) throws IOException {
        return loadSignature(new File(loc));
    }

    public static Signature loadSignature(File p) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(p));
        String signatureString = properties.getProperty("signature");
        if (signatureString.equals(""))
            throw new IllegalArgumentException("Given file does not contain a method signature");
        else {
            Signature signature = new Signature();
            String[] tokens = signatureString.split(" ");
            for (String token : tokens) {
                if (token.equals("params"))
                    continue;
                Token t = new Token(token, properties);
                signature.addToken(t);
            }
            signature.validate();
            signatures.add(signature);
            return signature;
        }
    }

}
