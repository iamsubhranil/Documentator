package com.iamsubhranil.documentator;

import com.iamsubhranil.documentator.parser.Signature;
import com.iamsubhranil.documentator.parser.SignatureParser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Signature javaSig = SignatureParser.loadSignature(Main.class.getResource("signatures/java.signature").getFile());
        System.out.println("Test : " + javaSig.isSignature("Resurrect main ( )"));
        Signature cSig = SignatureParser.loadSignature(Main.class.getResource("signatures/c.signature").getFile());
        System.out.println("Test C : " + cSig.isSignature("static int main ( )"));
    }
}
