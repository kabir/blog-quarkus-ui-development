package org.kabir.quarkus.ui;


import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:kabir.khan@jboss.com">Kabir Khan</a>
 */
@WebFilter(urlPatterns = "/*")
public class IndexHtmlFilter extends HttpFilter {

    private static final Pattern FILE_NAME_PATTERN = Pattern.compile(".*[.][a-zA-Z\\d]+");

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (isIndexHtmlRequest(request)) {
            request.getRequestDispatcher("/").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isIndexHtmlRequest(HttpServletRequest request) {
        String path = request.getRequestURI().substring(
                request.getContextPath().length()).replaceAll("[/]+$", "");
        if (path.equals("")) {
            return true;
        }
        if (FILE_NAME_PATTERN.matcher(path).matches()) {
            return false;
        }
        if (path.startsWith("/api/") || path.startsWith("/servlet/")) {
            return false;
        }

        return true;
    }
}