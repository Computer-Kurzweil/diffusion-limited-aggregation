package org.woehlke.simulation.diffusion.limited.aggregation.view;

import org.woehlke.simulation.diffusion.limited.aggregation.model.*;
import org.woehlke.simulation.diffusion.limited.aggregation.model.Point;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/diffusion-limited-aggregation/
 * @author Thomas Woehlke
 * Date: 05.02.2006
 * Time: 00:51:51
 */
public class WorldCanvas extends JComponent {


    private Particles particles;
    private Point worldDimensions;

    private final Color MEDIUM = Color.BLACK;
    private final Color PARTICLES = Color.BLUE;

    public WorldCanvas(Point worldDimensions, Particles particles) {
        this.worldDimensions = worldDimensions;
        this.setBackground(MEDIUM);
        this.setSize(this.worldDimensions.getX(), this.worldDimensions.getY());
        this.particles=particles;
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        int width = worldDimensions.getX();
        int height = worldDimensions.getY();
        g.setColor(MEDIUM);
        g.fillRect(0,0,width,height);
        g.setColor(PARTICLES);
        for(Point pixel:particles.getParticles()){
            g.drawLine(pixel.getX(),pixel.getY(),pixel.getX(),pixel.getY());
        }
        for(int y=0;y<worldDimensions.getY();y++){
            for(int x=0;x<worldDimensions.getX();x++){
                int age = particles.getDendriteColor(x,y);
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

    public Point getWorldDimensions() {
        return worldDimensions;
    }
}
