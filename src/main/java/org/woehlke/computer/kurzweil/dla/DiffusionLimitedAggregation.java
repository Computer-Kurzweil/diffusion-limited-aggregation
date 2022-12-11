package org.woehlke.computer.kurzweil.dla;

import java.io.Serializable;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html">Blog Arrticle</a>
 * @see <a href="https://java.woehlke.org/diffusion-limited-aggregation">Maven Project Page</a>
 * @author Thomas Woehlke
 */
public interface DiffusionLimitedAggregation extends Serializable {

    long mySerialVersionUID = 242L;

    String TITLE = "diffusion limited aggregation (DLA)";

    int THREAD_SLEEP_TIME = 50;

    int NUMBER_OF_PARTICLES = 30000;
}
