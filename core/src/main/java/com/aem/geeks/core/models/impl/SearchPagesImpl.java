package com.aem.geeks.core.models.impl;

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
import java.util.*;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = SearchPages.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = {"aemgeeks/components/content/search"}
)

public class SearchPagesImpl implements SearchPages {
    @Inject
    SearchPageService searchService;
    @Inject
    SearchComponentService searchComponentService;
    @OSGiService(filter = "(serviceName=getcontentservice)")
    com.aem.geeks.core.services.ResourceResolverFactory oSGiConfig;
    private static final Logger LOG = LoggerFactory.getLogger(SearchPages.class);

    @Override
    public List<Page> getList() throws RepositoryException, LoginException {
        List<String> list = new ArrayList<>();
        list.add("/conf/we-retail/settings/wcm/templates/redirect-page");
        list.add("/conf/we-retail/settings/wcm/templates/hero-page");
        List<Page> pages = searchService.getPagesByTemplate("/content/we-retail", list);
        return pages;
    }



}