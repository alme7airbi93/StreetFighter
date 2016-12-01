package com.me.model;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpaceGame extends JPanel {

    public static  int width;
    public static  int height;

    private List<GameObject> gameObjects = new ArrayList<>();
    private Background bg = new Background();
    private boolean showBounds ;
    Thread t = null;
    
    private BufferedImage db;
    private Graphics2D g2d;
    private AffineTransform identity = new AffineTransform();

    public static boolean UP_PRESSED = false;
    public static boolean LEFT_PRESSED = false;
    public static boolean RIGHT_PRESSED = false;
    public static boolean SPACE_PRESSED = false;
    private boolean run = false;
       
 

    public SpaceGame(int height, int width) {
        this.width = width;
        this.height = height;
        this.db  = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      
        initGame();
    }

    private void initGame() {
        setFocusable(true);
        requestFocusInWindow();
        setPreferredSize(new Dimension(width, height));
        
        g2d = db.createGraphics();
        /*g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);*/
        
       

        bg.setPanel(this);
        bg.setG2d(g2d);
//        bg.loadImage("/ae/coder/images/bluespace.png");
        bg.setX(width / 2);
        bg.setY(height / 2);
        gameObjects.add(bg);

        showBounds = false;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPresses(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyReleases(e);
            }
        });
        
        firstScreen();
        pauseGame();
        
        
         t = new Thread(new Runnable() {
            @Override
            public void run() {
                runGame();
            }
        });
        t.start();
    }

    private void handleKeyPresses(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                UP_PRESSED = true;
                break;
            case KeyEvent.VK_LEFT:
                LEFT_PRESSED = true;
                break;
            case KeyEvent.VK_RIGHT:
                RIGHT_PRESSED = true;
                break;
        }
    }

    private void handleKeyReleases(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                UP_PRESSED = false;
                break;
            case KeyEvent.VK_LEFT:
                LEFT_PRESSED = false;
                break;
            case KeyEvent.VK_RIGHT:
                RIGHT_PRESSED = false;
                break;
            case KeyEvent.VK_SPACE:
                SPACE_PRESSED = true;
                break;
        }
    }

    private void runGame() {
        while (run) {
            updateGame();
            renderGame();
            repaint();
            sleep(20);
        }
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private void updateGame() {
        if (SPACE_PRESSED) {
       
        }
        for (GameObject go : gameObjects) {
            go.update();
        }

        
      
    }

  

    private void renderGame() {

        for (GameObject go : gameObjects) {
            go.draw();
        }

    }

  

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(db, 0, 0, this);
    }
    
     private void firstScreen() {
            
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        FontMetrics fm = g2d.getFontMetrics();
        
        int width = fm.stringWidth("ENTER: Start Game");
        int height = fm.getHeight();
        g2d.drawString("Enter: Start Game", SpaceGame.width/2 - SpaceGame.width/2, SpaceGame.height/2 - SpaceGame.height/2);
        
        width = fm.stringWidth("ESCAPE: Exit Game");
        g2d.drawString("ESCAPE: Exit Game", SpaceGame.width/2 - SpaceGame.width/2, SpaceGame.height/2 - SpaceGame.height/2);

    }

    private void pauseGame() {

            run = false;
    }

    public  int getWidth() {
        return width;
    }

    public  int getHeight() {
        return height;
    }

    public static void setWidth(int width) {
        SpaceGame.width = width;
    }

    public static void setHeight(int height) {
        SpaceGame.height = height;
    }
    


    public static void main(String[] args) {
        JFrame game = new JFrame("Space Game");
        game.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpaceGame spaceGame = new SpaceGame(game.getWidth(),game.getHeight());
        game.pack();
        game.setLocationRelativeTo(null);
        
        
        
        game.add(spaceGame,BorderLayout.CENTER);
        setWidth(game.getWidth());
        setHeight(game.getHeight());
        game.setVisible(true);
       
    }

   

}
