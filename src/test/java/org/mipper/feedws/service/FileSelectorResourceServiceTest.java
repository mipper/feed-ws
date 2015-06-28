package org.mipper.feedws.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;


@RunWith ( MockitoJUnitRunner.class )
public class FileSelectorResourceServiceTest
{

    @Mock
    private FileSelector selector;
    @Mock
    private MediaType type;
    @InjectMocks
    private FileSelectorResourceService fileSelectorResourceService;


    @Test
    public void testGetResourceNone ()
        throws
            IOException
    {
        Mockito.when ( selector.select () ).thenReturn ( null );

        assertNull ( fileSelectorResourceService.getResource ().getResource () );
    }


    @Test
    public void testGetResource ()
        throws
            IOException
    {
        Mockito.when ( selector.select () ).thenReturn ( new File ( "/hello/world.txt" ) );

        final ResourceRecord res = fileSelectorResourceService.getResource ();
        assertEquals ( "/hello/world.txt",
                       res.getResource ().getFile ().getAbsolutePath () );
        assertEquals ( type, res.getMediaType () );
    }

}
