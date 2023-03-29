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

import java.util.Iterator;

@Component(service = ContentService.class, immediate = true)
public class ReadContentService implements ContentService {

    @Reference(target="(serviceName=getcontentservice)")
    ResourceResolverFactory oSGiConfig;

    @Override
    public Iterator<Page> getPages() {
        ResourceResolver resourceResolver = oSGiConfig.getServiceUser();
        Resource pageResource = resourceResolver.getResource("/content/we-retail/us/en");
        return $(pageResource).children("cq:Page").map(Page.class).iterator();
    }
}


