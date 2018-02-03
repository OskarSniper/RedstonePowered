package at.oskarsniper.redstonepowered.main;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlockToggle {
	
	private HashMap<Location, Toggle> toggle = new HashMap<Location, Toggle>();
	
	public void addToggle(Block bl, double time)
	{
		this.toggle.put(bl.getLocation(), new Toggle(bl, time));
		this.toggle.get(bl.getLocation()).runTaskTimer(main.plugin, 0L, 1L);
		main.plugin.getConfig().set("RedstonePowered." + bl.getWorld().getName() + "." + bl.getX() + "." + bl.getY() + "." + bl.getZ(), time);
		main.plugin.saveConfig();
	}
	
	public void removeToggle(Block bl)
	{
		if(this.toggle.containsKey(bl.getLocation()))
		{
			this.toggle.get(bl.getLocation()).cancel();
			this.toggle.remove(bl.getLocation());
			main.plugin.getConfig().set("RedstonePowered." + bl.getWorld().getName() + "." + bl.getX() + "." + bl.getY() + "." + bl.getZ(), null);
			main.plugin.saveConfig();
		}
	}
	
	public HashMap<Location, Toggle> getToggle()
	{
		return this.toggle;
	}
	
	public boolean containsToggle(Block bl)
	{
		if(this.toggle.containsKey(bl.getLocation()))
		{
			return true;
		} else {
			return false;
		}
	}
}
