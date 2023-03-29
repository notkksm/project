package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.ContentService;
import com.aem.geeks.core.services.ResourceResolverFactory;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import static org.apache.sling.query.SlingQuery.$;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceRanking;

import java.util.Iterator;

@Component(service = ContentService.class, immediate = true)
@ServiceRanking(1001)
public class ModifyContentService implements ContentService {

    @Reference(target="(serviceName=modifycontentservice)")
    ResourceResolverFactory oSGiConfig;

    @Override
    public Iterator<Page> getPages() throws LoginException, PersistenceException {
        ResourceResolver resourceResolver = oSGiConfig.getServiceUser();
        Iterator<Page> pagesList = null;
        try {
            Resource pageResource = resourceResolver.getResource("/content/we-retail/us/en");
            Iterator<Resource> pages = pageResource.listChildren();
            while (pages.hasNext()) {
                Resource currentPageContentRes = pages.next().getChild(JcrConstants.JCR_CONTENT);
                if (currentPageContentRes != null) {
                    ModifiableValueMap modifiableValueMap = currentPageContentRes.adaptTo(ModifiableValueMap.class);
                    String title = (String) modifiableValueMap.get(JcrConstants.JCR_TITLE);
                    modifiableValueMap.put(JcrConstants.JCR_TITLE, "Upd: " + title);
                    resourceResolver.commit();
                }
            }
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage("/content/we-retail/us/en");
            pagesList = page.listChildren();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pagesList;
    }
}


