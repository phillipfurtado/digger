package com.github.phillipfurtado.digger.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

import com.github.phillipfurtado.digger.exception.DiggerException;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

@ApplicationScoped
public class ApplicationScanner {

    public List<String> obterListaAplicacoesViaSHH(String hostname, Integer osType, String username, String pwd) {

        try {
            if (osType == 1) {

                String dpkgResult = sshExec(hostname, username, pwd, "dpkg --get-selections | cut -f1");
                if (dpkgResult != null) {
                    return Arrays.asList(dpkgResult.split("\\n"));
                }
            }
            if (osType == 2) {
                String rpmResult = sshExec(hostname, username, pwd, "rpm -qa");
                if (rpmResult != null) {
                    return Arrays.asList(rpmResult.split("\\n"));
                }
            }
        } catch (IOException e) {
            throw new DiggerException("Nao foi possivel conectar ao host destino", e);
        }

        return new ArrayList<>();
    }

    private String sshExec(String hostname, String username, String pwd, String command) throws IOException {

        final SSHClient ssh = new SSHClient();
        ssh.loadKnownHosts();

        ssh.addHostKeyVerifier(new PromiscuousVerifier());
        ssh.connect(hostname);
        ssh.authPassword(username, pwd);

        try {
            final Session session = ssh.startSession();
            try {
                final Command cmd = session.exec(command);
                String result = IOUtils.readFully(cmd.getInputStream()).toString();
                cmd.join(5, TimeUnit.SECONDS);
                if (cmd.getExitStatus() == 0) {
                    session.close();
                    ssh.disconnect();
                    return result;
                }
            } finally {
                session.close();
            }
        } finally {
            ssh.disconnect();
            ssh.close();
        }
        return null;
    }

}
