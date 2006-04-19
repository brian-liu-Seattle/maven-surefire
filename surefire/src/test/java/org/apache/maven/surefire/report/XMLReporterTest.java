package org.apache.maven.surefire.report;

/*
 * Copyright 2001-2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.codehaus.plexus.util.xml.Xpp3Dom;

public class XMLReporterTest
    extends TestCase
{

    private XMLReporter reporter;

    private ReportEntry reportEntry;

    private String message;

    protected void setUp()
        throws Exception
    {
        super.setUp();
        reporter = new XMLReporter();
        message = "junit.framework.AssertionFailedError";
        reportEntry = new ReportEntry( this, "XMLReporterTest", message, new AssertionFailedError() );
        reporter.setTestCase( new Xpp3Dom( "" ) );
    }

    /*
     * Test method for 'org.codehaus.surefire.report.XMLReporter.testError(ReportEntry, String, String)'
     */
    public void testTestError()
    {
        reporter.testError( reportEntry, "", "" );
        assertResult( reporter, message );
    }

    /*
     * Test method for 'org.codehaus.surefire.report.XMLReporter.testFailed(ReportEntry, String, String)'
     */
    public void testTestFailed()
    {
        reporter.testError( reportEntry, "", "" );
        assertResult( reporter, message );
    }
    
    public void testReplaceAll()
    {
        String s, from, to;
        s = "";
        from = "";
        to = "";
        String result = XMLReporter.replaceAll( s, from, to );
        assertEquals( "", result );

        s = "xxfromyytozz";
        from = "from";
        to = "to";
        result = XMLReporter.replaceAll( s, from, to );
        assertEquals( "xxtoyytozz", result );
    }

    private void assertResult( XMLReporter reporter, String message )
    {
        Xpp3Dom result = reporter.getTestCase();
        Xpp3Dom child = result.getChild( "error" );
        assertEquals( message, child.getAttribute( "type" ) );
    }

}