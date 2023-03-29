package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.ResourceResolverFactory;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.*;
import java.util.Collections;
import java.util.Map;

@Component(service = ResourceResolverFactory.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = ResourceResolverFactoryImpl.ServiceConfig.class, factory = true)
public class ResourceResolverFactoryImpl implements ResourceResolverFactory {
    @Reference
    org.apache.sling.api.resource.ResourceResolverFactory resourceResolverFactory;
    private String serviceName;
    @Activate
    protected void activate(ServiceConfig serviceConfig) {
        serviceName = serviceConfig.serviceName();
    }
    @Override
    public ResourceResolver getServiceUser() {
        ResourceResolver resourceResolver = null;
        try {
            final Map<String, Object> param = Collections.singletonMap(org.apache.sling.api.resource.ResourceResolverFactory.SUBSERVICE, serviceName);
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
        return resourceResolver;
    }

    @ObjectClassDefinition(name = "Resource Resolver Factory")
    public @interface ServiceConfig {

        @AttributeDefinition(
                name = "serviceName",
                description = "Enter service name.",
                type = AttributeType.STRING)
        public String serviceName();
    }

}