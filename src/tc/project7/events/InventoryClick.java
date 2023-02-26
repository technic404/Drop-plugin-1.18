package tc.project7.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import tc.project7.api.DropAPI;
import tc.project7.guis.DropGUI;

public class InventoryClick implements Listener {

	DropAPI da = new DropAPI();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(e.getClickedInventory() == null) return;
		
		Player p = (Player) e.getWhoClicked();
		
		if(!ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Menu dropu")) return;
		
		e.setCancelled(true);
		
		if(e.getCurrentItem() == null) return;
		
		Material clickedType = e.getCurrentItem().getType();
		
		if(da.getDisabledDrops(p).contains(clickedType)) {
			da.enableDrop(p, clickedType);
		} else {
			da.disableDrop(p, clickedType);
		}
		
		p.closeInventory();
		p.openInventory(DropGUI.getInventory(p));
	}
}
