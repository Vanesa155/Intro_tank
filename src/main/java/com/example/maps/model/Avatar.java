package com.example.maps.model;

import com.example.maps.HelloApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Avatar {

    private Canvas canvas;
    private GraphicsContext gc;


    private Image tank;
    //private int x = 250;
    //private int y = 250;

    private Vector pos;//new
    private Vector direction; //new

    private Vector pos2;

    private Vector direction2;

    public Avatar(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+ HelloApplication.class.getResource("tank.png").getPath();
        tank = new Image(uri);
        pos = new Vector (100,100);
        pos2 = new Vector(200, 150);
        direction = new Vector(2,2);
        direction2 = new Vector(-2,-2);
     }

    public void draw(){

        gc.save();
        //gc.translate(x,y);
        gc.translate(pos.x, pos.y);
        //gc.rotate(45);
        gc.rotate(90+direction.getAngle());
        gc.drawImage(tank,-25,-25,50,50);
        gc.restore();
    }

    public void draw1(){
        gc.save();
        //gc.translate(x,y);
        gc.translate(pos2.x, pos2.y);
        //gc.rotate(45);
        gc.rotate(90+direction2.getAngle());
        gc.drawImage(tank,-25,-25,50,50);
        gc.restore();
    }
    public void drawWall(double x, double y, double w, double h){
        gc.setFill(Color.RED);
        gc.fillRect(x,y,w,h);

    }
    public void setPosition(double x, double y){
        pos.x=(int) x -25;
        pos.y=(int) y -25;
    }
    //new
    public void changeAngle(double a){
        double amp = direction.getAmplitude();
        double angle = direction.getAngle();
        angle += a;

        //x = a coseno de teta y y = a seno de teta
        direction.x = amp*Math.cos(Math.toRadians(angle));
        direction.y = amp*Math.sin(Math.toRadians(angle));
    }

    public void moveForward(){
        pos.x += direction.x;
        pos.y += direction.y;
    }

    public void moveReverse(){
        pos.x -= direction.x;
        pos.y -= direction.y;
    }
    public void changeAngle2(double a){
        double amp = direction2.getAmplitude();
        double angle = direction2.getAngle();
        angle += a;

        //x = a coseno de teta & y = a seno de teta
        direction2.x = amp*Math.cos(Math.toRadians(angle));
        direction2.y = amp*Math.sin(Math.toRadians(angle));
    }

    public void moveForward2(){
        pos2.x += direction2.x;
        pos2.y += direction2.y;
    }
    public void moveReverse2(){
        pos2.x -= direction2.x;
        pos2.y -= direction2.y;
    }
    public boolean overLaps(Avatar other) {
        boolean noOverlap = pos.x + 50 < other.pos.x || other.pos.x + 50 < pos.x || pos.y + 50 < other.pos.y || other.pos.y + 50 < pos.y;

        return !noOverlap;
    }
    //public void moveVertical(int i) {
    //    pos.y += i;
    //}

    //public void moveHorizontal(int i) {
    //    this.x += i;
    //}
}
