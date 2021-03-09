import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import net.md_5.bungee.api.ChatColor;

public class CompassTrack implements Listener{
	private Main plugin;
	
	public CompassTrack(Main plugin){
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static Location WhereToTrack = null;  //For Portal Locations
	public static Player WhoToTrack = null;     //For players
	
	@EventHandler
	public void onCompassRightClick(PlayerInteractEvent event){
		Action action = event.getAction(); // Instance of action
	    Player player = event.getPlayer(); // Instance of player.
	    
	    if(action == action.RIGHT_CLICK_BLOCK || action == action.RIGHT_CLICK_AIR ) { // If something is right clicked 
	    	
	    	//We only check for main hand (EquipmentSlot.HAND) because compass click can't be done with off hand anywho
	    	if(player.getInventory().getItemInMainHand().getType() == Material.COMPASS && event.getHand() == EquipmentSlot.HAND){  
	    		
	    		if(WhereToTrack == null || player.getWorld().getEnvironment() == World.Environment.NETHER){
	    			if(WhoToTrack != null){
	    				player.setCompassTarget(WhoToTrack.getLocation()); 
		    			player.sendMessage(ChatColor.AQUA + "Tracking " + WhoToTrack.getName() );
	    			}else{
	    				player.sendMessage(ChatColor.RED + "No One To Track! Set Who to track with: /hunt <player name>");
	    			}
	    			
	    		}else if(player.getWorld().getEnvironment() == World.Environment.THE_END){
	    			player.sendMessage(ChatColor.RED + "You Can't Track Players In The End!");
	    		}else if(WhereToTrack != null){
	    			player.setCompassTarget(WhereToTrack); 
	    			player.sendMessage(ChatColor.AQUA + "Tracking Hunted Player's Portal");
	    		}else{
	    			player.sendMessage(ChatColor.LIGHT_PURPLE + "IDK What Went Wrong");
	    		}
	    		
	    	}
	    }
	}
	
	
}
