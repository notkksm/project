package com.aem.geeks.core.services;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;

import java.util.List;

public interface SearchComponentService {
    List<Resource> getPageComponent(Page page, String type);
}
