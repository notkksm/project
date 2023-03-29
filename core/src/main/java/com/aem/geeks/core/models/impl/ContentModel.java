package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.services.ContentService;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import java.util.Iterator;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = com.aem.geeks.core.models.Content.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ContentModel implements com.aem.geeks.core.models.Content {
    @OSGiService(filter = "(component.name=com.aem.geeks.core.services.impl.ReadContentService)")
    ContentService readContentService;
    @OSGiService(filter = "(component.name=com.aem.geeks.core.services.impl.ModifyContentService)")
    ContentService updateContentService;

    @Override
    public Iterator<Page> getPagesList() throws PersistenceException, LoginException {
        return readContentService.getPages();
    }
    @Override
    public Iterator<Page> getUpdatedList() throws PersistenceException, LoginException {
        return updateContentService.getPages();
    }
}

