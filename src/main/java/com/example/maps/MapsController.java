package com.example.maps;

import com.example.maps.model.Avatar;
import com.example.maps.model.Vector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;


public class MapsController {

    private GraphicsContext gc;

    private Avatar avatar;
    private Avatar Wall;


    private Boolean isRuning = true;

    //Estados de las teclas
    boolean Wpressed = false;
    boolean Apressed = false;
    boolean Spressed = false;
    boolean Dpressed = false;

    boolean Uppressed = false;
    boolean Leftpressed = false;
    boolean Downpressed = false;
    boolean Rightpressed = false;
    @FXML
    private GridPane matriz;

    @FXML
    private ImageView image00;

    @FXML
    private ImageView image10;

    @FXML
    private ImageView image20;

    @FXML
    private ImageView image30;

    @FXML
    private ImageView image01;

    @FXML
    private ImageView image11;

    @FXML
    private ImageView image21;

    @FXML
    private ImageView image31;

    @FXML
    private ImageView image02;

    @FXML
    private ImageView image12;

    @FXML
    private ImageView image22;

    @FXML
    private ImageView image32;

    @FXML
    private ImageView image03;

    @FXML
    private ImageView image13;

    @FXML
    private ImageView image23;

    @FXML
    private ImageView image33;

    @FXML
    private Canvas canvas;

    private Vector pos;//new
    private Vector direction; //new

    public void initialize() {
        String uriW = "file:"+ MapsController.class.getResource("wall.png").getPath();
        String uriS = "file:"+ MapsController.class.getResource("space.png").getPath();
        Image wall = new Image(uriW);
        Image space = new Image(uriS);

        image12.setImage(wall);



        image22.setImage(wall);

        image30.setImage(wall);
        image31.setImage(wall);
        image32.setImage(wall);


        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        canvas.setOnMouseClicked(this::onMouseClicked);

        canvas.setOnKeyPressed(this ::inKeyPressed);

        canvas.setOnKeyReleased(this ::onKeyReleased);
        avatar = new Avatar(canvas); // se crea el avatar
        Wall = new Avatar(canvas);
        draw();


        /// 3,0 - 3,1 - 3,2

        /// 1,2 - 1,2
    }


    private void onKeyReleased(KeyEvent keyEvent) {

        if(keyEvent.getCode()== KeyCode.W){
            Wpressed = false;
        }
        if(keyEvent.getCode()== KeyCode.A){
            Apressed = false;
        }
        if(keyEvent.getCode()== KeyCode.S){
            Spressed = false;
        }
        if(keyEvent.getCode()== KeyCode.D){
            Dpressed = false;
        }
        if(keyEvent.getCode()== KeyCode.UP){
            Uppressed = false;
        }
        if(keyEvent.getCode()== KeyCode.LEFT){
            Leftpressed = false;
        }
        if(keyEvent.getCode()== KeyCode.DOWN){
            Downpressed = false;
        }
        if(keyEvent.getCode()== KeyCode.RIGHT){
            Rightpressed = false;
        }

    }

    private void inKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());

        if(keyEvent.getCode()== KeyCode.W){
            Wpressed = true;
        }
        if(keyEvent.getCode()== KeyCode.A){
            Apressed = true;
        }
        if(keyEvent.getCode()== KeyCode.S){
            Spressed = true;
        }
        if(keyEvent.getCode()== KeyCode.D){
            Dpressed = true;
        }

        if(keyEvent.getCode()== KeyCode.UP){
            Uppressed = true;
        }
        if(keyEvent.getCode()== KeyCode.LEFT){
            Leftpressed = true;
        }
        if(keyEvent.getCode()== KeyCode.DOWN){
            Downpressed = true;
        }
        if(keyEvent.getCode()== KeyCode.RIGHT){
            Rightpressed = true;
        }
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        avatar.setPosition(mouseEvent.getX(),mouseEvent.getY());
    }

    public void draw(){
        new Thread(
                ()->{
                    while(isRuning){
                        //Dibujo
                        Platform.runLater(
                                ()->{
                                    gc.setFill(Color.BLACK);
                                    gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
                                    avatar.draw();
                                    avatar.draw1();
                                    Wall.drawWall(image12.getLayoutX(),image12.getLayoutY(), image12.getFitWidth(),image12.getFitHeight());
                                    Wall.drawWall(image22.getLayoutX(),image22.getLayoutY(), image22.getFitWidth(),image22.getFitHeight());
                                    Wall.drawWall(image30.getLayoutX(),image30.getLayoutY(), image30.getFitWidth(),image30.getFitHeight());
                                    Wall.drawWall(image31.getLayoutX(),image31.getLayoutY(), image31.getFitWidth(),image31.getFitHeight());
                                    Wall.drawWall(image32.getLayoutX(),image32.getLayoutY(), image32.getFitWidth(),image32.getFitHeight());
                                    if(Wpressed){

                                        //avatar.moveVertical(-3);
                                        avatar.moveForward();
                                    }
                                    if(Apressed){
                                        //avatar.moveHorizontal(-3);
                                        avatar.changeAngle(-10);
                                    }
                                    if(Spressed){
                                        //avatar.moveVertical(3);
                                        avatar.moveReverse();
                                    }
                                    if(Dpressed){
                                        //avatar.moveHorizontal(3);
                                        avatar.changeAngle(10);
                                    }
                                    if(Uppressed){
                                        //avatar.moveVertical(-3);
                                        avatar.moveForward2();
                                    }
                                    if(Leftpressed){
                                        //avatar.moveHorizontal(-3);
                                        avatar.changeAngle2(-10);
                                    }
                                    if(Downpressed){
                                        //avatar.moveVertical(3);
                                        avatar.moveReverse2();
                                    }
                                    if(Rightpressed){
                                        //avatar.moveHorizontal(3);
                                        avatar.changeAngle2(10);
                                    }
                                });
                        //sleep
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start(); //Se debe poner el start para que lo que este en draw funcione
    }
}
