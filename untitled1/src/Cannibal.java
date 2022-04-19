import java.awt.*;
import java.util.ArrayList;

public class Cannibal extends Carnivore {
    public static int property = 1;
    public static ArrayList<Cannibal> CannibalList = new ArrayList<>();
    public char type = 'h';

    public static void Initialize() {
        CannibalList.clear();
        while (CannibalList.size() != 5) {
            boolean Duplicate = false;
            Cannibal herb = new Cannibal();
            herb.setX();
            herb.setY();
            herb.prototypeNum = (int) (100000 * Math.random());
            herb.color = Color.BLACK;
            herb.size = 10;
            herb.speed = 2;
            herb.alive = true;
            if (CannibalList.size() == 0) {
                herb.prototypeNum = property;
                CannibalList.add(herb);
                property = property + 1;
            } else {
                for (int x = 0; x < CannibalList.size(); x++) {
                    if (herb == CannibalList.get(x)) {

                        Duplicate = true;
                        break;
                    }
                }
                if (!Duplicate) {
                    herb.prototypeNum = property;
                    CannibalList.add(herb);
                    property++;
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
        boolean preyfound = false;
        int target = Integer.MAX_VALUE;
        for (int x = 0; x < Herbivore.HerbivoreList.size(); x++) {
            if (this.size >= Herbivore.HerbivoreList.get(x).size) {
                {
                    distanceX = (Herbivore.HerbivoreList.get(x).x - this.x);

                    distanceY = (Herbivore.HerbivoreList.get(x).y - this.y);
                    total = Math.abs(distanceX) + Math.abs(distanceY);
                    if (total < target) {
                        preyfound = true;
                        type = 'h';
                        target = total;
                        totalX = distanceX;
                        totalY = distanceY;
                        targetIndex = x;
                    }
                }
            }
        }
        for (int x = 0; x < Carnivore.CarnivoreList.size(); x++) {
            if (this.size >= Carnivore.CarnivoreList.get(x).size) {
                {
                    distanceX = (Carnivore.CarnivoreList.get(x).x - this.x);

                    distanceY = (Carnivore.CarnivoreList.get(x).y - this.y);
                    total = Math.abs(distanceX) + Math.abs(distanceY);
                    if (total < target) {
                        preyfound = true;
                        type = 'c';
                        target = total;
                        totalX = distanceX;
                        totalY = distanceY;
                        targetIndex = x;
                    }
                }
            }
        }

       /* for (int x = 0; x < Cannibal.CannibalList.size(); x++) {
            if(this.prototypeNum== CannibalList.get(x).prototypeNum){
System.out.println(prototypeNum);
                System.out.println(CannibalList.get(x).prototypeNum);
            }
            else if (this.size >= Cannibal.CannibalList.get(x).size) {
                {
                    distanceX = (Carnivore.CarnivoreList.get(x).x - this.x);

                    distanceY = (Carnivore.CarnivoreList.get(x).y - this.y);
                    total = Math.abs(distanceX) + Math.abs(distanceY);
                    if (total < target) {
                        type = 'c';
                        target = total;
                        totalX = distanceX;
                        totalY = distanceY;
                        targetIndex = x;
                    }
                }
            }
        }*/
        if (preyfound) {
            if (type == 'c') {
                this.targetId = Carnivore.CarnivoreList.get(targetIndex).prototypeNum;
                this.targetIndex = targetIndex;
                this.preyFound = true;
            } else {
                this.targetId = Herbivore.HerbivoreList.get(targetIndex).prototypeNum;
                this.targetIndex = targetIndex;
                this.preyFound = true;
            }
            if (totalX < -20) {
                this.x = this.x - 40;
                this.eaten = this.eaten + 1;
            } else if (totalX > 20) {
                this.x = this.x + 40;
                this.eaten = this.eaten + 1;
            } else if (totalX == 20) {
                this.x = this.x + 20;
                this.eaten = this.eaten + 1;
            } else if (totalX == -20) {
                this.eaten = this.eaten + 1;
                this.x = this.x - 20;
            }
            if (totalY < -20) {
                this.eaten = this.eaten + 1;
                this.y = this.y - 40;
            } else if (totalY > 20) {
                this.eaten = this.eaten + 1;
                this.y = this.y + 40;
            } else if (totalY == 20) {
                this.eaten = this.eaten + 1;
                this.y = this.y + 20;
            } else if (totalY == -20) {
                this.eaten = this.eaten + 1;
                this.y = this.y - 20;
            }
            if (target == 0) {

                this.eat();
                this.eaten = 0;
                if (type == 'c') {
                    Carnivore.CarnivoreList.remove(targetIndex);
                } else {
                    Herbivore.HerbivoreList.remove(targetIndex);
                }
            }

        }
    }

    public void followTarget() {
        int distanceX;
        int distanceY;
        int total;
        int index = 0;
        boolean found = false;
        boolean toofat = false;
        if (preyFound) {
            if (type == 'h') {
                for (int x = 0; x < Herbivore.HerbivoreList.size(); x++) {
                    if (Herbivore.HerbivoreList.get(x).prototypeNum == this.targetId) {
                        System.out.println("Moving to target" + Herbivore.HerbivoreList.get(x).prototypeNum);
                        found = true;
                        index = x;
                        targetIndex = x;
                        break;
                    }
                }
                if (!found) {
                    preyFound = false;
                    System.out.println("Target not found");
                }
                if (found == true) {
                    if (Herbivore.HerbivoreList.get(index).size > this.size) {
                        found = false;
                        toofat = true;
                        System.out.println("Too fat");
                        this.preyFound = false;
                    }
                }
                if (found) {
                    distanceX = Herbivore.HerbivoreList.get(targetIndex).x - this.x;
                    distanceY = Herbivore.HerbivoreList.get(targetIndex).y - this.y;
                    total = Math.abs(distanceX) + Math.abs(distanceY);

                    if (distanceX < -20) {
                        this.x = this.x - 40;
                        this.eaten = this.eaten + 1;
                    } else if (distanceX > 20) {
                        this.x = this.x + 40;
                        this.eaten = this.eaten + 1;
                    } else if (distanceX == 20) {
                        this.x = this.x + 20;
                        this.eaten = this.eaten + 1;
                    } else if (distanceX == -20) {
                        this.eaten = this.eaten + 1;
                        this.x = this.x - 20;
                    }
                    if (distanceY < -20) {
                        this.eaten = this.eaten + 1;
                        this.y = this.y - 40;
                    } else if (distanceY > 20) {
                        this.eaten = this.eaten + 1;
                        this.y = this.y + 40;
                    } else if (distanceY == 20) {
                        this.eaten = this.eaten + 1;
                        this.y = this.y + 20;
                    } else if (distanceY == -20) {
                        this.eaten = this.eaten + 1;
                        this.y = this.y - 20;
                    }
                    distanceX = Herbivore.HerbivoreList.get(targetIndex).x - this.x;
                    distanceY = Herbivore.HerbivoreList.get(targetIndex).y - this.y;
                    total = Math.abs(distanceX) + Math.abs(distanceY);
                    if (total == 0) {
                        this.eat();
                        System.out.println("ate and removed number herbivore " + Herbivore.HerbivoreList.get(targetIndex).prototypeNum);
                        this.eaten = 0;
                        Herbivore.HerbivoreList.remove(targetIndex);
                        this.preyFound = false;

                    }

                }
            } else if (type == 'c') {
                for (int x = 0; x < Carnivore.CarnivoreList.size(); x++) {
                    if (Carnivore.CarnivoreList.get(x).prototypeNum == this.targetId) {
                        System.out.println("Moving to target" + Carnivore.CarnivoreList.get(x).prototypeNum);
                        found = true;
                        index = x;
                        targetIndex = x;
                        break;
                    }
                }
                if (!found) {
                    preyFound = false;
                    System.out.println("Target not found");
                }
                if (found == true) {
                    if (Carnivore.CarnivoreList.get(index).size > this.size) {
                        found = false;
                        toofat = true;
                        System.out.println("Too fat");
                        this.preyFound = false;
                    }
                }
                if (found) {
                    distanceX = Carnivore.CarnivoreList.get(targetIndex).x - this.x;
                    distanceY = Carnivore.CarnivoreList.get(targetIndex).y - this.y;
                    total = Math.abs(distanceX) + Math.abs(distanceY);

                    if (distanceX < -20) {
                        this.x = this.x - 40;
                        this.eaten = this.eaten + 1;
                    } else if (distanceX > 20) {
                        this.x = this.x + 40;
                        this.eaten = this.eaten + 1;
                    } else if (distanceX == 20) {
                        this.x = this.x + 20;
                        this.eaten = this.eaten + 1;
                    } else if (distanceX == -20) {
                        this.eaten = this.eaten + 1;
                        this.x = this.x - 20;
                    }
                    if (distanceY < -20) {
                        this.eaten = this.eaten + 1;
                        this.y = this.y - 40;
                    } else if (distanceY > 20) {
                        this.eaten = this.eaten + 1;
                        this.y = this.y + 40;
                    } else if (distanceY == 20) {
                        this.eaten = this.eaten + 1;
                        this.y = this.y + 20;
                    } else if (distanceY == -20) {
                        this.eaten = this.eaten + 1;
                        this.y = this.y - 20;
                    }
                    distanceX = Carnivore.CarnivoreList.get(targetIndex).x - this.x;
                    distanceY = Carnivore.CarnivoreList.get(targetIndex).y - this.y;
                    total = Math.abs(distanceX) + Math.abs(distanceY);
                    if (total == 0) {
                        this.eat();
                        System.out.println("ate and removed number carnivore " + Carnivore.CarnivoreList.get(targetIndex).prototypeNum);
                        this.eaten = 0;
                        Carnivore.CarnivoreList.remove(targetIndex);
                        this.preyFound = false;

                    }
                }
            } }else {
                System.out.println("Changing target");
                this.setTarget();
            }
            if (toofat == true) {
                System.out.println("Changing target cuz too fat");
                this.setTarget();
            }
        }



    public void Offspring() {
        if (this.size == 16) {
            for (int y = 0; y < 2; y++) {
                boolean Duplicate = false;
                Cannibal carn = new Cannibal();
                carn.setX();
                carn.setY();
                carn.color = Color.BLACK;
                carn.size = 10;
                carn.speed = 2;
                carn.prototypeNum = (int) (100000 * Math.random());
                carn.alive = true;
                if (CannibalList.size() == 0) {
                    CannibalList.add(carn);
                } else {
                    for (int x = 0; x < CannibalList.size(); x++) {
                        if (carn == CannibalList.get(x)) {
                            Duplicate = true;
                            y = y - 1;
                            break;
                        }

                    }

                    if (!Duplicate) {
                        carn.prototypeNum = CannibalList.size();
                        CannibalList.add(carn);
                    }
                }
            }

        }
    }
}

