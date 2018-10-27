package io.github.therealmone.DiffieHellman;

import io.github.therealmone.DiffieHellman.model.Client;
import io.github.therealmone.DiffieHellman.model.Listener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PseudoNetwork {
    private final static Logger logger = LogManager.getLogger(PseudoNetwork.class);

    public static void main(String[] args) {
        final Client a = new Client(23, 5, "Alice");
        final Client b = new Client(23, 5, "Bob");
        final Listener listener = new Listener("listener");

        b.calcKey(listener.listen("A1", a.getA()));
        a.calcKey(listener.listen("A2", b.getA()));

        logger.info("Client {} has key : {}", a.getName(), a.getKey());
        logger.info("Client {} has key : {}", b.getName(), b.getKey());
        logger.info("Listener {} has values : {}", listener.getName(), listener.getValues().toString());
    }
}
