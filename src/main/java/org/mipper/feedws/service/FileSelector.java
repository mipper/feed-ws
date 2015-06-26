package org.mipper.feedws.service;

import java.io.File;
import java.io.IOException;


public interface FileSelector
{

    File select ()
        throws
            IOException;

}
