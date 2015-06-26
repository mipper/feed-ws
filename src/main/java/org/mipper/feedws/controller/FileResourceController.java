package org.mipper.feedws.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.mipper.feedws.service.FileSelectorResourceService;
import org.mipper.feedws.service.LatestFileSelector;
import org.mipper.feedws.service.ResourceRecord;
import org.mipper.feedws.service.ResourceService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FileResourceController
{

    public FileResourceController ( String root )
    {
        super ();
        final Path pwd = Paths.get ( root )
                              .toAbsolutePath ()
                              .normalize ();
        _factories.put ( "restricted",
                         new FileSelectorResourceService ( new LatestFileSelector ( pwd.resolve ( "restricted" ).toFile (),
                                                                                    "list-\\d{8}-\\d{6}.csv" ),
                                                           MediaType.TEXT_PLAIN ) );
        _factories.put ( "html",
                         new FileSelectorResourceService ( new LatestFileSelector ( pwd.resolve ( "html" ).toFile (),
                                                                                    ".*\\.html" ),
                                                           MediaType.TEXT_HTML ) );
    }


    @RequestMapping(value="/file/{type}",produces="application/html")
    public ResponseEntity<Resource> fileResource ( @PathVariable String type )
        throws
            IOException
    {
        final HttpHeaders headers = new HttpHeaders ();
        headers.add ( "Cache-Control", "no-cache, no-store, must-revalidate" );
        headers.add ( "Pragma", "no-cache" );
        headers.add ( "Expires", "0" );
        final ResourceRecord res = getResourceService ( type ).getResource ();
        return ResponseEntity.ok ()
                             .headers ( headers )
                             .contentLength ( res.getResource ().contentLength () )
                             .contentType ( res.getMediaType () )
                             .body ( res.getResource () );
    }


    private ResourceService getResourceService ( String type )
    {
        final ResourceService res = _factories.get ( type );
        if ( null == res )
        {
            throw new IllegalArgumentException ( "No ResourceService available for " + type );
        }
        return res;
    }


    private final Map<String, ResourceService> _factories = new HashMap<> ();

}
