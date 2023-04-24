package com.aem.geeks.core.listeners;

import com.aem.geeks.core.services.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;


@Component(immediate = true,service= EventListener.class)
public class BasicPageEventListener implements EventListener{

    private static final Logger log = LoggerFactory.getLogger(BasicPageEventListener.class);
    private Session session;

    @Reference
    SlingRepository slingRepository;
    @Reference(target="(serviceName=getcontentservice)")
    ResourceResolverFactory oSGiConfig;



    @Activate
    public void activate() throws Exception {
        try {
            session = slingRepository.loginService("modifycontentservice",null);
            session.getWorkspace().getObservationManager().addEventListener(
                    this,
                    Event.NODE_ADDED | Event.PROPERTY_ADDED,
                    "/content/aemgeeks_training/en/basic-page",
                    true,
                    null,
                    null,
                    true);
        } catch (RepositoryException e){
            log.error(" \n Error: {} ",e.getMessage());
        } catch (Exception e){
            log.error(" \n Error: {} ",e.getMessage());
        }
    }

    public void onEvent(EventIterator eventIterator) {
        if (eventIterator.hasNext()) {
            try {
                log.info("Path: {}", eventIterator.nextEvent().getPath());
            } catch (RepositoryException e) {
                log.error("Error", e.getMessage());
            }
        }
    }

}
