package me.nikl.gamebox.games;

import me.nikl.gamebox.games.templategame.Template;
import me.nikl.gamebox.module.GameBoxModule;

/**
 * Template module for GameBox
 *
 * @author Niklas Eicker
 */
public class GameTemplateModule extends GameBoxModule {
    public static final String gameId = "templategame";

    @Override
    public void onEnable(){
        /*
            All resources for a specific game have to be in the folder 'games/gameId/'
                This folder MUST contain a 'config.yml'
                All subfolders are also copied to the gameBox folder and can be used for game
                    specific additional resources
            All language files belong in 'language/moduleID/'
                This folder MUST contain a 'en_GB.yml' which is used for default messages
         */

        // As this module whats to add a game, we can register it here
        //    The last Strings are subcommands. Our template game could be opened with:
        //    '/gb templategame'
        //    '/gb test1'         and      '/gb test2'
        registerGame(gameId, Template.class, "test1", "test2");
    }

    @Override
    public void onDisable(){
        // clean up your own resources
        // Nothing to do atm
    }
}
