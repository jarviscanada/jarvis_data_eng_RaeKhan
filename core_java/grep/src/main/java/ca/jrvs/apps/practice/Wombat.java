package ca.jrvs.apps.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wombat {

    //    create Logger for this class
    private final Logger logger = LoggerFactory.getLogger(Wombat.class);
    private Integer t;
    private Integer oldT;

    public void setTemperature(Integer temperature) {
        oldT = t;
        t = temperature;
//        use logger to debug message
        logger.debug("Temperature set to {}. Old temp was {}.", t, oldT);

        if (temperature.intValue() > 50) {
            logger.info("Temperature has risen above 50 degrees.");
        }
    }
}

