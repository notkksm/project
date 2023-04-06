package com.aem.geeks.core.services;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;

import javax.jcr.RepositoryException;
import java.util.List;

public interface SearchPageService {
    List<Page> getPagesByTemplate(String path,List<String>templates) throws LoginException, RepositoryException;

}
