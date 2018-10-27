package io.github.therealmone.DiffieHellman.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Listener {
    private final static Logger logger = LogManager.getLogger(Listener.class);
    private final String name;
    private Map<String, Double> values = new HashMap<>();

    public Listener(final String name) {
        this.name = name;
    }

    public double listen(final String varName, final Double value) {
        logger.info("Listener \"{}\" got {} = {}", name, varName, value);
        values.put(varName, value);
        return value;
    }

    public Map<String, Double> getValues() {
        return values;
    }

    public String getName() {
        return name;
    }
}
