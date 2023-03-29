package com.aem.geeks.core.models;

import org.apache.sling.api.resource.LoginException;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

public interface Author {
    List<Map<String,String>> getList() throws LoginException, RepositoryException;
}
