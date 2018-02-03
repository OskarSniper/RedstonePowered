package at.oskarsniper.redstonepowered.main;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class Toggle extends BukkitRunnable {

	private Block block;
	private double time;
	private final double remainingTime;
	private SwitchBlock sb;
	
	public Toggle(Block bl, double time)
	{
		this.block = bl;
		this.time = time;
		this.remainingTime = time;
		this.sb = new SwitchBlock();
		this.sb.initWorld(bl.getWorld());
	}
	
	public Block getBlock()
	{
		return this.block;
	}
	
	@Override
	public void run() {
		if(this.time <= 0.0D)
		{
			if(this.block.getType().equals(Material.REDSTONE_LAMP_OFF))
			{
				this.sb.switchLamp(this.block, true);
			} else {
				this.sb.switchLamp(this.block, false);
			}
			this.time = this.remainingTime;
		}
		this.time -= 0.05D;
	}

}
