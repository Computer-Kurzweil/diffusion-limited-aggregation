package org.woehlke.computer.kurzweil.dla.view.canvas;

import lombok.Getter;
import org.woehlke.computer.kurzweil.dla.model.DiffusionLimitedAggregationModel;
import org.woehlke.computer.kurzweil.dla.model.dendrite.Point;

import javax.swing.*;
import java.awt.*;
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
 * Time: 00:51:51
 */
public class WorldCanvas extends JComponent implements Serializable {

    static final long serialVersionUID = 242L;

    @Getter
    private volatile DiffusionLimitedAggregationModel model;

    @Getter
    private final Point worldDimensions;

    private final Color MEDIUM = Color.BLACK;
    private final Color PARTICLES = Color.BLUE;

    public WorldCanvas( DiffusionLimitedAggregationModel model) {
        this.model = model;
        this.worldDimensions = model.getWorldDimensions();
        this.setBackground(MEDIUM);
        this.setSize(this.worldDimensions.getX(), this.worldDimensions.getY());
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        int width = worldDimensions.getX();
        int height = worldDimensions.getY();
        g.setColor(MEDIUM);
        g.fillRect(0,0,width,height);
        g.setColor(PARTICLES);
        for(Point pixel: model.getParticles()){
            g.drawLine(pixel.getX(),pixel.getY(),pixel.getX(),pixel.getY());
        }
        for(int y=0;y<worldDimensions.getY();y++){
            for(int x=0;x<worldDimensions.getX();x++){
                int age = model.getDendriteColor(x,y);
                if(age>0){
                    age /= 25;
                    int blue = (age / 256) % (256*256);
                    int green = (age % 256);
                    int red = 255;
                    Color ageColor = new Color(red,green,blue);
                    g.setColor(ageColor);
                    g.drawLine(x,y,x,y);
                }
            }
        }
    }

    public void update(Graphics g) {
        paint(g);
    }
}
