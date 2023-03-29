package com.aem.geeks.core.services;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;

import java.util.Iterator;

public interface DemoService {
    public Iterator<Page> getPages() throws LoginException, PersistenceException;
}
