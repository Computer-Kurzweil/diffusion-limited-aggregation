package org.woehlke.simulation.diffusion.limited.aggregation.view;

import org.woehlke.simulation.diffusion.limited.aggregation.control.ControllerThread;
import org.woehlke.simulation.diffusion.limited.aggregation.model.Particles;
import org.woehlke.simulation.diffusion.limited.aggregation.model.Point;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/diffusion-limited-aggregation/
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:33:14
 */
public class DlaApplet extends JApplet implements ImageObserver, MenuContainer, Serializable, Accessible {

    private Label title = new Label("diffusion limited aggregation (DLA)");
    private ControllerThread controllerThread;
    private WorldCanvas canvas;
    private Particles particles;

    public void init() {
        int scale = 2;
        int width = 320 * scale;
        int height = 234 * scale;
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        Point worldDimensions = new Point(width,height);
        particles = new Particles(worldDimensions);
        canvas = new WorldCanvas(worldDimensions,particles);
        this.add(canvas, BorderLayout.CENTER);
        controllerThread = new ControllerThread(canvas,particles);
        controllerThread.start();
    }

    public void destroy() {
    }

    public void stop() {
    }

    public Point getCanvasDimensions() {
        return canvas.getWorldDimensions();
    }
}
