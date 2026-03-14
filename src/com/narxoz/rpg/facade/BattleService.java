package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        int rounds = 0;
        StringBuilder log = new StringBuilder();

        log.append(hero.getName()).append(" (HP: ").append(hero.getHealth())
                .append(") vs ").append(boss.getName()).append(" (HP: ").append(boss.getHealth()).append(")\n");

        while (hero.isAlive() && boss.isAlive() && rounds < 20) {
            rounds++;

            int dmg = action.getDamage();
            boss.takeDamage(dmg);
            log.append(String.format("Round %d: %s attacks for %d damage. Boss HP → %d\n",
                    rounds, action.getActionName(), dmg, boss.getHealth()));

            if (!boss.isAlive()) break;

            int bossDmg = boss.getAttackPower();
            hero.takeDamage(bossDmg);
            log.append(String.format("Boss counters for %d damage. Hero HP → %d\n",
                    bossDmg, hero.getHealth()));
        }

        if (hero.isAlive()) {
            result.setWinner(hero.getName());
        } else {
            result.setWinner(boss.getName());
        }

        result.setRounds(rounds);
        result.addLine(log.toString().trim());
        return result;
    }
}
