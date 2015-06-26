package org.mipper.feedws.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.mipper.feedws.domain.Insider;
import org.mipper.feedws.domain.Restriction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestrictionController
{

    @RequestMapping("/list")
    public Collection<Restriction>restrictedList ()
    {
        final List<Restriction> res = new ArrayList<> ();
        res.add ( new Restriction ( "R1",
                                    Arrays.asList ( new Insider ( "Sarah-Jane", "Evans" ),
                                                    new Insider ( "Cliff", "Evans" ),
                                                    new Insider ( "Bob", "Jones" ) ) ) );
        res.add ( new Restriction ( "R2",
                                    Arrays.asList ( new Insider ( "Bob", "Jones" ) ) ) );
        res.add ( new Restriction ( "R2",
                                    Collections.emptyList () ) );
        return res;
    }
}
