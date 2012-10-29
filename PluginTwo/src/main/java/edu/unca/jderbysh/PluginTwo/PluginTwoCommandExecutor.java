package edu.unca.jderbysh.PluginTwo;

import javax.persistence.Entity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.json.simple.ItemList;

import com.google.common.base.Joiner;

/*
 * This is a sample CommandExectuor
 */
public class PluginTwoCommandExecutor implements CommandExecutor {
    private final PluginTwo plugin;

    /*
     * This command executor needs to know about its plugin from which it came from
     */
    public PluginTwoCommandExecutor(PluginTwo plugin) {
        this.plugin = plugin;
    }

    /*
     * On command set the sample message
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	plugin.getLogger().info("Congrats.  You've reached onCommand in the command executor!");
    	if(args.length == 0) return false;
    	plugin.getLogger().info("Congrats!  You've got more than just the plugintwo!  You've typed a command, " + args[0]);
    	if(!(sender instanceof Player)) return false;
    	plugin.getLogger().info("Congrats!! You're a player named " + ((Player)(sender)).getName());
        if (args[0].equalsIgnoreCase("message") && sender.hasPermission("plugintwo.message")) {
        	plugin.getLogger().info("Congrats!! You've called message!");
            this.plugin.getConfig().set("plugintwo.message", Joiner.on(' ').join(args));
            return true;
        }
        else if (args[0].equalsIgnoreCase("equip") && sender.hasPermission("plugintwo.equip")) {
        	Player p = (Player)sender;

        	p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        	p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        	p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        	p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        	p.getItemInHand().setType(Material.DIAMOND_SWORD);
        	return true;
        }
        else if (args[0].equalsIgnoreCase("chicken") && sender.hasPermission("plugintwo.chicken")) {
        	Player p = (Player)sender;
        	Location l = p.getLocation();
        	if(args.length<2) {
        		p.getWorld().spawn(l, Chicken.class);
        	}
        	else {
        		int amount = Integer.parseInt(args[1]);
        		plugin.getLogger().info("You are trying to spawn " + amount + " chickens.  Good luck!");
        		for(int i = 0; i<amount; i++) {
            		l.setX(p.getLocation().getX()+5-(Math.random()*10));
            		l.setZ(p.getLocation().getZ()+5-(Math.random()*10));
        			p.getWorld().spawn(l, Chicken.class);
        		}
        	}
        	return true;
        }
        else if (args[0].equalsIgnoreCase("science") && sender.hasPermission("plugintwo.science")) {
        	Player p = (Player)sender;
        	p.getInventory().addItem(new ItemStack(Material.REDSTONE_TORCH_OFF,64));
        	p.getInventory().addItem(new ItemStack(Material.REDSTONE_WIRE,64));
        	p.getInventory().addItem(new ItemStack(Material.DIODE,64));
        	p.getInventory().addItem(new ItemStack(Material.REDSTONE_LAMP_OFF,64));
        	return true;
        }
        else if (args[0].equalsIgnoreCase("mountain") && sender.hasPermission("plugintwo.mountain")) {
        	Player p = (Player)sender;
        	Location start = p.getLocation();
        	start.setY(start.getY()+3);
        	plugin.getLogger().info("Building a mountain based on the player with location " + p.getLocation().toString());
        	for(double i = 10; i>0; i = i-.5) {
        		for(double j = p.getLocation().getX()-i; j < p.getLocation().getX()+i; j++) {
        			for(double k = p.getLocation().getZ()-i; j < p.getLocation().getZ() +i; k++) {
        				start.setX(j);
        				start.setZ(k);
        				start.setY(start.getY()+1);
        				plugin.getLogger().info("Changing " + start.toString() + " to stone.");
        				start.getBlock().setType(Material.STONE);
        			}
        		}
        	}
        	return true;
        }
        else {
        	plugin.getLogger().info("Failure.  You've returned false for some odd reason");
            return false;
        }
    }

}
