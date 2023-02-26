package tc.project7.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import tc.project7.Main;
import tc.project7.objects.DropChance;

public class DropAPI {
	
	public void disableDrop(Player p, Material mat) {
		List<Material> disabled = getDisabledDrops(p);
		List<Material> enabled = getEnabledDrops(p);
		
		disabled.add(mat);
		enabled.remove(mat);
		
		Main.getInstance().getConfig().set(p.getUniqueId() + ".disabled", convertMaterialList(disabled));
		Main.getInstance().getConfig().set(p.getUniqueId() + ".enabled", convertMaterialList(enabled));
		Main.getInstance().saveConfig();
	}
	
	public void enableDrop(Player p, Material mat) {
		List<Material> disabled = getDisabledDrops(p);
		List<Material> enabled = getEnabledDrops(p);
		
		disabled.remove(mat);
		enabled.add(mat);
		
		Main.getInstance().getConfig().set(p.getUniqueId() + ".disabled", convertMaterialList(disabled));
		Main.getInstance().getConfig().set(p.getUniqueId() + ".enabled", convertMaterialList(enabled));
		Main.getInstance().saveConfig();
	}
	
	public List<Material> getDisabledDrops(Player p) {
		List<String> list = Main.getInstance().getConfig().getStringList(p.getUniqueId() + ".disabled");
		
		if(list != null) {
			return list
					.stream()
					.map(element -> Material.valueOf(element))
					.collect(Collectors.toList());
		} else {
			return Collections.emptyList();
		}
	}
	
	public List<Material> getEnabledDrops(Player p) {
		List<String> list = Main.getInstance().getConfig().getStringList(p.getUniqueId() + ".enabled");
		
		if(list != null) {
			return list
					.stream()
					.map(element -> Material.valueOf(element))
					.collect(Collectors.toList());
		} else {
			return getDefaultDrops();
		}
	}
	
	public List<Material> getDefaultDrops() {
		DropChance[] drops = Main.drops;
		List<Material> mats = new ArrayList<>();
		
		for(DropChance drop : drops) {
			mats.add(drop.getMaterial());
		}
		
		return mats;
	}
	
	public List<String> convertMaterialList(List<Material> list) {
		return list
				.stream()
				.map(element -> element.toString())
				.collect(Collectors.toList());
	}

}
