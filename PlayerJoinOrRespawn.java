import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
		ItemStack item = new ItemStack(Material.COMPASS, 1);
		ItemMeta newMeta = item.getItemMeta();
		newMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);  //so player doesn't drop compass on death
		item.setItemMeta(newMeta);
		p.getInventory().addItem(item);
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event){
		Player p = event.getPlayer();
		//Give them a compass in inventory slot 0
		//p.getInventory().setItem(0, new ItemStack(Material.COMPASS, 1));
		ItemStack item = new ItemStack(Material.COMPASS, 1);
		ItemMeta newMeta = item.getItemMeta();
		newMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);  //so player doesn't drop compass on death
		item.setItemMeta(newMeta);
		p.getInventory().addItem(item);
	}
}
