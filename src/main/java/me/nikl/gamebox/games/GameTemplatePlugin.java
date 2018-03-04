package me.nikl.gamebox.games;

import me.nikl.gamebox.GameBox;
import me.nikl.gamebox.Module;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by nikl
 *
 * Main class of the GameBox game-template
 */
public class GameTemplatePlugin extends JavaPlugin{
    public static final String gameID = "templategame";

    @Override
    public void onEnable(){
        GameBox gameBox = (GameBox) Bukkit.getPluginManager().getPlugin("GameBox");

        /*
            All resources for a specific module have to be in the folder 'games/moduleID/'
                This folder MUST contain a 'config.yml'
                All subfolders are also copied to the gameBox folder and can be used for game
                    specific additional resources
            All language files belong in 'language/moduleID/'
                This folder MUST contain a 'lang_en.yml'
         */

        // As this plugin contains an external GameBox module, We have to use the constructor
        //      Module(GameBox, String, String, JavaPlugin)
        new Module(gameBox, gameID
                , "me.nikl.gamebox.games.templategame.Template"
                , this);
    }

    @Override
    public void onDisable(){

    }
}
