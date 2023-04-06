package com.aem.geeks.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;

import javax.jcr.RepositoryException;
import java.util.Iterator;
import java.util.List;

public interface Content {
    public Iterator<Page> getPagesList() throws RepositoryException;
    public Iterator<Page> getUpdatedList();
}
