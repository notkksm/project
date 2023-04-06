package com.aem.geeks.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;

import javax.jcr.RepositoryException;
import java.util.List;

public interface SearchComponents {
    List<String>getComponents();
}
