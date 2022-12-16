package org.woehlke.computer.kurzweil.dla.control;

import org.woehlke.computer.kurzweil.dla.model.DiffusionLimitedAggregationModel;
import org.woehlke.computer.kurzweil.dla.view.DiffusionLimitedAggregationFrame;
import org.woehlke.computer.kurzweil.dla.view.canvas.WorldCanvas;

import java.io.Serializable;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html">Blog Arrticle</a>
 * @see <a href="https://java.woehlke.org/diffusion-limited-aggregation">Maven Project Page</a>
 * @see <a href="https://github.com/Computer-Kurzweil/diffusion-limited-aggregation">Github</a>
 *
 * Date: 05.02.2006
 * Time: 00:36:20
 */
public class ControllerThread extends Thread
        implements Runnable, Serializable {

    static final long serialVersionUID = 242L;

    private final DiffusionLimitedAggregationFrame tab;

    private volatile DiffusionLimitedAggregationModel model;
    private volatile WorldCanvas canvas;

    private final int threadSleepTime;

    private Boolean goOn;

    public ControllerThread(DiffusionLimitedAggregationFrame tab) {
        this.tab = tab;
        this.goOn = Boolean.TRUE;
        this.canvas = this.tab.getCanvas();
        this.model = this.tab.getModel();
        this.threadSleepTime = model.getConfig().getDla().getControl().getThreadSleepTime();
    }

    public void run() {
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            this.model.move();
            this.canvas.repaint();
            this.tab.repaint();
            try { sleep(this.threadSleepTime); }
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
