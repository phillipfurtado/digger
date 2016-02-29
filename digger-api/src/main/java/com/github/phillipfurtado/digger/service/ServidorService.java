package com.github.phillipfurtado.digger.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.github.phillipfurtado.digger.exception.DiggerException;
import com.github.phillipfurtado.digger.model.Aplicacao;
import com.github.phillipfurtado.digger.model.Servidor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@ApplicationScoped
public class ServidorService {

    private static final String ID = "_id ==";

    private static Datastore datastore;

    static {
        MongoClientURI uri = new MongoClientURI("mongodb://172.17.0.2:27017/digger");
        final MongoClient mongoClient = new MongoClient(uri);

        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.github.phillip.furtado.digger.model");

        final Datastore ds = morphia.createDatastore(mongoClient, "digger");
        ds.ensureIndexes();
        datastore = ds;
    }

    @Inject
    private ApplicationScanner appScanner;

    public List<Servidor> obterServidores() {
        return datastore.find(Servidor.class).asList();
    }

    public Servidor criarServidor(Servidor servidor) {
        final Key<Servidor> servidorKey = datastore.save(servidor);
        servidor.setId((String) servidorKey.getId());
        return servidor;
    }

    public Servidor alterarServidor(String idServidor, Servidor servidor) {
        final UpdateOperations<Servidor> updateOperations = datastore.createUpdateOperations(Servidor.class)
                .set("nome", servidor.getNome()).set("enderecoIP", servidor.getEnderecoIP())
                .set("user", servidor.getUser()).set("senha", servidor.getSenha()).set("tipoOS", servidor.getTipoOS());
        final Query<Servidor> query = datastore.createQuery(Servidor.class).filter(ID, new ObjectId(idServidor));
        datastore.update(query, updateOperations);
        return query.get();
    }

    public Servidor obterServidor(String idServidor) {
        return datastore.createQuery(Servidor.class).filter(ID, new ObjectId(idServidor)).get();
    }

    public void removerServidor(String idServidor) {
        datastore.findAndDelete(datastore.createQuery(Servidor.class).filter(ID, new ObjectId(idServidor)));
    }

    public List<Aplicacao> obterAplicacoesInstaladas(String idServidor) {
        Servidor servidor = obterServidor(idServidor);
        if (servidor == null) {
            throw new DiggerException("Servidor nao encontrado");
        }

        List<String> apps = appScanner.obterListaAplicacoesViaSHH(servidor.getEnderecoIP(), servidor.getTipoOS(),
                servidor.getUser(), servidor.getSenha());

        List<Aplicacao> aplicacoes = new ArrayList<>();
        for (String string : apps) {
            aplicacoes.add(new Aplicacao(string));
        }

        return aplicacoes;
    }
}
