import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinOrRespawn implements Listener{
private Main plugin;
	
	public PlayerJoinOrRespawn(Main plugin){
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		//Give them a compass in inventory slot 0
		//p.getInventory().setItem(0, new ItemStack(Material.COMPASS, 1));
		p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event){
		Player p = event.getPlayer();
		//Give them a compass in inventory slot 0
		//p.getInventory().setItem(0, new ItemStack(Material.COMPASS, 1));
		p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
	}
}
