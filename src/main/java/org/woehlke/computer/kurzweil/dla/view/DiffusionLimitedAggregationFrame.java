package org.woehlke.computer.kurzweil.dla.view;

import org.woehlke.computer.kurzweil.dla.config.DiffusionLimitedAggregation;
import org.woehlke.computer.kurzweil.dla.control.ControllerThread;
import org.woehlke.computer.kurzweil.dla.model.Particles;
import org.woehlke.computer.kurzweil.dla.model.Point;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
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
 * Date: 04.02.2006
 * Time: 18:47:46
 */
public class DiffusionLimitedAggregationFrame extends JFrame implements ImageObserver,
        MenuContainer,
        Serializable,
        Accessible,
        WindowListener, DiffusionLimitedAggregation {

    static final long serialVersionUID = mySerialVersionUID;

    private Label title = new Label(TITLE);
    private ControllerThread controllerThread;
    private WorldCanvas canvas;
    private Particles particles;

    public DiffusionLimitedAggregationFrame() {
        super(TITLE);
        int scale = 2;
        int width = 320 * scale;
        int height = 234 * scale;
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        org.woehlke.computer.kurzweil.dla.model.Point worldDimensions = new Point(width,height);
        particles = new Particles(worldDimensions);
        canvas = new WorldCanvas(worldDimensions,particles);
        this.add(canvas, BorderLayout.CENTER);
        controllerThread = new ControllerThread(canvas,particles);
        controllerThread.start();
        add("Center", canvas);
        setBounds(100, 100, this.getCanvasDimensions().getX(), this.getCanvasDimensions().getY() + 30);
        pack();
        setVisible(true);
        toFront();
        addWindowListener(this);
    }

    public void windowOpened(WindowEvent e) {
        setBounds(100, 100, this.getCanvasDimensions().getX(), this.getCanvasDimensions().getY() + 30);
        setVisible(true);
        toFront();
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {
        setBounds(100, 100, this.getCanvasDimensions().getX(), this.getCanvasDimensions().getY() + 30);
        setVisible(true);
        toFront();
    }

    public void windowActivated(WindowEvent e) {
        toFront();
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public Point getCanvasDimensions() {
        return canvas.getWorldDimensions();
    }
}
