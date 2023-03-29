package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.SearchService;
import com.aem.geeks.core.utils.ResolverUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.query.SlingQuery;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

import static org.apache.sling.query.SlingQuery.$;

@Component(service = SearchService.class, immediate = true)
public class SearchServiceImpl implements SearchService{
    @Reference
    QueryBuilder queryBuilder;
    @Reference(target="(serviceName=getcontentservice)")
    com.aem.geeks.core.services.ResourceResolverFactory oSGiConfig;

    public Map getMap(){
        Map predicateMap = new HashMap();
        predicateMap.put("path", "/content");
        predicateMap.put("property", "jcr:content/cq:template");
        predicateMap.put("property.1_value", "/conf/editable-templates/settings/wcm/templates/project-page");
        predicateMap.put("property.2_value", "/conf/we-retail/settings/wcm/templates/redirect-page");
        predicateMap.put("property.3_value", "/conf/we-retail/settings/wcm/templates/hero-page");
        return predicateMap;
    }
    @Override
    public List<Map<String,String>> searchResult() throws LoginException, RepositoryException {
        List<Map<String,String>>resultMap=new ArrayList<>();
        ResourceResolver resourceResolver = oSGiConfig.getServiceUser();
        Session session = resourceResolver.adaptTo(Session.class);

        Query query = queryBuilder.createQuery(PredicateGroup.create(getMap()), session);
        SearchResult result = query.getResult();
        List <Hit> list = result.getHits();
        for (Hit hit : list) {
            Map<String,String> map=new HashMap<>();
            map.put("title",hit.getTitle());
            map.put("path",hit.getPath());
            resultMap.add(map);
        }
        return resultMap;
    }
}
