package com.github.phillipfurtado.digger.rest;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.phillipfurtado.digger.Resource;
import com.github.phillipfurtado.digger.model.Aplicacao;
import com.github.phillipfurtado.digger.model.Servidor;
import com.github.phillipfurtado.digger.service.ServidorService;

@Path("/servidores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServidorResource implements Resource {

    @Inject
    private ServidorService servidorService;

    @GET
    public List<Servidor> obterServidores() {

        return servidorService.obterServidores();
    }

    @POST
    public Response criarServidor(@Valid Servidor servidor) {
        Servidor criado = servidorService.criarServidor(servidor);
        return Response.ok(criado).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterarServidor(@PathParam("id") String idServidor, @Valid Servidor servidor) {
        Servidor modificado = servidorService.alterarServidor(idServidor, servidor);
        return Response.ok(modificado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerServidor(@PathParam("id") String idServidor) {
        servidorService.removerServidor(idServidor);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response obterServidor(@PathParam("id") String idServidor) {
        Servidor server = servidorService.obterServidor(idServidor);
        return Response.ok(server).build();
    }

    @GET
    @Path("/{id}/aplicacoes")
    public List<Aplicacao> obterAplicacoesServidor(@PathParam("id") String idServidor) {

        return servidorService.obterAplicacoesInstaladas(idServidor);
    }

}
