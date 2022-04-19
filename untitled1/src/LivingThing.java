import java.awt.*;

abstract class LivingThing {
public int size;
public int age;
public boolean alive;
public boolean animalAlive=true;
public int eaten;
public int speed;
public int deathTimer;
public Color color;
public int targetIndex;
public boolean preyFound;
public boolean locked;
public int prototypeNum;
public int targetId;
public int x;
public int y;
public int getSize(){
    return size;
}
public boolean getState(){
    return alive;
}
public int getEaten(){
    return eaten;
}
public int getSpeed(){
    return speed;
}
public void checkCollision(LivingThing g){
}
public void setSpeed(int speed){
    this.speed=speed;
}
public void revive(){
    if(this.deathTimer==14){
        alive=true;
        this.color=Color.GREEN;
    }
    else{
        this.age=this.age+1;
    }

}
public void duel(LivingThing g){

}
public void move(int target){

}
public void eaten(){
    alive=false;
}
public void checkAge(){

}
public void checkStatus(){
}
public void eat(){
    this.size=size+1;
}
public void move(){

}
public void setTarget(){

}
    public void setX(){
        Double variable=Math.random();
        int x;
        if(variable>0&variable<0.06){
            x=0;
        }
        else if(variable>0.06&variable<0.12){
            x=20;
        }
        else if(variable>0.12&variable<0.18){
            x=40;
        }
        else if(variable>0.18&variable<0.24){
           x=60;
        }
        else if(variable>0.24&variable<0.30){
            x=80;
        }
        else if(variable>0.30&variable<0.36){
            x=100;
        }
        else if(variable>0.36&variable<0.42){
            x=120;
        }
        else if(variable>0.42&variable<0.48){
            x=140;
        }
        else if(variable>0.48&variable<0.54){
            x=160;
        }
        else if(variable>0.54&variable<0.60){
            x=180;
        }
        else if(variable>0.60&variable<0.66){
            x=200;
        }
        else if(variable>0.66&variable<0.72){
            x=220;
        }
        else if(variable>0.72&variable<0.78){
            x=240;
        }
        else if(variable>0.78&variable<0.84){
            x=260;
        }
        else if(variable>0.84&variable<0.90){
            x=280;
        }
        else if(variable>0.90&variable<0.96){
            x=300;
        }
        else{
            x=320;
        }
        this.x=x;
    }
    public void setY(){
        Double variable=Math.random();
        int x;
        if(variable>0&variable<0.06){
            x=0;
        }
        else if(variable>0.06&variable<0.12){
            x=20;
        }
        else if(variable>0.12&variable<0.18){
            x=40;
        }
        else if(variable>0.18&variable<0.24){
            x=60;
        }
        else if(variable>0.24&variable<0.30){
            x=80;
        }
        else if(variable>0.30&variable<0.36){
            x=100;
        }
        else if(variable>0.36&variable<0.42){
            x=120;
        }
        else if(variable>0.42&variable<0.48){
            x=140;
        }
        else if(variable>0.48&variable<0.54){
            x=160;
        }
        else if(variable>0.54&variable<0.60){
            x=180;
        }
        else if(variable>0.60&variable<0.66){
            x=200;
        }
        else if(variable>0.66&variable<0.72){
            x=220;
        }
        else if(variable>0.72&variable<0.78){
            x=240;
        }
        else if(variable>0.78&variable<0.84){
            x=260;
        }
        else if(variable>0.84&variable<0.90){
            x=280;
        }
        else if(variable>0.90&variable<0.96){
            x=300;
        }
        else{
            x=320;
        }
        this.y=x;
    }
}
