package org.mipper.feedws.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.mipper.feedws.service.ResourceRecord;
import org.mipper.feedws.service.ResourceService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FileResourceController
{

    public void addResourceService ( String path, ResourceService service )
    {
        _factories.put ( path, service );

    }


    @RequestMapping(value="/file/{type}", produces="application/html")
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
