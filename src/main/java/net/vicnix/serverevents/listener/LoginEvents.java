package net.vicnix.serverevents.listener;

import net.vicnix.serverevents.Main;
import net.vicnix.serverevents.entity.GamePlayer;
import net.vicnix.serverevents.utils.GameBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginEvents implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        GamePlayer gamePlayer = new GamePlayer(p.getUniqueId(), p);

        Main.getPlugin().getArena().getGamePlayerSet().add(gamePlayer);

        e.setJoinMessage(null);
    }


}
