package com.aem.geeks.core.schedulers;

import com.aem.geeks.core.config.SchedulerConfiguration;
import com.aem.geeks.core.services.ResourceResolverFactory;
import com.day.cq.tagging.InvalidTagFormatException;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Component(immediate = true, service = Runnable.class)
@Designate(ocd = SchedulerConfiguration.class)
public class TagsScheduler implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(TagsScheduler.class);

    private int schedulerId;

    @Reference
    private Scheduler scheduler;
    @Reference(target="(serviceName=modifycontentservice)")
    ResourceResolverFactory oSGiConfig;

    @Activate
    protected void activate(SchedulerConfiguration config) {
        schedulerId = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(SchedulerConfiguration config) {
        removeScheduler();
    }

    protected void removeScheduler() {
        scheduler.unschedule(String.valueOf(schedulerId));
    }

    protected void addScheduler(SchedulerConfiguration config) {
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerId));
        scheduler.schedule(this, scheduleOptions);
    }
   @Override
    public void run() {
       try(ResourceResolver resourceResolver=oSGiConfig.getServiceUser()) {
           Resource resource=resourceResolver.getResource("/content/aemgeeks_training/en/home-page/jcr:content");
           TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
           Tag newTag = tagManager.createTagByTitle(getTagTitle());
           tagManager.setTags(resource, Collections.singleton(newTag).toArray(new Tag[0]), true);

       } catch (InvalidTagFormatException e) {
           throw new RuntimeException(e);
       }
       catch (Exception e){
           LOG.info("\n Error " , e.getMessage());
       }
   }
    public String getTagTitle(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return "aemgeeks:home-page/"+dtf.format(now);
    }

}
