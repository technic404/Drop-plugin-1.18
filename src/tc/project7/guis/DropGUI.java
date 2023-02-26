package tc.project7.guis;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import tc.project7.Main;
import tc.project7.api.DropAPI;
import tc.project7.objects.DropChance;
import tc.project7.utils.ItemBuilderUtil;

public class DropGUI {

	static DropAPI da = new DropAPI();
	
	public static Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(p, 27, "Menu dropu");
		
		List<Material> disabled = da.getDisabledDrops(p);
		
		for(int i = 0; i < Main.drops.length; i++) {
			DropChance dc = Main.drops[i];
			inv.setItem(
				i, 
				new ItemBuilderUtil(dc.getMaterial(), 1)
				.setName((disabled.contains(dc.getMaterial()) ? ChatColor.RED : ChatColor.GREEN) + dc.getMaterial().toString().toLowerCase())
				.toItemStack()
			);
		}
		
		return inv;
	}
	
}
