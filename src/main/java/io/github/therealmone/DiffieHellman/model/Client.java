package io.github.therealmone.DiffieHellman.model;

import io.github.therealmone.DiffieHellman.exception.InvalidValuesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    private final static Logger logger = LogManager.getLogger(Client.class);
    private final static Random random = new Random();
    private final String name;
    private final int modulus;
    private final int base;
    private final int secret;
    private double Key;

    public Client(final int modulus, final int base, final String name) throws InvalidValuesException {
        this.name = name;
        this.modulus = modulus;
        this.base = base;
        this.secret = random.nextInt(10) + 1;
        validate();
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

    private void validate() throws InvalidValuesException {
        final List<Double> values = new ArrayList<>();
        for (int i = 1; i < modulus; i++) {
            final double value = Math.pow(base, i) % modulus;
            if(values.contains(value)) {
                throw new InvalidValuesException("Values 'base ^ i mod modulus' (i = [1, modulus - 1]) must be different");
            }

            if(!(value >= 1 && value <= modulus - 1)) {
                throw new InvalidValuesException("Each value 'base ^ i mod modulus (i = [1, modulus - 1]) must be equal [1, modulus - 1]");
            }

            values.add(value);
        }

        if(!(base >= 1 && base <= modulus - 1)) {
            throw new InvalidValuesException("Base must be equal [1, modulus - 1]");
        }

        if(Math.pow(base, secret) % modulus == 1) {
            throw new InvalidValuesException("Base ^ secret mod modulus must not be equal 1");
        }
    }

    public String getName() {
        return name;
    }

    public double getKey() {
        return Key;
    }
}
