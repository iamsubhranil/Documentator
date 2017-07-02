package com.iamsubhranil.documentator.test;

import com.iamsubhranil.documentator.parser.Signature;
import com.iamsubhranil.documentator.parser.SignatureParser;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Author : Subhranil Mukherjee
 * Date : 3/7/17
 * Time : 1:46 AM
 * Project : Documentator
 */
public class SignatureTest {

    @Test
    public void testJava() throws IOException {
        Signature signature = SignatureParser.loadSignature(Signature.class.getResource("signatures/java.signature").getFile());
        System.out.println("Testing java..");
        assertEquals("\"void main ( )\"", true, signature.isSignature("void main ( )"));
        assertEquals("\"main ( )\"", false, signature.isSignature("main ( )"));
        assertEquals("\"main()\"", true, signature.isSignature("main()"));
        assertEquals("\"public ClassAbc main ( )", true, signature.isSignature("public ClassAbc main ( )"));
        assertEquals("\"public public classAbc main ( )\"", false, signature.isSignature("public public classAbc main ( )"));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SignatureTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

}