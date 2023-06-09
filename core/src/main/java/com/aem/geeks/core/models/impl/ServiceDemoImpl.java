package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.ServiceDemo;
import com.aem.geeks.core.services.DemoService;
import com.aem.geeks.core.services.DemoServiceB;
import com.aem.geeks.core.services.ContentService;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
@Model(adaptables = SlingHttpServletRequest.class,
adapters = ServiceDemo.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoImpl  implements ServiceDemo {
    private static final Logger LOG= LoggerFactory.getLogger(ServiceDemoImpl.class);

    /*--------Start Tutorial #29--------*/
    @OSGiService
    DemoService demoService;

    /*@Inject*/
    @OSGiService
    DemoServiceB demoServiceB;


    @Override
    public Iterator<Page> getPagesList() throws PersistenceException, LoginException {
        return demoService.getPages();
    }

    @Override
    public List<String> getPageTitleList() {
        return demoServiceB.getPages();
    }

    @Override
    public String getNameFromService() {
        return null;
    }

    @Override
    public String getNameFromServiceB() {
        return null;
    }
    /*--------End Tutorial #29--------*/

    /*--------Start Tutorial #30--------*/
    @OSGiService(filter = "(component.name=serviceA)")
    ContentService contentService;

    @OSGiService(filter = "(component.name=com.aem.geeks.core.services.impl.MultiServiceBImpl)")
    ContentService contentServiceB;

    @Override
    public String getNameWithReference() {
        return demoServiceB.getNameWithReference();
    }
    /*--------End Tutorial #30--------*/
}

