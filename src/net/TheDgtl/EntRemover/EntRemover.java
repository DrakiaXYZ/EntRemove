package net.TheDgtl.EntRemover;

import java.io.File;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EntRemover extends JavaPlugin {

	@Override
	public void onDisable() {
		
	}

	@Override
	public void onEnable() {
		for (World world : getServer().getWorlds()) {
			System.out.println("Removing 1.8 Entities from " + world.getName());
			removeOrbs(world);
		}
	}
	
	private void removeOrbs(World world) {
		int cCount = 0;
		File file = new File(world.getName() + "/region/");
		if (!file.exists()) return;
		String[] regions = file.list();
		for (String region : regions) {
			if (region.isEmpty()) continue;
			String[] parts = region.split("\\.");
			if (parts.length != 4) continue;
			int sX = Integer.valueOf(parts[1]) * 32;
			int sZ = Integer.valueOf(parts[2]) * 32;
			System.out.println("Parsing region at chunk coords " + sX + ", " + sZ);
			for (int x = sX; x < sX + 32; x++) {
				for (int z = sZ; z < sZ + 32; z++) {
					Chunk chunk = world.getChunkAt(x, z);
					if (chunk.load(false)) {
						cCount++;
						chunk.unload();
					}
				}
			}
		}
		System.out.println("Loaded " + cCount + " chunks");
	}
	
}
