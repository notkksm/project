package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.Author;
import com.aem.geeks.core.services.SearchService;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.RepositoryException;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author.class,
        resourceType = AuthorImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name = "jackson", extensions ="json",selector = "geeks",
        options = {
                @ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value="true"),
                @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true")
        })

@JsonRootName("author-details")
public class AuthorImpl implements Author{
    private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);
    final protected static String RESOURCE_TYPE="aemgeeks/components/content/author";

    @Inject
    SearchService searchService;

    @Override
    public List<Map<String,String>> getList() throws LoginException, RepositoryException {
        return searchService.searchResult();
    }



}
