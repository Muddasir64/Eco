import java.awt.*;
import java.util.ArrayList;

public class Carnivore extends LivingThing {
    public static int property = 1;
    public static ArrayList<Carnivore> CarnivoreList = new ArrayList<>();

    public static void Initialize() {
        CarnivoreList.clear();
        while (CarnivoreList.size() != 7) {
            boolean Duplicate = false;
            Carnivore carn = new Carnivore();
            carn.setX();
            carn.setY();
            carn.color = Color.RED;
            carn.size = 10;
            carn.speed = 2;
            carn.alive = true;
            if (CarnivoreList.size() == 0) {
                CarnivoreList.add(carn);
            } else {
                for (int x = 0; x < CarnivoreList.size(); x++) {
                    if (carn == CarnivoreList.get(x)) {
                        carn.prototypeNum = property;
                        Duplicate = true;
                        property = property + 1;
                        break;
                    }

                }

                if (!Duplicate) {
                    carn.prototypeNum = property;
                    CarnivoreList.add(carn);
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
        boolean preyfound = false;
        int target = Integer.MAX_VALUE;
        for (int x = 0; x < Herbivore.HerbivoreList.size(); x++) {
            if (this.size >= Herbivore.HerbivoreList.get(x).size) {
                distanceX = (Herbivore.HerbivoreList.get(x).x - this.x);

                distanceY = (Herbivore.HerbivoreList.get(x).y - this.y);
                total = Math.abs(distanceX) + Math.abs(distanceY);
                if (total < target) {
                    preyfound = true;
                    target = total;
                    totalX = distanceX;
                    totalY = distanceY;
                    targetIndex = x;
                }
            }
        }
        if (preyfound == true) {
            System.out.println("Locked target:" + Herbivore.HerbivoreList.get(targetIndex).prototypeNum);
            this.targetId = Herbivore.HerbivoreList.get(targetIndex).prototypeNum;
            this.targetIndex = targetIndex;
            this.preyFound = true;

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
            distanceX = Herbivore.HerbivoreList.get(targetIndex).x - this.x;
            distanceY = Herbivore.HerbivoreList.get(targetIndex).y - this.y;
            total = Math.abs(distanceX) + Math.abs(distanceY);
            if (total == 0) {
                System.out.println("ate and removed number " + Herbivore.HerbivoreList.get(targetIndex).prototypeNum);

                this.eat();
                this.eaten = 0;
                Herbivore.HerbivoreList.remove(targetIndex);
                this.preyFound = false;
            }
        }
        if (!preyfound) {
            System.out.println("Sad luck");
            preyFound=false;
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
                    System.out.println("ate and removed number " + Herbivore.HerbivoreList.get(targetIndex).prototypeNum);
                    this.eaten = 0;
                    Herbivore.HerbivoreList.remove(targetIndex);
                    this.preyFound = false;

                }

            }
        } else {
            System.out.println("Changing target");
            this.setTarget();
        }
        if (toofat == true) {
            System.out.println("Changing target cuz too fat");
            this.setTarget();
        }
    }

    public void Birth() {
        if (this.size > 15) {
            for (int y = 0; y < 4; y++) {
                boolean Duplicate = false;
                Carnivore carn = new Carnivore();
                carn.setX();
                carn.setY();
                carn.color = Color.RED;
                carn.size = 10;
                carn.speed = 2;
                carn.alive = true;
                if (CarnivoreList.size() == 0) {
                    CarnivoreList.add(carn);
                } else {
                    for (int x = 0; x < CarnivoreList.size(); x++) {
                        if (carn == CarnivoreList.get(x)) {
                            Duplicate = true;
                            y = y - 1;
                            break;
                        }
                    }
                    if (!Duplicate) {
                        CarnivoreList.add(carn);
                    }
                }
            }

        }
    }

}