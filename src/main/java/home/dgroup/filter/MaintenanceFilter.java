package home.dgroup.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

/**
 * Created by dgroup on 09.03.2015.
 */
@WebFilter(
    filterName  = "CurfewFilter",
    urlPatterns = {"/*"},
    initParams  = { @WebInitParam(name = "Duration",   value = "8" ),
                    @WebInitParam(name = "Start time", value = "22")})
public class MaintenanceFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceFilter.class);

    private LocalTime startTime;
    private LocalTime endTime;

    @Override
    public void init(FilterConfig cfg) {
        Integer time = Integer.valueOf( cfg.getInitParameter("Start time") );
        startTime = LocalTime.of( time, 0 );

        Long duration = Long.valueOf ( cfg.getInitParameter("Duration") );
        endTime = startTime.plusHours( duration );
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        LocalTime now = LocalTime.now();
        LOG.debug("Filter now {}, start {}, end {}.", now, startTime, endTime );

        if (maintenancePeriod(now)) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<h2 align='center'>System is unavailable due to maintenance operations.</h2><hr>");
            out.flush();

        } else
            chain.doFilter(request, response);
    }

    private boolean maintenancePeriod(LocalTime time){
        return startTime.isAfter(time) && endTime.isAfter(time);
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destroyed");
    }
}
