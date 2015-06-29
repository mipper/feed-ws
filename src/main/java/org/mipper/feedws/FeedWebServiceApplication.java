package org.mipper.feedws;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.mipper.feedws.controller.FileResourceController;
import org.mipper.feedws.service.FileSelectorResourceService;
import org.mipper.feedws.service.LatestFileSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class FeedWebServiceApplication
{

    public static void main ( String[] args )
    {
        SpringApplication.run ( FeedWebServiceApplication.class, args );
    }


    @Bean
    public FileResourceController fileResourceController ()
    {
        final Path pwd = Paths.get ( _env.getRequiredProperty ( "feed.root.dir" ) )
                              .toAbsolutePath ()
                              .normalize ();
        final FileResourceController res = new FileResourceController ();
        res.addResourceService ( "restricted",
                                 new FileSelectorResourceService ( new LatestFileSelector ( pwd.resolve ( "restricted" )
                                                                                               .toFile (),
                                                                                            "list-\\d{8}-\\d{6}.csv" ),
                                                                   MediaType.TEXT_PLAIN ) ) ;
        res.addResourceService ( "html",
                                 new FileSelectorResourceService ( new LatestFileSelector ( pwd.resolve ( "html" )
                                                                                               .toFile (),
                                                                                            ".*\\.html" ),
                                                                   MediaType.TEXT_HTML ) );
        res.addResourceService ( "empty",
                                 new FileSelectorResourceService ( new LatestFileSelector ( pwd.resolve ( "empty" )
                                                                                               .toFile (),
                                                                                            ".*\\.html" ),
                                                                   MediaType.TEXT_HTML ) );
        return res;
    }


    @Autowired
    private Environment _env;

}
