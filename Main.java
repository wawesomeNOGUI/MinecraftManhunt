import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	@Override
	public void onEnable(){
		new CompassTrack(this);
		new PortalCheck(this);
		new PlayerJoinOrRespawn(this);
		new Commands(this);
	}
	
	
}