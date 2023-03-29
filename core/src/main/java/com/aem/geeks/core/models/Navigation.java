package com.aem.geeks.core.models;

import com.aem.geeks.core.models.impl.ImageLinkModel;
import com.aem.geeks.core.models.impl.NavigationItemModel;

import java.util.List;

public interface Navigation {
    public ImageLinkModel getLogo();
    public ImageLinkModel getSponsor();
    public List<NavigationItemModel> getItems();
}
