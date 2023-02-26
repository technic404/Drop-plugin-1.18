package tc.project7.objects;

import org.bukkit.Material;

public class DropChance {
	
	private Material material;
	private int chance;
	
	public DropChance(Material material, int chance) {
		this.setMaterial(material);
		this.setChance(chance);
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

}
