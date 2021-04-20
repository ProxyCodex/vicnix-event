package net.vicnix.serverevents.entity;

import org.bukkit.entity.Player;

import java.util.UUID;

public class GamePlayer {

    private UUID id;
    private Player p;

    public GamePlayer(UUID id, Player p) {
        this.id = id;
        this.p = p;
    }


}
