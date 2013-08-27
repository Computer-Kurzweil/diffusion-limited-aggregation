package org.woehlke.simulation.diffusion.limited.aggregation.control;

import org.woehlke.simulation.diffusion.limited.aggregation.model.Particles;
import org.woehlke.simulation.diffusion.limited.aggregation.view.WorldCanvas;

/**
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/diffusion-limited-aggregation/
 * @author Thomas Woehlke
 * Date: 05.02.2006
 * Time: 00:36:20
 */
public class ControllerThread extends Thread
        implements Runnable {

    private Particles particles;
    private WorldCanvas canvas;

    private int THREAD_SLEEP_TIME = 75;
    private Boolean goOn;

    public ControllerThread(WorldCanvas canvas, Particles particles) {
        goOn = Boolean.TRUE;
        this.canvas=canvas;
        this.particles=particles;
    }

    public void run() {
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            particles.move();
            canvas.repaint();
            try { sleep(THREAD_SLEEP_TIME); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        while (doIt);
    }

    public void exit() {
        synchronized (goOn) {
            goOn = Boolean.FALSE;
        }
    }


}
