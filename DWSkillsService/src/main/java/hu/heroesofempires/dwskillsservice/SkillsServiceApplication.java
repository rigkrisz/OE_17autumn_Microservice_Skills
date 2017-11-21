package hu.heroesofempires.dwskillsservice;

import hu.heroesofempires.dwskillsservice.api.RequestHandler;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

public class SkillsServiceApplication extends Application<SkillsServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SkillsServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "SkillsService";
    }

    @Override
    public void initialize(final Bootstrap<SkillsServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final SkillsServiceConfiguration configuration,
                    final Environment environment)
    {
        // Enable CORS headers
        final FilterRegistration.Dynamic filter =
        environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        filter.setInitParameter("allowedOrigins", "*");
        filter.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        filter.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // DO NOT pass a preflight request to down-stream auth filters
        // unauthenticated preflight requests should be permitted by spec
        filter.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());
        
        // TODO: implement application
        RequestHandler request = new RequestHandler();
        environment.jersey().register(request);
    }
}
