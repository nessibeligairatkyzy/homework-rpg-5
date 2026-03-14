package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null || battleResult.getWinner() == null) {
            return "No reward";
        }
        if (battleResult.getWinner().equals("TODO")) {
            return "Adventure incomplete";
        }
        if (battleResult.getRounds() == 0) {
            return "No battle occurred";
        }
        return battleResult.getWinner() + " receives 100 gold and a shiny trophy";
    }
}
