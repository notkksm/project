package com.aem.geeks.core.services;
import com.day.cq.wcm.api.Page;
import java.util.Iterator;

public interface ContentService {
    public Iterator<Page> getPages();
}
