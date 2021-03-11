import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

import net.md_5.bungee.api.ChatColor;


public class PortalCheck implements Listener{
	private Main plugin;
	
	public PortalCheck(Main plugin){
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPortalCreate(PortalCreateEvent event){
		if(event.getEntity() instanceof Player){
			Player p = (Player) event.getEntity();
			
			//If the player who made the portal is the speedrunner being hunted
			if(p.getName() == CompassTrack.WhoToTrack.getName()){
				if(p.getWorld().getEnvironment() == World.Environment.NORMAL){  //created portal in the overworld
					CompassTrack.WhereToTrack = p.getLocation();
					//Bukkit.broadcastMessage(ChatColor.RED + "Now Tracking A Portal");
				}else if(p.getWorld().getEnvironment() == World.Environment.NETHER){
					CompassTrack.WhereToTrackNether = p.getLocation();
					//Bukkit.broadcastMessage(p.getName() + " has entered the " + p.getWorld());
				}
			}
			
			
		}
	}
}
