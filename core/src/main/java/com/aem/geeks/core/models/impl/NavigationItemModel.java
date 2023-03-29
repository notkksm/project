package com.aem.geeks.core.models.impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationItemModel {

    @ValueMapValue
    private String title;
    @ValueMapValue
    private String link;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }


}
