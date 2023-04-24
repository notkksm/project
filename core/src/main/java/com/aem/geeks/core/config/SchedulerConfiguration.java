package com.aem.geeks.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Tag Scheduler"
)
public @interface SchedulerConfiguration {

    @AttributeDefinition(
            name = "Scheduler name",
            type = AttributeType.STRING)
    public String schedulerName() default "Tas Scheduler";

    @AttributeDefinition(
            name = "Cron Expression",
            type = AttributeType.STRING)
    public String cronExpression() default "0/20 * * * * ?";
}

