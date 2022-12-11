package org.woehlke.computer.kurzweil.dla.model;

import org.woehlke.computer.kurzweil.dla.DiffusionLimitedAggregation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2022 Thomas Woehlke.
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html">Blog Arrticle</a>
 * @see <a href="https://java.woehlke.org/diffusion-limited-aggregation">Maven Project Page</a>
 * @author Thomas Woehlke
 *
 * Date: 27.08.13
 * Time: 14:57
 */
public class Particles implements DiffusionLimitedAggregation {

    static final long serialVersionUID = mySerialVersionUID;


    private Point worldDimensions;

    private List<Point> particles = new ArrayList<Point>();

    private Random random;

    private Dendrite dendrite;

    public Particles(Point worldDimensions) {
        this.worldDimensions=worldDimensions;
        random = new Random(new Date().getTime());
        for(int i=0; i<NUMBER_OF_PARTICLES;i++){
            int x = random.nextInt(worldDimensions.getX());
            int y = random.nextInt(worldDimensions.getY());
            particles.add(new Point(x>=0?x:-x,y>=0?y:-y));
        }
        this.dendrite = new Dendrite(worldDimensions);
    }

    public List<Point> getParticles() {
        return particles;
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
