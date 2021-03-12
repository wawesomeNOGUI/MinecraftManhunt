import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

import net.md_5.bungee.api.ChatColor;

public class CompassTrack implements Listener{
	private Main plugin;
	
	public CompassTrack(Main plugin){
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static Location WhereToTrack = null;  //For Portal In Overworld
	public static Location WhereToTrackNether = null; //For portal in Nether
	public static Player WhoToTrack = null;     //For players
	
	
	//Map for storing previous lodestone
	Map<String, Location> previousLodestone
    = new HashMap<String, Location>(); 
	
	@EventHandler
	public void onCompassRightClick(PlayerInteractEvent event){
		Action action = event.getAction(); // Instance of action
	    Player player = event.getPlayer(); // Instance of player.
	    
	    if(action == action.RIGHT_CLICK_BLOCK || action == action.RIGHT_CLICK_AIR ) { // If something is right clicked 
	    	
	    	//We only check for main hand (EquipmentSlot.HAND) because compass click can't be done with off hand anywho
	    	if(player.getInventory().getItemInMainHand().getType() == Material.COMPASS && event.getHand() == EquipmentSlot.HAND){  
	    		
	    		if(WhoToTrack == null){ 
	    			player.sendMessage(ChatColor.RED + "No One To Track! Set Who to track with: /hunt <player name>");
	    			return;
	    		}
	    		
	    		if(player.getWorld().getEnvironment() == World.Environment.NORMAL){
	    				
    				if(WhoToTrack.getWorld().getEnvironment() == World.Environment.NORMAL){  //If speedrunner in overworld too
    					CompassMeta meta = (CompassMeta) player.getInventory().getItemInMainHand().getItemMeta();
    					if(meta.hasLodestone()){
    						//Give player fresh compass without lodestone
    						player.getInventory().setItem(0, new ItemStack(Material.COMPASS, 1));
    					}
 	    			    
    					player.setCompassTarget(WhoToTrack.getLocation()); 
		    			player.sendMessage(ChatColor.AQUA + "Tracking " + WhoToTrack.getName() );
    				}else if (WhereToTrack != null){
    					CompassMeta meta = (CompassMeta) player.getInventory().getItemInMainHand().getItemMeta();
    					if(meta.hasLodestone()){
    						//Give player fresh compass without lodestone
    						player.getInventory().setItem(0, new ItemStack(Material.COMPASS, 1));
    					}
 	    			    
    					player.setCompassTarget(WhereToTrack); 
    	    			player.sendMessage(ChatColor.AQUA + "Tracking Hunted Player's Portal");
    				}
    				
	    		}else if(player.getWorld().getEnvironment() == World.Environment.NETHER){
	    			
	    			if(WhoToTrack.getWorld().getEnvironment() == World.Environment.NETHER){  //if speedrunner in nether too
	    				//Set a lodestone to speedrunner's position (out of sight y though), set hunter's compass to that lodestone
		    				
	    				if(previousLodestone.get(player.getName()) != null){
	    					//Delete Previous Lodestone
		    				Block b = previousLodestone.get(player.getName()).getBlock();
		    			    b.setType(Material.BEDROCK);
	    				}		    				
	    			    
	    			    //Then set new Lodestone
	    			    Location loc = WhoToTrack.getLocation();
	    			    loc.setY(0);  //can't place blocks below 0
	    			    Block b = loc.getBlock();
	    			    b.setType(Material.LODESTONE);
	    			    
	    			    //Set location in map
	    			    previousLodestone.put(player.getName(), loc);
	    			    
	    			    //Set player's compass to point to lodestone
	    			    CompassMeta meta = (CompassMeta) player.getInventory().getItemInMainHand().getItemMeta();
	    			    meta.setLodestone(loc);
	    			    meta.setLodestoneTracked(true);
	    			    player.getInventory().getItemInMainHand().setItemMeta(meta);
	    			    
	    			    player.sendMessage(ChatColor.AQUA + "Tracking " + WhoToTrack.getName() );
		    			    
	    			}else if(WhereToTrackNether != null){
	    				if(previousLodestone.get(player.getName()) != null){
	    					//Delete Previous Lodestone
		    				Block b = previousLodestone.get(player.getName()).getBlock();
		    			    b.setType(Material.BEDROCK);
	    				}		    				
	    			    
	    			    //Then set new Lodestone
	    			    Location loc = WhereToTrackNether;
	    			    loc.setY(0);  //can't place blocks below 0
	    			    Block b = loc.getBlock();
	    			    b.setType(Material.LODESTONE);
	    			    
	    			    //Set location in map
	    			    previousLodestone.put(player.getName(), loc);
	    			    
	    			    //Set player's compass to point to lodestone
	    			    CompassMeta meta = (CompassMeta) player.getInventory().getItemInMainHand().getItemMeta();
	    			    meta.setLodestone(loc);
	    			    meta.setLodestoneTracked(true);
	    			    player.getInventory().getItemInMainHand().setItemMeta(meta);
	    			    
	    			    player.sendMessage(ChatColor.AQUA + "Tracking Speedrunner's Portal in Nether! " );
	    			}else{
	    				player.sendMessage(ChatColor.AQUA + "Speedrunner Hasn't Entered Nether Yet" );
	    			}
	    			
	    			
	    			
	    		}else if(player.getWorld().getEnvironment() == World.Environment.THE_END){
	    			
	    			player.sendMessage(ChatColor.RED + "You Can't Track Players In The End!");

	    		}else{
	    			player.sendMessage(ChatColor.LIGHT_PURPLE + "IDK What Went Wrong");
	    		}
	    		
	    		
	    	}
	    }
	}
	
	
}
