package com.aem.geeks.core.services;

import org.apache.sling.api.resource.LoginException;
import org.json.JSONObject;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

public interface SearchService {
    public List<Map<String,String>> searchResult() throws LoginException, RepositoryException;
}
