package com.aem.geeks.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;

import java.util.Iterator;
import java.util.List;

public interface Content {
    public Iterator<Page> getPagesList();
    public Iterator<Page> getUpdatedList();
}
