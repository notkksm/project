package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.Area;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FigureModel {
    @ValueMapValue
    private String type;
    @ValueMapValue
    private String squareside;
    @ValueMapValue
    private String radius;
    @ValueMapValue
    private String rectside;
    @ValueMapValue
    private String rectsecondside;

    private Double area;

    @PostConstruct
    public void init() {
        if (getType().equals("circle")) {
            Double radius = Double.valueOf(getRadius());
            setArea(3.14 * radius * radius);
        } else if (type.equals("rectangle")) {
            setArea(Double.valueOf(getRectside()) * Double.valueOf(getRectsecondside()));
        } else {
            Double side = Double.valueOf(getSquareside());
            setArea(side * side);
        }
    }

    public String getType() {
        return type;
    }
    public String getSquareside() {
        return squareside;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getRadius() {
        return radius;
    }
    public String getRectside() {
        return rectside;
    }
    public String getRectsecondside() {
        return rectsecondside;
    }
}
