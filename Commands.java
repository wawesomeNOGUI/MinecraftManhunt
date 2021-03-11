import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	private Main plugin;
	
	public Commands(Main plugin){
		this.plugin = plugin;
		plugin.getCommand("hunt").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ( !(sender instanceof Player) ){
			sender.sendMessage("Only Players May Execute This Command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(p.hasPermission("hunt.use")){
			if(args.length < 1){
				p.sendMessage(ChatColor.RED + "Type in /hunt <playerName>");
				return false;
			}else if(args.length > 1){
				p.sendMessage(ChatColor.RED + "Too many args! Type in /hunt <playerName>");
				return false;
			}else{
				Player player = Bukkit.getPlayer(args[0]);
				if (player == null) {
				    sender.sendMessage(ChatColor.RED + "Player doesn't exist or is offline!");
				    return true;
				}
				
				Bukkit.broadcastMessage(ChatColor.BLUE + "Now Tracking: " + player.getName());
				CompassTrack.WhoToTrack = player;
				
				//Also Reset Portal Tracking coords
				CompassTrack.WhereToTrack = null;
				CompassTrack.WhereToTrackNether = null;
				
				return true;
			}

		}else{
			p.sendMessage("You Do Not Have Permission To Use This Command");
			return false;
		}
		
		//p.playNote(arg0, arg1, arg2);
		
	}
}
