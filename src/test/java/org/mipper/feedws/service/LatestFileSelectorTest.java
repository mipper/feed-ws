package org.mipper.feedws.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith ( MockitoJUnitRunner.class )
public class LatestFileSelectorTest
{

    @Before
    public void setUp ()
        throws
            Exception
    {
        final File f1 = _folder.newFile ( "1.html" );
        final File f2 = _folder.newFile ( "2.html" );
        sleep ();
        final File f3 = _folder.newFile ( "3.html" );
        _files = new File[] { f2, f3, f1 };

    }


    @Test
    public void testSelect ()
        throws
            IOException
    {
        Mockito.when ( _root.listFiles ( _filter ) ).thenReturn ( _files );

        assertEquals ( "3.html",
                       new LatestFileSelector ( _root, _filter ).select ()
                                                                .getName () );
    }


    @Test
    public void testSelectEmpty ()
        throws
            IOException
    {
        Mockito.when ( _root.listFiles ( _filter ) ).thenReturn ( null );
        assertNull ( new LatestFileSelector ( _root, _filter ).select () );

        Mockito.when ( _root.listFiles ( _filter ) ).thenReturn ( new File[] {} );
        assertNull ( new LatestFileSelector ( _root, _filter ).select () );
    }


    private static void sleep ()
        throws
            InterruptedException
    {
        Thread.sleep ( 1000 );
    }


    @Rule
    public final TemporaryFolder _folder = new TemporaryFolder ();

    private File[] _files;
    @Mock
    private File _root;
    @Mock
    private FilenameFilter _filter;

}
