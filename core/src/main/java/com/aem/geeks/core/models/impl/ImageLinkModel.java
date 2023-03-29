package com.aem.geeks.core.models.impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageLinkModel {
    @ValueMapValue
    private String image;
    @ValueMapValue
    private String link;

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

}




