package com.aem.geeks.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;

import java.util.Iterator;
import java.util.List;

public interface ServiceDemo {

    public Iterator<Page> getPagesList() throws PersistenceException, LoginException;
    public List<String> getPageTitleList();

    public String getNameFromService();
    public String getNameFromServiceB();
    public String getNameWithReference();
}
