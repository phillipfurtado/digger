package com.github.phillipfurtado.digger;

import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;

public class Server {

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private final UndertowJaxrsServer serverInstance = new UndertowJaxrsServer();

    public Server(Integer port, String host) {
        Undertow.Builder serverBuilder = Undertow.builder().addHttpListener(port, host);
        
        serverInstance.start(serverBuilder);
    }

    public DeploymentInfo deployApplication(String appPath, Class<? extends Application> applicationClass) {
        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setInjectorFactoryClass("org.jboss.resteasy.cdi.CdiInjectorFactory");
        deployment.setApplicationClass(applicationClass.getName());
        return serverInstance.undertowDeployment(deployment, appPath);
    }

    public void deploy(DeploymentInfo deploymentInfo) throws ServletException {
        serverInstance.deploy(deploymentInfo);
    }

    public static void main(String[] args) throws ServletException {
        Server myServer = new Server(8080, "0.0.0.0");
        DeploymentInfo di = myServer.deployApplication("/api", DiggerApplication.class)
                .setClassLoader(Server.class.getClassLoader()).setContextPath("/digger").setDeploymentName("Digger")
                .addListeners(Servlets.listener(org.jboss.weld.environment.servlet.Listener.class));
        
        myServer.deploy(di);

        logger.info("Digger API running...");
    }

}