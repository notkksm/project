package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.Navigation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Navigation.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = { "aemgeeks/components/content/mavigation" }
)
public class NavigationModel implements Navigation {
    @Inject
    @Named("links")
    @Via("resource")
    private List<NavigationItemModel> items;
    @Inject
    @Via("resource")
    private ImageLinkModel sponsor;
    @Inject
    @Via("resource")
    private ImageLinkModel logo;

    public List<NavigationItemModel> getItems() {
        return items;
    }

    public ImageLinkModel getSponsor() {
        return sponsor;
    }

    public ImageLinkModel getLogo() {
        return logo;
    }

}
