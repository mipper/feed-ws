package org.mipper.feedws.service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LatestFileSelector
    implements
        FileSelector
{

    public LatestFileSelector ( File root, FilenameFilter filter )
    {
        _root = root;
        _filter = filter;
        _log.info ( "Root: {}, Filter: {}", _root, _filter );
    }


    @Override
    public File select ()
        throws
            IOException
    {
        final File[] matches = _root.listFiles ( _filter );
        if ( null == matches || matches.length == 0 )
        {
            return null;
        }
        File res = matches[0];
        for ( final File f: matches )
        {
            res = getLatest ( res, f );
        }
        return res;
    }


    @Override
    public String toString ()
    {
        return String.format ( "LatestFileSelector with root: %s and pattern: %s",
                               _root,
                               _filter );
    }


    private static File getLatest ( File res, final File f )
        throws
            IOException
    {
        return Files.getLastModifiedTime ( f.toPath () ).toMillis () >
               Files.getLastModifiedTime ( res.toPath () ).toMillis ()
               ? f : res;
    }


    private final static Logger _log = LoggerFactory.getLogger ( LatestFileSelector.class );
    private final File _root;
    private final FilenameFilter _filter;

}
