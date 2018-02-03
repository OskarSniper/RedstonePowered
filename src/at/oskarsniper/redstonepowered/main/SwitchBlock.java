package at.oskarsniper.redstonepowered.main;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

public class SwitchBlock
{
  private Object craftWorld;
  private Field isClientSideField;
  
  public void initWorld(World world)
  {
    try
    {
      this.craftWorld = (net.minecraft.server.v1_8_R3.World) (getInstanceOfCW((CraftWorld) world));
      this.isClientSideField = this.craftWorld.getClass().getField("isClientSide");
    }
    catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchFieldException e)
    {
      try
      {
        this.isClientSideField = this.craftWorld.getClass().getField("isStatic");
      }
      catch (NoSuchFieldException e1)
      {
        e1.printStackTrace();
      }
    }
    this.isClientSideField.setAccessible(true);
  }
  
  public void setStatic(boolean value)
  {
    try
    {
      this.isClientSideField.set(this.craftWorld, Boolean.valueOf(value));
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }
  
  public void switchLamp(Block block, boolean light)
  {
    if (light)
    {
      setStatic(true);
      block.setType(Material.REDSTONE_LAMP_ON);
      setStatic(false);
    }
    else
    {
      block.setType(Material.REDSTONE_LAMP_OFF);
    }
  }
  
  private Object getInstanceOfCW(Object cW)
    throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
  {
    return cW.getClass().getDeclaredMethod("getHandle", new Class[0]).invoke(cW, new Object[0]);
  }
}

