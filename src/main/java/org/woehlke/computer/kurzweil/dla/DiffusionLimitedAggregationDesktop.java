package org.woehlke.computer.kurzweil.dla;

import org.woehlke.computer.kurzweil.dla.view.DiffusionLimitedAggregationFrame;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html">Blog Arrticle</a>
 * @see <a href="https://java.woehlke.org/diffusion-limited-aggregation">Maven Project Page</a>
 * @see <a href="https://github.com/Computer-Kurzweil/diffusion-limited-aggregation">Github</a>
 */
public class DiffusionLimitedAggregationDesktop {

    private DiffusionLimitedAggregationDesktop() { }

    /**
     * Starting the App.
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        DiffusionLimitedAggregationFrame diffusionLimitedAggregation = new DiffusionLimitedAggregationFrame();
    }
}
