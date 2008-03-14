package org.apache.maven.surefire.its;


import junit.framework.TestCase;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

import java.io.File;

/**
 * Test JUnit test that contains inner classes
 * 
 * @author <a href="mailto:dfabulich@apache.org">Dan Fabulich</a>
 * 
 */
public class Surefire44InnerClassTest
    extends TestCase
{
    public void testInnerClass ()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/junit-innerClass" );

        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        verifier.executeGoal( "test" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();
        
        HelperAssertions.assertTestSuiteResults( 1, 0, 0, 0, testDir );        
    }
}
