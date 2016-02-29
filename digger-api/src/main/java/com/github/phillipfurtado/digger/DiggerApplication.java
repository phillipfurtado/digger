package com.github.phillipfurtado.digger;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.core.Application;

import com.github.phillipfurtado.digger.handle.CorsFilter;
import com.github.phillipfurtado.digger.handle.ExceptionHandler;

public class DiggerApplication extends Application {

    @Inject
    private Instance<Resource> resources;

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resourceList = new LinkedHashSet<>();
        resources.forEach(resource -> resourceList.add(resource.getClass()));
        resourceList.add(ExceptionHandler.class);
        resourceList.add(CorsFilter.class);
        return resourceList;
    }

}
