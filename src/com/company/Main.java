package com.company;

import java.util.Random;

public class Main {
    public static int[] heroesHealth = {200, 200, 200, 250};
    public static int[] heroesDamage = {20, 15, 10, 20};
    public static String[] heroesAttakcType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int bossHealth = 900;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";


    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }

    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttakcType.length);
        bossDefenceType = heroesAttakcType[randomIndex];

    }

    public static void round() {
        changeBossDefence();
        bossHit();
        heroesHit();
        healing();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("Boss health  " + bossHealth + " " + bossDefenceType);
        System.out.println("Warrior health  " + heroesHealth[0]);
        System.out.println("Magic health  " + heroesHealth[1]);
        System.out.println("Kinetic health  " + heroesHealth[2]);
        System.out.println("Medic health " + heroesHealth[3]);
        System.out.println("________________");
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0) {
                if (bossDefenceType == heroesAttakcType[i]) {
                    Random r = new Random();
                    int coef = r.nextInt(3) + 2;
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    }
                    bossHealth = bossHealth - heroesDamage[i] * coef;
                    System.out.println(heroesAttakcType[i] +
                            " critically hit  " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    }
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static void healing() {
        if (heroesHealth[3] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                heroesHealth[i] = heroesHealth[i] + heroesDamage[3];
            }
        } else {
            heroesHealth[3] = 0;
        }
    }


    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss won");
            return true;
        }
        return false;
    }
}
