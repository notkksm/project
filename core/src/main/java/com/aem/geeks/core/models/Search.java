package com.aem.geeks.core.models;

import org.apache.sling.api.resource.LoginException;
import org.json.JSONObject;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

public interface Search {
    public List<Map<String,String>> getList() throws LoginException, RepositoryException;
}
