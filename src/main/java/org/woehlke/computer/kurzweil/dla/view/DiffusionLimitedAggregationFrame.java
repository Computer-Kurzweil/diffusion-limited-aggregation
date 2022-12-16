package org.woehlke.computer.kurzweil.dla.view;

import org.woehlke.computer.kurzweil.dla.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.dla.control.ControllerThread;
import org.woehlke.computer.kurzweil.dla.model.DiffusionLimitedAggregationModel;
import org.woehlke.computer.kurzweil.dla.model.dendrite.Point;
import org.woehlke.computer.kurzweil.dla.view.canvas.WorldCanvas;

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
        WindowListener{

    static final long serialVersionUID = 242L;

    private Label title;
    private ControllerThread controller;
    private WorldCanvas canvas;
    private DiffusionLimitedAggregationModel model;
    private ComputerKurzweilProperties config;

    public DiffusionLimitedAggregationFrame(ComputerKurzweilProperties config) {
        super(config.getDla().getView().getTitle());
        title = new Label(config.getDla().getView().getTitle());
        this.config = config;
        this.model = new DiffusionLimitedAggregationModel(config);
        this.canvas = new WorldCanvas(model);
        this.controller = new ControllerThread(canvas, model);
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);
        //this.add("Center", canvas);
        pack();
        showMe();
        addWindowListener(this);
    }

    public void start() {
        controller.start();
    }

    public void showMe(){
        setBounds(100, 100, this.getCanvasDimensions().getX(), this.getCanvasDimensions().getY() + 30);
        setVisible(true);
        toFront();
    }

    public void windowOpened(WindowEvent e) {
        showMe();
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
        showMe();
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
