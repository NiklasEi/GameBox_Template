package me.nikl.gametemplate;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by nikl
 *
 * Main class of the GameBox game-template
 */
public class Main extends JavaPlugin{

    @Override
    public void onEnable(){
        GameBox gameBox = (GameBox) Bukkit.getPluginManager().getPlugin("GameBox");
    }

    @Override
    public void onDisable(){

    }
}
