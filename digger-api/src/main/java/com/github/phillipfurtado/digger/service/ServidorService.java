package com.github.phillipfurtado.digger.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.phillipfurtado.digger.dto.ServidorDTO;

@ApplicationScoped
public class ServidorService {

    @Inject
    private ApplicationScanner appScanner;

    public List<ServidorDTO> obterServidores() {
        List<ServidorDTO> servidores = new ArrayList<>();
        ServidorDTO s1 = new ServidorDTO("webserver", "192.168.0.1");
        servidores.add(s1);
        return servidores;
    }

    public ServidorDTO criarServidor(ServidorDTO servidor) {
        return null;
    }

    public ServidorDTO alterarServidor(Long idServidor, ServidorDTO servidor) {
        return null;
    }

    public void removerServidor(Long idServidor) {

    }

    public ServidorDTO obterAplicacoesInstaladas(Long idServidor) {
        // TODO obter servidor do banco de dados
        ServidorDTO dto = new ServidorDTO("vagrant", "10.11.12.42");
        dto.setTipoOS(1);

        List<String> apps = appScanner.obterListaAplicacoesViaSHH(dto.getEnderecoIP(), dto.getTipoOS(), "vagrant",
                "vagrant");

        dto.setAplicacoes(apps);
        return dto;
    }
}
