package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.Area;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Exporter(name = "jackson", extensions ="json",selector = "f-json")

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = Area.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = { "aemgeeks/components/content/arealist" }
)

public class AreaModel implements Area {
    @Inject
    @Via("resource")
    private List<FigureModel> figures;
    @JsonIgnore
    public List<FigureModel> getFigures() {
        return figures;
    }
    public Map<String, String> getFiguresExport() {
        Map<String,String>map=new HashMap<>();
        for(FigureModel figureModel:figures){
            map.put(figureModel.getType(),figureModel.getArea().toString());
        }
        return map;
    }

}