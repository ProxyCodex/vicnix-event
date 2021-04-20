package net.vicnix.serverevents.commands;

import net.vicnix.serverevents.Main;
import net.vicnix.serverevents.arena.Arena;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Random;

public class VictorCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(command.getName().equalsIgnoreCase("ev")){
            if(commandSender instanceof Player){
                Player p = (Player) commandSender;
                if(p.hasPermission("vicnix.event")){

                    String argument = strings[0];
                    Arena a = Main.getPlugin().getArena();
                    if(strings.length == 1) {




                        if (a.getCuboid() == null) {
                            p.sendMessage(ChatColor.GREEN + "El cuboid no existe o no ha sido creado");
                            return true;
                        }

                        if (a.getCuboid().getPoint1() == null) {
                            p.sendMessage(ChatColor.GREEN + "El cuboid posicion 1 no existe o no ha sido creado");
                            return true;
                        }

                        switch (argument) {
                            case "pos1":
                                p.teleport(a.getCuboid().getPoint1());
                                break;
                            case "pos2":
                                p.teleport(a.getCuboid().getPoint2());
                                break;
                            case "setlobby":
                                Main.getPlugin().getConfig().set("spawn.x", p.getLocation().getBlockX());
                                Main.getPlugin().getConfig().set("spawn.y", p.getLocation().getBlockY());
                                Main.getPlugin().getConfig().set("spawn.z", p.getLocation().getBlockZ());
                                Main.getPlugin().getConfig().set("spawn.yaw", p.getLocation().getYaw());
                                Main.getPlugin().getConfig().set("spawn.pitch", p.getLocation().getPitch());
                                Main.getPlugin().getConfig().set("spawn.world", p.getWorld().getName());
                                Main.getPlugin().saveConfig();
                                p.sendMessage(ChatColor.GOLD + "Se ha creado la localizacion para el spawn automáticamente.");
                                break;
                            case "setpos1":
                                Main.getPlugin().getConfig().set("pos1", Main.getPlugin().getStringFromLocation(p.getLocation()));
                                Main.getPlugin().saveConfig();
                                p.sendMessage(ChatColor.GOLD + "Se ha creado la localizacion para el Position 1 en el Cuboid");
                                break;
                            case "setpos2":
                                Main.getPlugin().getConfig().set("pos2", Main.getPlugin().getStringFromLocation(p.getLocation()));
                                Main.getPlugin().saveConfig();
                                p.sendMessage(ChatColor.GOLD + "Se ha creado la localizacion para el Position 2 en el Cuboid");
                                break;
                            case "forest":
                                Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " " + ChatColor.YELLOW + "activo el evento de " + ChatColor.AQUA
                                        + "Generación de Árboles");
                                for (Location location : a.getRandomLocationBase(10)) {
                                    location.getWorld().generateTree(location, TreeType.TREE);
                                }
                                break;
                            case "fire":
                                Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " " + ChatColor.YELLOW + "activo el evento de " + ChatColor.AQUA
                                        + "Lluvia de Fuego");
                                break;
                            case "anvil":
                                Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " " + ChatColor.YELLOW + "activo el evento de " + ChatColor.AQUA
                                        + "Lluvia de Yunques");
                                for (Location loc : a.getRandomLocationBase(10)) {
                                    loc.clone().add(0, 15, 0).getBlock().setType(Material.ANVIL);
                                }
                                break;
                            case "sheep":
                                Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " " + ChatColor.YELLOW + "activo el evento de " + ChatColor.AQUA
                                        + "Ovejas por 20 segundos");
                                new BukkitRunnable() {
                                    int Timer = 20;

                                    @Override
                                    public void run() {
                                        if (--Timer > 0) {
                                            a.getRandomLocationBase(5).forEach(location -> {
                                                location.getWorld().spawn(location, Sheep.class);
                                            });
                                        } else {
                                            Bukkit.broadcastMessage(ChatColor.YELLOW + "El evento de " + ChatColor.AQUA
                                                    + "Ovejas por 20 segundos " + ChatColor.YELLOW + "ha finalizado!");
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(Main.getPlugin(), 20L, 20L);
                                break;
                            case "chicken":
                                Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " " + ChatColor.YELLOW + "activo el evento de " + ChatColor.AQUA
                                        + "Pollos por 20 segundos");
                                new BukkitRunnable() {
                                    int Timer = 20;

                                    @Override
                                    public void run() {
                                        if (--Timer > 0) {
                                            a.getRandomLocationBase(5).forEach(location -> {
                                                location.getWorld().spawn(location, Chicken.class);
                                            });
                                        } else {
                                            Bukkit.broadcastMessage(ChatColor.YELLOW + "El evento de " + ChatColor.AQUA
                                                    + "Pollos por 20 segundos " + ChatColor.YELLOW + "ha finalizado!");
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(Main.getPlugin(), 20L, 20L);
                                break;

                            case "cow":
                                Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " " + ChatColor.YELLOW + "activo el evento de " + ChatColor.AQUA
                                        + "Vacas por 20 segundos");
                                new BukkitRunnable() {
                                    int Timer = 20;

                                    @Override
                                    public void run() {
                                        if (--Timer > 0) {
                                            a.getRandomLocationBase(5).forEach(location -> {
                                                location.getWorld().spawn(location, Cow.class);
                                            });
                                        } else {
                                            Bukkit.broadcastMessage(ChatColor.YELLOW + "El evento de " + ChatColor.AQUA
                                                    + "Vacas por 20 segundos " + ChatColor.YELLOW + "ha finalizado!");
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(Main.getPlugin(), 20L, 20L);
                                break;
                            default:
                                p.sendMessage(ChatColor.GREEN + "/ev setpos1, setpos2, setlobby");
                                break;
                        }

                        return true;
                    }

                    if (strings.length == 2) {

                        switch (argument){
                            case "arena":
                                int capas = Integer.parseInt(strings[1]);

                                Bukkit.broadcastMessage(ChatColor.YELLOW + "El evento de " + ChatColor.AQUA
                                        + "Arena " + ChatColor.YELLOW + "se ha activado!");
                                Iterator<Block> blockList = a.getCuboid().topBlockList(15);

                                while(blockList.hasNext()){

                                    Block block = blockList.next();

                                    if(block.getType() == Material.AIR){
                                        System.out.println("Air changed.");
                                        block.setType(Material.SAND);

                                        blockList.remove();
                                    }
                                }

                                break;
                        }

                        return true;
                    }

                }else {
                    p.sendMessage(ChatColor.RED+"No tienes permisos!");
                }
            }else {
                commandSender.sendMessage(ChatColor.RED+"Solo jugadores ingame!");
            }
        }


        return true;
    }
}
