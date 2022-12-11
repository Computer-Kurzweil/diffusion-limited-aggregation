package org.woehlke.computer.kurzweil.dla;

import org.woehlke.computer.kurzweil.dla.view.desktop.DiffusionLimitedAggregationFrame;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html
 * @author Thomas Woehlke
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
