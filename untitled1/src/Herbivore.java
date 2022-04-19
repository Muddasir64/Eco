import java.awt.*;
import java.util.ArrayList;

public class Herbivore extends LivingThing {
    public static ArrayList<Herbivore> HerbivoreList = new ArrayList<>();
    public static int property = 1;

    public static void Initialize() {
        HerbivoreList.clear();
        while (HerbivoreList.size() != 12) {
            boolean Duplicate = false;
            Herbivore herb = new Herbivore();
            herb.setX();
            herb.setY();
            herb.color = Color.BLUE;
            herb.size = 3;
            herb.speed = 1;
            herb.alive = true;
            if (HerbivoreList.size() == 0) {
                herb.prototypeNum = property;
                HerbivoreList.add(herb);
                property = property + 1;
            } else {
                for (int x = 0; x < HerbivoreList.size(); x++) {
                    if (herb == HerbivoreList.get(x)) {
                        Duplicate = true;
                        break;
                    }
                }
                if (!Duplicate) {
                    herb.prototypeNum = property;
                    HerbivoreList.add(herb);
                    property = property + 1;
                }
            }
        }

    }

    public void setTarget() {
        int distanceX = 0;
        int distanceY = 0;
        int targetIndex = 0;
        int totalX = 0;
        int totalY = 0;
        int total = 0;
        int target = Integer.MAX_VALUE;
        for (int x = 0; x < Plant.PlantList.size(); x++) {
            if (Plant.PlantList.get(x).color == Color.GREEN) {

                distanceX = (Plant.PlantList.get(x).x - this.x);

                distanceY = (Plant.PlantList.get(x).y - this.y);
                total = Math.abs(distanceX) + Math.abs(distanceY);
                if (total < target) {
                    target = total;
                    totalX = distanceX;
                    totalY = distanceY;
                    targetIndex = x;
                }
            }
        }
        this.targetIndex = targetIndex;
        this.preyFound = true;

        if (totalX < 0) {
            this.x = this.x - 20;
            this.eaten = this.eaten + 1;
        } else if (totalX > 0) {
            this.x = this.x + 20;
            this.eaten = this.eaten + 1;
        }
        if (totalY < 0) {
            this.y = this.y - 20;
            this.eaten = this.eaten + 1;
        } else if (totalY > 0) {
            this.y = this.y + 20;
            this.eaten = this.eaten + 1;
        }
        if (target == 0) {

            this.eat();
            this.eaten = 0;
            Plant.PlantList.get(targetIndex).eaten();
            this.preyFound = false;

        }
    }

    public void followTarget() {
        int distanceX;
        int distanceY;
        int total;
        if (this.preyFound == true) {
            distanceX = Plant.PlantList.get(targetIndex).x - this.x;
            distanceY = Plant.PlantList.get(targetIndex).y - this.y;
            total = Math.abs(distanceX) + Math.abs(distanceY);

            if (distanceX < 0) {
                this.x = this.x - 20;
                this.eaten = this.eaten + 1;
            } else if (distanceX > 0) {
                this.x = this.x + 20;
                this.eaten = this.eaten + 1;
            }
            if (distanceY < 0) {
                this.y = this.y - 20;
                this.eaten = this.eaten + 1;
            } else if (distanceY > 0) {
                this.y = this.y + 20;
                this.eaten = this.eaten + 1;
            }
            if (total == 0) {
                this.eat();
                this.eaten = 0;
                Plant.PlantList.get(targetIndex).eaten();
                this.preyFound = false;

            }

        } else this.setTarget();
    }

    public void OffSpring() {
        if (this.size == 12) {
            for (int y = 0; y < 4; y++) {
                boolean Duplicate = false;
                Herbivore herb = new Herbivore();
                herb.setX();
                herb.setY();
                herb.color = Color.BLUE;
                herb.size = 10;
                herb.speed = 1;
                herb.alive = true;
                if (HerbivoreList.size() == 0) {
                    HerbivoreList.add(herb);
                } else {
                    for (int x = 0; x < HerbivoreList.size(); x++) {
                        if (herb == HerbivoreList.get(x)) {
                            Duplicate = true;
                            y = y - 1;
                            break;
                        }
                    }
                    if (!Duplicate) {
                        HerbivoreList.add(herb);
                    }
                }
            }
        }
    }
}
