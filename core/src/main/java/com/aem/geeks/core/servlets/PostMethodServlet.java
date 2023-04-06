package com.aem.geeks.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/submitfsorm"}
)
public class PostMethodServlet extends SlingAllMethodsServlet {
    @Override
    protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.getWriter().write(name);

    }
}
