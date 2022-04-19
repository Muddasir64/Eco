import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Plant extends LivingThing {
    public static ArrayList<Plant> PlantList = new ArrayList<>();

    public static void Initialize() {
double d=0;
        PlantList.clear();
        for (int a = 0; a < 17; a++) {
            for (int b = 0; b < 17; b++) {
                Plant plant = new Plant();
                plant.x = a * 20;
                plant.y = b * 20;
                plant.age=0;
                d=Math.random();
                if (d >= 0.4) {
                    plant.color = Color.GREEN;
                    plant.alive = true;
                }
              else{
                    plant.color = Color.GRAY;
                    plant.alive = false;
                }
                plant.speed = 0;
                plant.size=20;
                plant.deathTimer = 0;
                PlantList.add(plant);


            }

        }

    }
    public void eaten(){
        this.alive=false;
        this.color=Color.GRAY;
        this.deathTimer=0;
        this.size=20;

    }

    public void checkStatus(){
        if(!alive){
            this.deathTimer=deathTimer+1;
        }
        if(this.age==10){
            this.size=this.size+1;
            this.age=0;
        }
        revive();
    }
}

