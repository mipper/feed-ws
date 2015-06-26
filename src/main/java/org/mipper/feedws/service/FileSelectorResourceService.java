package org.mipper.feedws.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;


public class FileSelectorResourceService
    implements
        ResourceService
{

    public FileSelectorResourceService ( FileSelector selector,
                                         MediaType type )
    {
        _selector = selector;
        _type = type;
    }


    @Override
    public ResourceRecord getResource ()
        throws
            IOException
    {
        final File selected = _selector.select ();
        if ( null == selected )
        {
            _log.info ( "Could not find matching file using {}", _selector );
        }
        return new ResourceRecord ( new FileSystemResource ( selected ),
                                    _type );
    }


    private final static Logger _log = LoggerFactory.getLogger ( FileSelectorResourceService.class );
    private final FileSelector _selector;
    private final MediaType _type;

}
