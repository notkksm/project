package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.Author;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class AuthorImplTest {

    private final AemContext aemContext=new AemContext();

    private Author author;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(AuthorImpl.class);
        aemContext.load().json("/com/aem/geeks/core/models/impl/Author.json","/component");
        aemContext.load().json("/com/aem/geeks/core/models/impl/Page.json","/page");
        aemContext.load().json("/com/aem/geeks/core/models/impl/Resource.json","/resource");
    }



}