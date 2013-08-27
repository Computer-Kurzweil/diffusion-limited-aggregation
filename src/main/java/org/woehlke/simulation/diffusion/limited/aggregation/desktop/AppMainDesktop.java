package org.woehlke.simulation.diffusion.limited.aggregation.desktop;

import org.woehlke.simulation.diffusion.limited.aggregation.view.AppMainFrame;

/**
 * (C) 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 * @author Thomas Woehlke
 */
public class AppMainDesktop {

    private AppMainDesktop() { }

    /**
     * Starting the App.
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        AppMainFrame simGen = new AppMainFrame();
    }
}
