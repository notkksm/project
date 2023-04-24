package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.SearchPageService;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

import static org.apache.sling.query.SlingQuery.$;

@Component(service = SearchPageService.class, immediate = true)
public class SearchPageServiceImpl implements SearchPageService {
    @Reference
    QueryBuilder queryBuilder;
    @Reference(target="(serviceName=getcontentservice)")
    com.aem.geeks.core.services.ResourceResolverFactory oSGiConfig;
    private static final Logger LOG= LoggerFactory.getLogger(SearchPageService.class);

    public Map getMap(String path,List<String> templates){
        Map predicateMap = new HashMap();
        int i = 1;
        predicateMap.put("path", path);
        predicateMap.put("property", "jcr:content/cq:template");
        for (String template : templates) {
            predicateMap.put("property." + i + "_value", template);
            i++;
        }
        return predicateMap;
    }

    @Override
    public List<Page> getPagesByTemplate(String path,List<String>templates) throws LoginException, RepositoryException {

        List<Map<String,String>>resultMap=new ArrayList<>();
        List<Page> pages = new ArrayList<>();
        ResourceResolver resourceResolver=null;
        try {
            resourceResolver = oSGiConfig.getServiceUser();
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Session session = resourceResolver.adaptTo(Session.class);
            Map predicateMap = new HashMap();
            int i = 1;
            predicateMap.put("path", path);
            predicateMap.put("property", "cq:template");
            for (String template : templates) {
                predicateMap.put("property." + i + "_value", template);
                i++;
            }
            Query query = queryBuilder.createQuery(PredicateGroup.create(predicateMap), session);
            SearchResult result = query.getResult();
            for (Hit hit : result.getHits()) {
                Page containingPage = pageManager.getContainingPage(hit.getResource());
                if (containingPage != null) {
                    pages.add(containingPage);
                }
            }
        } catch (Exception e) {
            LOG.error("\n Exception {} ",e.getMessage());
        }
        finally {
            resourceResolver.close();
        }
        return pages;
    }
}
