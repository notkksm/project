package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.SearchComponents;
import com.aem.geeks.core.models.SearchPages;
import com.aem.geeks.core.services.SearchComponentService;
import com.aem.geeks.core.services.SearchPageService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = SearchComponents.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = {"aemgeeks/components/content/search"}
)

public class SearchComponentsImpl implements SearchComponents {
    @Inject
    SearchPageService searchService;
    @Inject
    SearchComponentService searchComponentService;
    @OSGiService(filter = "(serviceName=getcontentservice)")
    com.aem.geeks.core.services.ResourceResolverFactory oSGiConfig;
    private static final Logger LOG = LoggerFactory.getLogger(SearchComponentService.class);
    @Override
    public List<String> getComponents() {
        List<String> componentTitle = new ArrayList<>();
        try (ResourceResolver resourceResolver = oSGiConfig.getServiceUser()) {
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage("/content/we-retail/us/en/experience/arctic-surfing-in-lofoten");
            List<Resource> resources = searchComponentService.getPageComponent(page, "weretail/components/content/title");
            for (Resource resource : resources) {
                String resourcePath = resource.getPath();
                componentTitle.add(resourcePath.substring(resourcePath.lastIndexOf("/") + 1));
            }

        } catch (Exception e) {
            LOG.info("\n Exception {} ", e.getMessage());
        }
        return componentTitle;
    }

}