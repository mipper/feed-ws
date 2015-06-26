package org.mipper.feedws.service;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

public class ResourceRecord
{

    public ResourceRecord ( Resource resource, MediaType type )
    {
        _type = type;
        _resource = resource;
    }


    public MediaType getMediaType ()
    {
        return _type;
    }


    public Resource getResource ()
    {
        return _resource;
    }


    private final MediaType _type;
    private final Resource _resource;

}