package org.mipper.feedws.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Restriction
{

    public Restriction ( String rcode, Collection<Insider> insiders )
    {
        _rcode = rcode;
        _insiders.addAll ( insiders );
    }
    
    
    public List<Insider> getInsiders ()
    {
        return Collections.unmodifiableList ( _insiders );
    }
    
    
    public String getRCode ()
    {
        return _rcode;
    }
    

    private String _rcode;
    private List<Insider> _insiders = new ArrayList<> ();
    
}
