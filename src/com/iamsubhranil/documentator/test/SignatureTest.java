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
    public void testJava() {
        try {
            Signature signature = SignatureParser.loadSignature(Signature.class.getResource("signatures/java.signature").getFile());
            System.out.println("Testing java..");
            assertEquals("\"void main ( )\"", true, signature.isSignature("void main ( )"));
            assertEquals("\"main ( )\"", false, signature.isSignature("main ( )"));
            assertEquals("\"main()\"", true, signature.isSignature("main()"));
            assertEquals("\"public ClassAbc main ( String [] args )", true, signature.isSignature("public ClassAbc main ( String [] args )"));
            assertEquals("\"public public classAbc main ( )\"", false, signature.isSignature("public public classAbc main ( )"));
            assertEquals("\"public ClassAbc main ( String [] args \"", false, signature.isSignature("public ClassAbc main ( String [] args "));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testC() {
        try {
            System.out.println("Testing C..");
            Signature signature = SignatureParser.loadSignature(Signature.class.getResource("signatures/c.signature").getFile());
            assertEquals("int main ( char * argv, int argc )", true, signature.isSignature("int main ( char * argv, int argc )"));
            assertEquals("int main ( )", true, signature.isSignature("int main ( )"));
            assertEquals("int j = 5;", false, signature.isSignature("int j = 5;"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SignatureTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

}