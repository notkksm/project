package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.SearchComponentService;
import com.aem.geeks.core.services.SearchPageService;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.sling.query.SlingQuery.$;

@Component(service = SearchComponentService.class, immediate = true)
public class SearchComponentsServiceImpl implements SearchComponentService {
    @Reference(target="(serviceName=getcontentservice)")
    com.aem.geeks.core.services.ResourceResolverFactory oSGiConfig;
    private static final Logger LOG= LoggerFactory.getLogger(SearchComponentService.class);


    @Override
    public List<Resource> getPageComponent(Page page, String componentType) {
        List<Resource>resources=null;
        ResourceResolver resourceResolver=null;
        try{
           resourceResolver = resourceResolver = oSGiConfig.getServiceUser();
            Resource pageResource = resourceResolver.getResource(page.getPath());
            resources = $(pageResource)
                    .find("wcm/foundation/components/responsivegrid")
                    .has(componentType)
                    .children(componentType)
                    .asList();

        }catch (Exception e) {
            LOG.info("\n Exception {} ",e.getMessage());
        }
        finally {
            resourceResolver.close();
        }
        return resources;

    }
}
