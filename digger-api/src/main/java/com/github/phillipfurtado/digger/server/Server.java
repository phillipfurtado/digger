package com.github.phillipfurtado.digger.server;

import java.util.logging.Logger;

import javax.servlet.ServletException;

import com.github.phillipfurtado.digger.DiggerApplication;

public class Server {

    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private static final String HOST = "localhost";
    private static final Integer PORT = 8080;
    private static final String CTX_PATH = "/digger";
    private static final String APP_NAME = "digger";
    private static final String APP_PATH = "/api";

    private Server() {
    }

    public static void main(String[] args) throws ServletException {
        new EmbeddedServer(HOST, PORT).contextPath(CTX_PATH).deploymentName(APP_NAME).appPath(APP_PATH)
                .resourcesClass(DiggerApplication.class).start();

        logger.info("Digger API running...");
    }

}