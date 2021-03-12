import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.PortalCreateEvent;


public class PortalCheck implements Listener{
	private Main plugin;
	
	public PortalCheck(Main plugin){
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//We'll check for PlayerPortalEvent because if a player leaves or enters the nether that'll work better because 
	//PortalCreateEvent might not work if player isn't in nether when the game generates the portal
	@EventHandler
	public void onPortalEntry(PlayerPortalEvent event){   

			Player p = (Player) event.getPlayer();
			
			//If the player who made the portal is the speedrunner being hunted
			if(p.getName() == CompassTrack.WhoToTrack.getName()){
				if(p.getWorld().getEnvironment() == World.Environment.NORMAL){  //created portal in the overworld
					CompassTrack.WhereToTrack = p.getLocation();
					//Bukkit.broadcastMessage(ChatColor.RED + "Now Tracking A Portal");
				}else if(p.getWorld().getEnvironment() == World.Environment.NETHER){  //created portal in the overworld
					CompassTrack.WhereToTrackNether = p.getLocation();
					//Bukkit.broadcastMessage(ChatColor.RED + "Now Tracking A Portal");
				}
			}
			
	}
	
}
