package io.github.therealmone.DiffieHellman.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;

public class Client {
    private final static Logger logger = LogManager.getLogger(Client.class);
    private final static Random random = new Random();
    private final String name;
    private final double modulus;
    private final double base;
    private final double secret;
    private double Key;

    public Client(final double modulus, final double base, final String name) {
        this.name = name;
        this.modulus = modulus;
        this.base = base;
        this.secret = random.nextInt(10);
        logger.info("Client \"{}\" generated: modulus = {}, base = {}, secret = {}", name, modulus, base, secret);
    }

    public double getA() {
        final double A = Math.pow(base, secret) % modulus;
        logger.info("Client \"{}\" calculated A: {}", name, A);
        return A;
    }

    public void calcKey(final double A) {
        logger.info("Client \"{}\" got A: {}", name, A);
        this.Key =  Math.pow(A, secret) % modulus;
        logger.info("Client \"{}\" calculated a Key: {}", name, Key);
    }

    public String getName() {
        return name;
    }

    public double getKey() {
        return Key;
    }
}
