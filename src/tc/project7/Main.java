package tc.project7;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import tc.project7.events.BlockBreak;
import tc.project7.events.InventoryClick;
import tc.project7.guis.DropGUI;
import tc.project7.objects.DropChance;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	public static DropChance[] drops = {
			new DropChance(Material.DIAMOND, 100),
			new DropChance(Material.COAL, 100),
			new DropChance(Material.EMERALD, 100),
			new DropChance(Material.SAND, 100)
		};
	
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		instance = this;
		
		getCommand("opendrop").setExecutor((sender, cmd, label, args) -> {
			Player p = (Player) sender;
			p.openInventory(DropGUI.getInventory(p));
			return true;
		});
		
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new InventoryClick(), this);
	}
	
	public static Main getInstance() { return instance; }

}
