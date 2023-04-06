package com.aem.geeks.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

public interface Author {
    List<Page> getList() throws LoginException, RepositoryException;
}
