package com.github.phillipfurtado.digger;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.github.phillipfurtado.digger.dto.ServidorDTO;
import com.github.phillipfurtado.digger.rest.ServidorResource;

public class DiggerApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new LinkedHashSet<>();
        resources.add(ServidorResource.class);
        resources.add(ServidorDTO.class);
        return resources;
    }

}
