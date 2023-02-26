package tc.project7.events;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import tc.project7.Main;
import tc.project7.api.DropAPI;
import tc.project7.objects.DropChance;

public class BlockBreak implements Listener {
	
	DropAPI da = new DropAPI();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Material mat = e.getBlock().getType();
		if(mat != Material.STONE) return;
		
		Player p = e.getPlayer();
		
		int random = ThreadLocalRandom.current().nextInt(1, 101);
		
		for(DropChance drop : Main.drops) {
			if(da.getDisabledDrops(p).contains(drop.getMaterial())) continue;
			
			if(drop.getChance() >= random) { p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(drop.getMaterial())); }
		}
	}

}
