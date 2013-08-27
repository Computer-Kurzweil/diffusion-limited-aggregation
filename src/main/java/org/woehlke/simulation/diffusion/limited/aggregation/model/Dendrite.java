package org.woehlke.simulation.diffusion.limited.aggregation.model;

/**
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/diffusion-limited-aggregation/
 * @author Thomas Woehlke
 * Date: 27.08.13
 * Time: 16:56
 */
public class Dendrite {

    private int worldMap[][];
    private Point dimensions;
    private int age=1;

    public Dendrite(Point dimensions) {
        this.dimensions = dimensions;
        worldMap = new int[dimensions.getX()][dimensions.getY()];
        int x = dimensions.getX() / 2;
        int y = dimensions.getY() / 2;
        worldMap[x][y]=age;
        age++;
    }

    public boolean hasDendriteNeighbour(Point pixel){
        if(worldMap[pixel.getX()][pixel.getY()]==0){
            Point[] neighbours=pixel.getNeighbourhood(dimensions);
            for(Point neighbour:neighbours){
               if(worldMap[neighbour.getX()][neighbour.getY()]>0){
                   worldMap[pixel.getX()][pixel.getY()]=age;
                   age++;
                   return true;
               }
            }
            return false;
        } else {
           return true;
        }
    }

    public int getAgeForPixel(int x,int y){
        return worldMap[x][y];
    }
}
