package at.oskarsniper.redstonepowered.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import at.oskarsniper.redstonepowered.commands.redstonePoweredCommand;

public class main extends JavaPlugin {

	public static Plugin plugin;
	public static main main;
	private BlockToggle blocktoggle;
	public static List<Location> lamps = new ArrayList<>();
	
	@Override
	public void onEnable()
	{
		plugin = this;
		this.blocktoggle = new BlockToggle();
		ConfigurationSection seca = getConfig().getConfigurationSection("RedstonePowered");
		if (seca != null) {
		    for (String keya : seca.getKeys(false)) {
		    	ConfigurationSection secb = getConfig().getConfigurationSection("RedstonePowered." + keya);
		    	for(String keyb : secb.getKeys(false))
		    	{
			    	ConfigurationSection secc = getConfig().getConfigurationSection("RedstonePowered." + keya + "." + keyb);
			    	for(String keyc : secc.getKeys(false))
			    	{
				    	ConfigurationSection secd = getConfig().getConfigurationSection("RedstonePowered." + keya + "." + keyb + "." + keyc);
				    	for(String keyd : secd.getKeys(false))
				    	{
				    		Location l = new Location(Bukkit.getWorld(keya), Double.parseDouble(keyb), Double.parseDouble(keyc), Double.parseDouble(keyd));
				    		this.blocktoggle.addToggle(l.getBlock(), this.getConfig().getDouble("RedstonePowered." + keya + "." + keyb + "." + keyc + "." + keyd));
				    	}
			    	}
		    	}
		    }
		}

		
		
		this.getCommand("rp").setExecutor(new redstonePoweredCommand(this));
	}
	
	public BlockToggle getBlockToggle()
	{
		return this.blocktoggle;
	}
}
