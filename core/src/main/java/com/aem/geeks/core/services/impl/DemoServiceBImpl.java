package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.DemoService;
import com.aem.geeks.core.services.DemoServiceB;
import com.aem.geeks.core.services.ContentService;
import com.day.cq.wcm.api.Page;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(service = DemoServiceB.class,immediate = true)
public class DemoServiceBImpl implements DemoServiceB {
    private static final Logger LOG= LoggerFactory.getLogger(DemoServiceBImpl.class);



    /*--------Start Tutorial #30--------*/
    @Reference(target = "(component.name=com.aem.geeks.core.services.impl.MultiServiceBImpl)")
    ContentService contentService;

    /*--------End Tutorial #30--------*/
    /*--------Start Tutorial #29--------*/
    @Reference
    DemoService demoService;

    @Override
    public List<String> getPages(){
        List<String> listPages = new ArrayList<String>();

        try {
            Iterator<Page> pages=demoService.getPages();
            while (pages.hasNext()) {
                listPages.add(pages.next().getTitle());
            }
            return listPages;
        } catch (Exception e) {
            LOG.info("\n  Exception {} ",e.getMessage());
        }
        return null;
    }

    @Override
    public String getNameWithReference() {
        return null;
    }
    /*--------End Tutorial #29--------*/
}
