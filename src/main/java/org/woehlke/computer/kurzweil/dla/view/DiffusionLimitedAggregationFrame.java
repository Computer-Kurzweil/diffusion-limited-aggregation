package org.woehlke.computer.kurzweil.dla.view;

import lombok.Getter;
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

import static javax.swing.SwingConstants.CENTER;

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
@Getter
public class DiffusionLimitedAggregationFrame extends JFrame implements ImageObserver,
        MenuContainer,
        Serializable,
        Accessible,
        WindowListener{

    static final long serialVersionUID = 242L;

    private final JLabel subtitle;
    private final JLabel copyright;
    private volatile ControllerThread controller;
    private volatile WorldCanvas canvas;
    private volatile DiffusionLimitedAggregationModel model;
    private volatile ComputerKurzweilProperties config;
    private volatile Rectangle rectangleBounds;
    private volatile Dimension dimensionSize;

    public DiffusionLimitedAggregationFrame(ComputerKurzweilProperties config) {
        super(config.getDla().getView().getTitle());
        this.config = config;
        this.subtitle = new JLabel(config.getDla().getView().getSubtitle(),CENTER);
        this.copyright = new JLabel(config.getDla().getView().getCopyright(),CENTER);
        this.model = new DiffusionLimitedAggregationModel(config);
        this.canvas = new WorldCanvas(model);
        this.controller = new ControllerThread(this);
        this.setLayout(new BorderLayout());
        this.add(subtitle, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);
        this.add(copyright, BorderLayout.SOUTH);
        showMeInit();
        addWindowListener(this);
    }

    public void start() {
        controller.start();
    }

    public void showMeInit() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width =  this.model.getWorldDimensions().getX();
        double height = this.model.getWorldDimensions().getY() + 60;
        double startX = (screenSize.getWidth() - width) / 2d;
        double startY = (screenSize.getHeight() - height) / 2d;
        int myheight = Double.valueOf(height).intValue();
        int mywidth = Double.valueOf(width).intValue();
        int mystartX = Double.valueOf(startX).intValue();
        int mystartY = Double.valueOf(startY).intValue();
        this.rectangleBounds = new Rectangle(mystartX, mystartY, mywidth, myheight);
        this.dimensionSize = new Dimension(mywidth, myheight);
        this.setBounds(this.rectangleBounds);
        this.setSize(this.dimensionSize);
        this.setPreferredSize(this.dimensionSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        toFront();
    }

    /**
     * Things to do, to show the Application Window again.
     */
    public void showMe() {
        this.pack();
        this.setBounds(this.rectangleBounds);
        this.setSize(this.dimensionSize);
        this.setPreferredSize(this.dimensionSize);
        this.setVisible(true);
        this.toFront();
    }

    public Rectangle getFrameBounds() {
        int height = this.model.getWorldDimensions().getY();
        int width = this.model.getWorldDimensions().getX();
        int TITLE_HEIGHT = 60;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double startX = (screenSize.getWidth() - height) / 2d;
        double startY = (screenSize.getHeight() - width) / 2d;
        int myheight = Double.valueOf(height).intValue() + TITLE_HEIGHT;
        int mywidth = Double.valueOf(width).intValue();
        int mystartX = Double.valueOf(startX).intValue();
        int mystartY = Double.valueOf(startY).intValue();
        return new Rectangle(mystartX, mystartY, mywidth, myheight);
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
