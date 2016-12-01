/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.model;

/**
 *
 * @author almehairbi
 */
public class Background extends GameObject implements Runnable
{
        
    private int counter;
    private Thread t = null;
    private boolean  run = false ;
    private static final int X_CHANGE = 5;
    private static final int Y_CHANGE = 5;
    
    public void shake()
    {
        if(t == null || !t.isAlive())
        {   
            t = new Thread(this);
            run = true;
            t.start();
        }
    }
    
    
    @Override
    public void update() {
            
        
    }

    @Override
    public void run() {
            
        while (run) {

            x += X_CHANGE;
            y += Y_CHANGE;
            sleep(60);
            
            x -= X_CHANGE;
            y -= Y_CHANGE;
            sleep(60);
            
            if(counter > 3)
            {
                counter = 0;
                run = false;
                t = null;
            }
        }
        
        
    }

    private void sleep(int i) {
            
        try
        {
            Thread.sleep(i);
        }catch(InterruptedException ie)
        {
            
        }
    }
    
    
    
}
