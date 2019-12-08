package me.FerrissBueller.SkRegion;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public class SkRegion extends JavaPlugin {

    SkRegion instance;
    SkriptAddon addon;

    public void onEnable() {
	instance = this;
	addon = Skript.registerAddon(this);
	try {
	    //This will register all our syntax for us. Explained below
	    addon.loadClasses("me.FerrissBueller.SkRegion", "elements");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Bukkit.getLogger().info("[SkRegion] has been enabled!");
    }

    public SkRegion getInstance() {
	return instance;
    }

    public SkriptAddon getAddonInstance() {
	return addon;
    }
}
