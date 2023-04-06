package com.aem.geeks.core.workflows;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.aem.geeks.core.services.ResourceResolverFactory;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

@Component(
        service = WorkflowProcess.class,
        immediate = true,
        property = {
                "process.label" + " = Asset Workflow Process"
        }
)
public class AssetWorkflowProcess implements WorkflowProcess {

    private static final Logger log = LoggerFactory.getLogger(AssetWorkflowProcess.class);
    @Reference(target="(serviceName=modifycontentservice)")
    ResourceResolverFactory oSGiConfig;

    public String getTagTitle(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-DD_hh-mm");
        LocalDateTime now = LocalDateTime.now();
        return "aemgeeks:dam-assets/"+dtf.format(now);
    }

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) {
        try(ResourceResolver resourceResolver=oSGiConfig.getServiceUser()) {
            WorkflowData workflowData = workItem.getWorkflowData();
            if (workflowData.getPayloadType().equals("JCR_PATH")) {
                String path = workflowData.getPayload().toString() + "/jcr:content/metadata";
                Resource resource=resourceResolver.getResource(path);
                TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
                Tag[] assetTags=tagManager.getTags(resource);
                String tagTitle=getTagTitle();
                if(assetTags.length==0){
                    Tag newTag = tagManager.createTagByTitle(tagTitle);
                    tagManager.setTags(resource, Collections.singleton(newTag).toArray(new Tag[0]), true);
                }
                else {
                    Tag assetTag = tagManager.resolve(tagTitle);
                    if(assetTag==null){
                        Tag[] updatedTags= Arrays.copyOf(assetTags,assetTags.length+1);
                        updatedTags[updatedTags.length-1]=assetTag;
                    }
                }

            }
        }catch (Exception e){
                log.info("\n  Exception {} ",e.getMessage());
            }
        }
    }


