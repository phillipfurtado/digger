package com.github.phillipfurtado.digger.rest;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.phillipfurtado.digger.dto.ServidorDTO;
import com.github.phillipfurtado.digger.service.ServidorService;

@Path("/servidores")
@Produces({ MediaType.TEXT_PLAIN })
public class ServidorResource {

    @Inject
    private ServidorService servidorService;

    @GET
    public List<ServidorDTO> obterServidores() {

        return servidorService.obterServidores();
    }

    @POST
    public Response criarServidor(@Valid ServidorDTO servidor) {
        ServidorDTO criado = servidorService.criarServidor(servidor);
        return Response.ok(criado).build();
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    public Response alterarServidor(@PathParam("id") Long idServidor, @Valid ServidorDTO servidor) {
        ServidorDTO modificado = servidorService.alterarServidor(idServidor, servidor);
        return Response.ok(modificado).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response removerServidor(@PathParam("id") Long idServidor) {
        servidorService.removerServidor(idServidor);
        return Response.ok().build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}/aplicacoes")
    public ServidorDTO obterAplicacoesServidor(@PathParam("id") Long idServidor) {

        return servidorService.obterAplicacoesInstaladas(idServidor);
    }

}
