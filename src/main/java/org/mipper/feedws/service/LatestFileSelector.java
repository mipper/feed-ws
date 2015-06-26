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

    public LatestFileSelector ( File root, String pattern )
    {
        _root = root;
        _pattern = pattern;
        _log.info ( "Root: {}, Pattern: {}", _root, _pattern );
    }


    @Override
    public File select ()
        throws
            IOException
    {
        final File[] matches = _root.listFiles ( ( FilenameFilter ) ( dir, name ) ->
                {
                    _log.debug ( "Checking if {} matches.", name );
                    return name.matches ( _pattern );
                } );
        if ( null == matches || matches.length == 0 )
        {
            return null;
        }
        File res = matches[0];
        for ( final File f: matches )
        {
            if ( res == null )
            {
                res = f;
            }
            else
            {
                res = getLatest ( res, f );
            }
        }
        return res;
    }


    @Override
    public String toString ()
    {
        return String.format ( "LatestFileSelector with root: %s and pattern: %s",
                               _root,
                               _pattern );
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
    private final String _pattern;

}
