package me.nikl.gamebox.games;

import me.nikl.gamebox.games.templategame.Template;
import me.nikl.gamebox.module.GameBoxModule;

/**
 * Template module for GameBox
 *
 * @author Niklas Eicker
 */
public class GameTemplateModule extends GameBoxModule {
    public static final String gameID = "templategame";

    @Override
    public void onEnable(){
        /*
            All resources for a specific module have to be in the folder 'games/moduleID/'
                This folder MUST contain a 'config.yml'
                All subfolders are also copied to the gameBox folder and can be used for game
                    specific additional resources
            All language files belong in 'language/moduleID/'
                This folder MUST contain a 'lang_en.yml' which is used for default messages
         */

        // As this module whats to add a game, we can register it here
        //    The last Strings are subcommands. Our template game could be opened with:
        //    '/gb templategame'
        //    '/gb test1'         and      '/gb test2'
        registerGame(gameID, Template.class, "test1", "test2");
    }

    @Override
    public void onDisable(){
        // clean up your own resources
        // Nothing to do atm
    }
}
