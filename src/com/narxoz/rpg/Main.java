package com.narxoz.rpg;

import com.narxoz.rpg.decorator.*;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Decorator demo ===\n");

        AttackAction basic = new BasicAttack("Sword Strike", 20);
        System.out.println(basic.getActionName() + " → " + basic.getDamage() + " dmg");
        System.out.println("  " + basic.getEffectSummary() + "\n");

        AttackAction enhanced = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(basic)));

        System.out.println(enhanced.getActionName() + " → " + enhanced.getDamage() + " dmg");
        System.out.println("  " + enhanced.getEffectSummary() + "\n");


        System.out.println("=== Dungeon run demo ===\n");

        HeroProfile hero = new HeroProfile("Aragorn", 150);
        BossEnemy boss = new BossEnemy("Dark Lord", 200, 18);

        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);

        AdventureResult result = facade.runAdventure(hero, boss, enhanced);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        System.out.println("\nLog:");
        for (String line : result.getLog()) {
            System.out.println(line);
        }
    }
}