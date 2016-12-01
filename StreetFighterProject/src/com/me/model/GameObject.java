package com.me.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.JPanel;

public abstract class GameObject 
{
   protected Image image;
   protected double x;
   protected double y;
   protected double rotation;
   protected double scale;
   protected boolean alive;
   protected double rotationAmount;
   protected double movingSpeed;
   protected double width;
   protected double height;
   protected Graphics2D g2d;
   protected AffineTransform identity = new AffineTransform();
   protected  Color color;
   protected JPanel panel;
   
   
   
   public GameObject()
   {
      image = null;
      x = 0;
      y = 0;
      rotation = 0;
      scale = 1;
      alive = true;
      rotationAmount = 0;
      movingSpeed = 0;
      width = 0;
      height = 0;
   }
   
   public void loadImage(String file)
   {
       URL url = getClass().getResource(file);
       image = Toolkit.getDefaultToolkit().getImage(url);
       
       while(image.getWidth(panel) <= 0);
       
           width = image.getWidth(panel);
           height = image.getHeight(panel);
       
   }
  
   
   public Rectangle getBounds()
   {
       Rectangle r = new Rectangle((int)width,(int)height);
       return r;
   }
   
   public Rectangle getTransformedBounds()
   {
       Rectangle r = getBounds();
       r.translate((int)(x - width / 2), (int)(y - height / 2));
       return r;
       
   }
    public void draw()
   {
       if(isAlive())
       {
           g2d.setTransform(identity);
           g2d.translate(x, y);
           g2d.rotate(Math.toRadians(rotation));
           g2d.translate(-getWidth() /2, -getHeight()/2);
           g2d.drawImage(image, 0, 0,panel);
           g2d.setColor(color);
           
       }
   }
   public abstract void update();
   
   
   public void move()
   {
      double faceAngle = Math.toRadians(rotation -90);
      double deltaX = movingSpeed * Math.cos(faceAngle);
      double deltaY = movingSpeed * Math.sin(faceAngle);
      
      x += deltaX;
      y += deltaY;
   }
   
   
   
   
 
   
   public void rotateRight()
   {
      rotation += rotationAmount;
   }
   
   public void rotateLeft()
   {
      rotation -= rotationAmount;
   }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getRotationAmount() {
        return rotationAmount;
    }

    public void setRotationAmount(double rotationAmount) {
        this.rotationAmount = rotationAmount;
    }

    public double getMovingSpeed() {
        return movingSpeed;
    }

    public void setMovingSpeed(double movingSpeed) {
        this.movingSpeed = movingSpeed;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public AffineTransform getIdentity() {
        return identity;
    }

    public void setIdentity(AffineTransform identity) {
        this.identity = identity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

  
   
}
