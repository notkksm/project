package com.aem.geeks.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.apache.sling.api.servlets.ServletResolverConstants.*;

@Component(
        service= {Servlet.class},
        property={
                "sling.servlet.methods="+ HttpConstants.METHOD_GET,
                SLING_SERVLET_METHODS+"="+HttpConstants.METHOD_POST,
                "sling.servlet.resourceTypes="+ "aemgeeks/components/structure/basepage",
                SLING_SERVLET_PATHS+"="+"/bin/submitform",
                "sling.servlet.selectors=" + "project",
                "sling.servlet.extensions"+"="+"html"
        })
public class FormServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws  IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.print("<html>");
        writer.print("<body>");
        writer.print("<form method=\"post\" action=\"/bin/submitform\">");
        writer.print("<label for=\"firstName\">Name:</label>");
        writer.print("<input type=\"text\" name=\"name\"><br><br>");
        writer.print("<input type=\"submit\" value=\"Submit\">");
        writer.print("</form>");
        writer.print("</body>");
        writer.print("</html>");
    }
    @Override
    protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {
        resp.getWriter().write("name");

    }

}
