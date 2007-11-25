package org.apache.maven.surefire.its;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.integrationtests.AbstractMavenIntegrationTestCase;
import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

/**
 * Test failIfNoTests
 * 
 * @author <a href="mailto:dfabulich@apache.org">Dan Fabulich</a>
 * 
 */
public class TestFailIfNoTests
    extends AbstractMavenIntegrationTestCase
{
    public void testFailIfNoTests ()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/default-configuration-noTests" );

        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        List goals = new ArrayList();
        goals.add( "test" );
        goals.add( "-DfailIfNoTests" );
        verifier.executeGoals( goals );
        verifier.resetStreams();
        
        try {
            verifier.verifyErrorFreeLog();
            fail ( "Build didn't fail, but it should" );
        } catch ( VerificationException e ) {
            // as expected
        }
                
    }
    
    public void testDontFailIfNoTests()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/default-configuration-noTests" );

        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        verifier.executeGoal( "test" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();

        File reportsDir = new File( testDir, "target/surefire-reports" );
        assertFalse ( "Unexpected reports directory", reportsDir.exists() );
    }

}