package org.woehlke.computer.kurzweil.dla.model;

import lombok.Getter;
import org.woehlke.computer.kurzweil.dla.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.dla.model.dendrite.Dendrite;
import org.woehlke.computer.kurzweil.dla.model.dendrite.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
 * Date: 27.08.13
 * Time: 14:57
 */
public class DiffusionLimitedAggregationModel implements Serializable {

    static final long serialVersionUID = 242L;

    @Getter
    private ComputerKurzweilProperties config;

    @Getter
    private Point worldDimensions;

    @Getter
    private List<Point> particles = new ArrayList<Point>();

    private Random random;

    private Dendrite dendrite;

    public DiffusionLimitedAggregationModel(ComputerKurzweilProperties config) {
        this.config = config;
        int scale = this.config.getDla().getView().getScale();
        int width = scale * this.config.getDla().getView().getWidth() ;
        int height = scale * this.config.getDla().getView().getHeight();
        this.worldDimensions = new Point(width,height);
        random = new Random(new Date().getTime());
        for(int i=0; i<config.getDla().getControl().getNumberOfParticles();i++){
            int x = random.nextInt(worldDimensions.getX());
            int y = random.nextInt(worldDimensions.getY());
            particles.add(new Point(x>=0?x:-x,y>=0?y:-y));
        }
        this.dendrite = new Dendrite(worldDimensions);
    }

    public void move() {
        List<Point> newParticles = new ArrayList<Point>();
        for(Point particle:particles){
            int x = particle.getX()+worldDimensions.getX();
            int y = particle.getY()+worldDimensions.getY();
            int direction = random.nextInt(4);
            switch (direction>=0?direction:-direction){
                case 0: y--; break;
                case 1: x++; break;
                case 2: y++; break;
                case 3: x--; break;
            }
            x %= worldDimensions.getX();
            y %= worldDimensions.getY();
            particle.setX(x);
            particle.setY(y);
            if(!dendrite.hasDendriteNeighbour(particle)){
                newParticles.add(particle);
            }
        }
        particles=newParticles;
    }

    public int getDendriteColor(int x, int y) {
        return dendrite.getAgeForPixel(x,y);
    }
}
