package at.oskarsniper.redstonepowered.commands;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.oskarsniper.redstonepowered.main.main;

public class redstonePoweredCommand implements CommandExecutor {

	private main plugin;
	
	public redstonePoweredCommand(main pl)
	{
		this.plugin = pl;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2, String[] arg3) {
		if(s instanceof Player)
		{
			Player p = ((Player) s).getPlayer();
			if(arg3.length > 0)
			{
				switch(arg3[0])
				{
					case "bind":
						if(this.plugin.getBlockToggle().containsToggle(p.getTargetBlock((HashSet<Byte>) null, 5))) { p.sendMessage("Please remove or change block!"); return false; }
						if(arg3.length < 2) { p.sendMessage("Please add a time"); return false; }
						if(arg3.length > 2) { p.sendMessage("Too many arguments!"); return false; }
						if(isDouble(arg3[1])) {
							if(p.getTargetBlock((HashSet<Byte>) null, 5).getLocation().getBlock().getType().equals(Material.REDSTONE_LAMP_OFF) || p.getTargetBlock((HashSet<Byte>) null, 5).getLocation().getBlock().getType().equals(Material.REDSTONE_LAMP_ON))
							{
								this.plugin.getBlockToggle().addToggle(p.getTargetBlock((HashSet<Byte>) null, 5).getLocation().getBlock(), Double.parseDouble(arg3[1]));
								p.sendMessage("RedstonePowered successful added!");
							} else {
								p.sendMessage("Only usable with Redstone Lamp right now!");
							}
						} else {
							s.sendMessage("Plugin usage: /rp bind <time as double>");
							s.sendMessage("Example: /rp bind 1.25 -> will be executed every 1.25 seconds!");
						}
					break;
					case "unbind":
						if(this.plugin.getBlockToggle().containsToggle(p.getTargetBlock((HashSet<Byte>) null, 5)))
						{
							this.plugin.getBlockToggle().removeToggle(p.getTargetBlock((HashSet<Byte>) null, 5));
							p.sendMessage("RedstonePowered successful removed!");
						} else {
							p.sendMessage("Cannot unbind due to exception!");
						}
					break;
					case "change":
						if(arg3.length < 2) { p.sendMessage("Please add a time"); return false; }
						if(arg3.length > 2) { p.sendMessage("Too many arguments!"); return false; }
						this.plugin.getBlockToggle().removeToggle(p.getTargetBlock((HashSet<Byte>) null, 5).getLocation().getBlock());
						if(isDouble(arg3[1]))
						{
							if(p.getTargetBlock((HashSet<Byte>) null, 5).getLocation().getBlock().getType().equals(Material.REDSTONE_LAMP_OFF) || p.getEyeLocation().getBlock().getType().equals(Material.REDSTONE_LAMP_ON))
							{
								this.plugin.getBlockToggle().addToggle(p.getTargetBlock((HashSet<Byte>) null, 5).getLocation().getBlock(), Double.parseDouble(arg3[1]));
							} else {
								p.sendMessage("Only usable with Redstone Torch right now!");
							}
						}
						p.sendMessage("RedstonePowered successful changed!");
					break;
					default:
						s.sendMessage("Plugin usage: /rp <bind/unbind/change> <time>");
					break;
				}
				return true;
			} else {
				s.sendMessage("Plugin usage: /rp <bind/unbind/change> <time>");
				return false;
			}
		}
		return false;
	}
	
	public boolean isDouble(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		} catch(Exception e)
		{
			return false;
		}
	}

}
