package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.ContentService;
import com.aem.geeks.core.services.ResourceResolverFactory;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.*;
import static org.apache.sling.query.SlingQuery.$;

import org.apache.sling.query.SlingQuery;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceRanking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

@Component(service = ContentService.class, immediate = true)
public class ReadContentService implements ContentService {
    private static final Logger LOG= LoggerFactory.getLogger(ContentService.class);

    @Reference(target="(serviceName=getcontentservice)")
    ResourceResolverFactory oSGiConfig;

    @Override
    public Iterator<Page> getPages() {
        Iterator<Page>pages=null;
        try(ResourceResolver resourceResolver = oSGiConfig.getServiceUser()) {
            Resource pageResource = resourceResolver.getResource("/content/we-retail/us/en");
            pages= $(pageResource).children("cq:Page").map(Page.class).iterator();
        }
        catch (Exception e) {
            LOG.info("\n Exception {} ",e.getMessage());
        }
        return pages;
    }
}


