package net.vicnix.serverevents;

import net.vicnix.serverevents.arena.Arena;
import net.vicnix.serverevents.commands.VictorCMD;
import net.vicnix.serverevents.listener.LoginEvents;
import net.vicnix.serverevents.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Arena arena;

    private Inventory inv;

    private boolean pvp;

    private static Main plugin;



    @Override
    public void onEnable() {
        plugin = this;

        this.pvp = false;

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.getCommand("ev").setExecutor(new VictorCMD());
        this.adminMenu();
        this.arena = new Arena();

        this.getServer().getPluginManager().registerEvents(new LoginEvents(), this);

    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public Arena getArena() {
        return arena;
    }

    public static Main getPlugin() {
        return plugin;
    }

    public Location getLocationFromString(String string){
        String[] strings = string.split(",");
        World world = Bukkit.getWorld(strings[0]);
        double x = Double.valueOf(strings[1]);
        double y = Double.valueOf(strings[2]);
        double z = Double.valueOf(strings[3]);
        float yaw = Float.valueOf(strings[4]);
        float pitch = Float.valueOf(strings[5]);

        return new Location(world, x, y, z, yaw, pitch);

    }

    public String getStringFromLocation(Location location){
        String world = location.getWorld().getName();
        double x = location.getBlockX();
        double y = location.getBlockY();
        double z = location.getBlockZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();
        return world+","+x+","+y+","+z+","+yaw+","+pitch;
    }


    public void adminMenu(){

        this.inv = Bukkit.createInventory(null, 4*9, "Menu de Control");

        this.inv.setItem(11,
                new ItemBuilder(Material.SAPLING)
                        .setName(ChatColor.GREEN+"Generar Arboles").setLore("", ChatColor.GRAY+"Genera arboles random en el mapa.")
                        .toItemStack());
        this.inv.setItem(12,
                new ItemBuilder(Material.WOOL)
                        .setName(ChatColor.GREEN+"Generar Ovejas").setLore("", ChatColor.GRAY+"Genera " +
                        "ovejas random en el mapa,", ChatColor.GRAY + "durante 20 segundos continuos.")
                        .toItemStack());
        this.inv.setItem(13,
                new ItemBuilder(Material.FEATHER)
                        .setName(ChatColor.GREEN+"Generar Pollos").setLore("", ChatColor.GRAY+"Genera " +
                        "pollos random en el mapa,", ChatColor.GRAY + "durante 20 segundos continuos.")
                        .toItemStack());
        this.inv.setItem(14,
                new ItemBuilder(Material.ANVIL)
                        .setName(ChatColor.GREEN+"Generar Yunques").setLore("", ChatColor.GRAY+"Genera " +
                        "yunques random en el mapa,", ChatColor.GRAY + "los yunques caen desde el cielo.")
                        .toItemStack());
        this.inv.setItem(15,
                new ItemBuilder(Material.LEATHER)
                        .setName(ChatColor.GREEN+"Generar Vacas").setLore("", ChatColor.GRAY+"Genera " +
                        "vacas random en el mapa,", ChatColor.GRAY + "durante 20 segundos continuos.")
                        .toItemStack());
        this.inv.setItem(20,
                new ItemBuilder(Material.FIREWORK_CHARGE)
                        .setName(ChatColor.GREEN+"Lluvia de Fuego").setLore("", ChatColor.GRAY+"Hacer " +
                        "llover una cantidad de fuego,", ChatColor.GRAY + "alrededor del mapa.")
                        .toItemStack());
        this.inv.setItem(20,
                new ItemBuilder(Material.RAW_BEEF)
                        .setName(ChatColor.GREEN+"Generar Zombies ").setLore("", ChatColor.GRAY+"Hacer " +
                        "llover una cantidad de fuego,", ChatColor.GRAY + "alrededor del mapa.")
                        .toItemStack());

    }

    public Inventory getInv() {
        return inv;
    }
}
