import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealths = {250, 250, 250, 250};
    public static int[] heroesDamages = {20, 20, 20};
    public static String[] heroesAttackTypes = {"Physical", "Magical", "Kinetic", "Doc"};
    public static int docHelp = 10;

    public static void help() {
        if ((heroesHealths[0] > 0 || heroesHealths[1] > 0 || heroesHealths[2] > 0)
                && (heroesHealths[0] < 250 || heroesHealths[1] < 250 || heroesHealths[2] < 250)) {
            Random r = new Random();
            int randomNumber = r.nextInt(3);
            switch (randomNumber) {
                case 0:
                    heroesHealths[0] = heroesHealths[0] + docHelp;
                    System.out.println("Help: " + heroesAttackTypes[0]);
                    break;
                case 1:
                    heroesHealths[1] = heroesHealths[1] + docHelp;
                    System.out.println("Help: " + heroesAttackTypes[1]);
                    break;
                case 2:
                    heroesHealths[2] = heroesHealths[2] + docHelp;
                    System.out.println("Help: " + heroesAttackTypes[2]);
                    break;


            }
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackTypes.length);
        bossDefenceType = heroesAttackTypes[randomIndex];
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        if (bossHealth > 0) {
            bossHit();
        }
        help();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("_______________");
        System.out.println("Boss health: " + bossHealth);
//        System.out.println("Warrior health: " + heroesHealths[0]);
//        System.out.println("Magic health: " + heroesHealths[1]);
//        System.out.println("Kinetic health: " + heroesHealths[2]);
        for (int i = 0; i < heroesAttackTypes.length; i++) {
            System.out.println(heroesAttackTypes[i] + " health: " + heroesHealths[i]);
        }
        System.out.println("_______________");
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (!heroesAttackTypes[i].equals(bossDefenceType)) { // heroesAttackTypes[i] != bossDefenceType
                Random r = new Random();
                int coefficient = r.nextInt(3) + 2; //0,1,2,3,4
                bossHealth = bossHealth - heroesDamages[i] * coefficient;
                System.out.println(heroesAttackTypes[i] + " critical attack "
                        + heroesDamages[i] * coefficient);
            } else {
                bossHealth = bossHealth - heroesDamages[i];
            }
        }
        /*for (int tempDamage : heroesDamages) {
            bossHealth = bossHealth - tempDamage;
        }*/
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealths.length; i++) {
            heroesHealths[i] = heroesHealths[i] - bossDamage;
        }
    }

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }
    }

    public static boolean isFinished() {
        /*if (bossHealth <= 0 && heroesHealths[0] <= 0
                && heroesHealths[1] <= 0 && heroesHealths[2] <= 0) {
            System.out.println("Draw!!!");
            return true;
        }*/
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealths[0] <= 0 && heroesHealths[1] <= 0 && heroesHealths[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

}
