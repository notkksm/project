package com.aem.geeks.core.listeners;

import com.aem.geeks.core.services.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = JobConsumer.class,
            immediate = true,
            property = {
                JobConsumer.PROPERTY_TOPICS + "=basic-page-job"
        })
public class AudioJobConsumer implements JobConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(AudioJobConsumer.class);

    @Reference(target="(serviceName=getcontentservice)")
    ResourceResolverFactory oSGiConfig;


    @Override
    public JobResult process(Job job) {
        try(ResourceResolver resourceResolver = oSGiConfig.getServiceUser()) {
            String resourceType = (String) job.getProperty("resourceType");
            String event = (String) job.getProperty("event");
            if (resourceType.equals("acs-commons/components/content/audio")) {
                LOG.info("\n Job for audio component : "+ event);
                return JobResult.OK;
            } else {
                LOG.info("\n Error in Job (not audio component)"+ event);
                return JobResult.FAILED;
            }
        }
    }
}
