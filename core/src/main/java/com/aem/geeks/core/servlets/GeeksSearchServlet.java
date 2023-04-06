package com.aem.geeks.core.servlets;

import com.aem.geeks.core.services.SearchPageService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;


@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/geeks/search"}
)
public class GeeksSearchServlet extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(GeeksSearchServlet.class);

    @Reference
    SearchPageService searchService;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        JSONObject searchResult=null;
        try {
            String searchtext = req.getRequestParameter("searchText").getString();
            int pageNumber = Integer.parseInt(req.getRequestParameter("pageNumber").getString())-1;
            int resultPerPage = Integer.parseInt(req.getRequestParameter("resultPerPage").getString());
            int startResult=pageNumber*resultPerPage;
        } catch (Exception e) {
            LOG.info("\n ERROR {} ", e.getMessage());
        }

        resp.setContentType("application/json");
        resp.getWriter().write(searchResult.toString());
    }

}
