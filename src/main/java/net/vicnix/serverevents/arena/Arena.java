package net.vicnix.serverevents.arena;

import net.vicnix.serverevents.Main;
import net.vicnix.serverevents.entity.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Arena {

    private Set<GamePlayer> gamePlayerSet = new HashSet<>();

    private Location joinSpawn;

    private Cuboid cuboid;

    private String event;


    public Arena(){

        this.event = "Ninguno";

        Location loc = Main.getPlugin().getLocationFromString(
                Main.getPlugin().getConfig().getString("pos1"));
        Location loc2 = Main.getPlugin().getLocationFromString(
                Main.getPlugin().getConfig().getString("pos2"));

        this.cuboid = new Cuboid(loc, loc2);
        Main.getPlugin().getLogger().info("Se ha cargado la arena correctamente");
    }

    public Cuboid getCuboid() {
        return cuboid;
    }

    public Set<Location> getRandomLocationBase(int max){
        Set<Location> randomList = new HashSet<>();
        for (int i = 0; i < max; i++) {
            Location random = cuboid.getRandomLocation().clone();
            random.setY(Main.getPlugin().getConfig().getInt("baseY"));
            randomList.add(random);
        }
        return randomList;
    }

    public Set<GamePlayer> getGamePlayerSet() {
        return gamePlayerSet;
    }

    public String getEvent() {
        return event;
    }
}
