package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.Area;
import com.aem.geeks.core.models.Search;
import com.aem.geeks.core.services.SearchService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Exporter(name = "jackson", extensions = "json", selector = "f-json")

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = Search.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class SearchImpl implements Search {
    @Inject
    SearchService searchService;

    @Override
    public List<Map<String,String>> getList() throws LoginException, RepositoryException {
        return searchService.searchResult();
    }
}