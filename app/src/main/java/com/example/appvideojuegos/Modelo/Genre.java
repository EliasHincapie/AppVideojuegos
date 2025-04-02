package com.example.appvideojuegos.Modelo;
import java.io.IOException;
import java.io.Serializable;

public enum Genre implements Serializable {

     ACTION, ACTION_GAME, ACTION_RPG, ARPG, BATTLE_ROYALE, CARD_GAME, FANTASY, FIGHTING, GENRE_MMORPG, MMO, MMOARPG, MMORPG, MOBA, RACING, SHOOTER, SOCIAL, SPORTS, STRATEGY;

    public String toValue() {
        switch (this) {
            case ACTION: return "Action";
            case ACTION_GAME: return "Action Game";
            case ACTION_RPG: return "Action RPG";
            case ARPG: return "ARPG";
            case BATTLE_ROYALE: return "Battle Royale";
            case CARD_GAME: return "Card Game";
            case FANTASY: return "Fantasy";
            case FIGHTING: return "Fighting";
            case GENRE_MMORPG: return " MMORPG";
            case MMO: return "MMO";
            case MMOARPG: return "MMOARPG";
            case MMORPG: return "MMORPG";
            case MOBA: return "MOBA";
            case RACING: return "Racing";
            case SHOOTER: return "Shooter";
            case SOCIAL: return "Social";
            case SPORTS: return "Sports";
            case STRATEGY: return "Strategy";
        }
        return null;
    }

    public static Genre forValue(String value) throws IOException {
        if (value.equals("Action")) return ACTION;
        if (value.equals("Action Game")) return ACTION_GAME;
        if (value.equals("Action RPG")) return ACTION_RPG;
        if (value.equals("ARPG")) return ARPG;
        if (value.equals("Battle Royale")) return BATTLE_ROYALE;
        if (value.equals("Card Game")) return CARD_GAME;
        if (value.equals("Fantasy")) return FANTASY;
        if (value.equals("Fighting")) return FIGHTING;
        if (value.equals(" MMORPG")) return GENRE_MMORPG;
        if (value.equals("MMO")) return MMO;
        if (value.equals("MMOARPG")) return MMOARPG;
        if (value.equals("MMORPG")) return MMORPG;
        if (value.equals("MOBA")) return MOBA;
        if (value.equals("Racing")) return RACING;
        if (value.equals("Shooter")) return SHOOTER;
        if (value.equals("Social")) return SOCIAL;
        if (value.equals("Sports")) return SPORTS;
        if (value.equals("Strategy")) return STRATEGY;
        throw new IOException("Cannot deserialize Genre");
    }
}


