package me.nikl.gametemplate;

import me.nikl.gamebox.GameBox;
import me.nikl.gamebox.games.Game;
import me.nikl.gamebox.games.GameSettings;

/**
 * Created by nikl on 21.11.17.
 */
public class Template extends Game {

    public Template(GameBox gameBox){
        super(gameBox, Main.gameID, new String[]{"test", "second"});
    }

    @Override
    public void onDisable() {
        // save your game specific stuff
    }

    @Override
    public void loadSettings() {
        // or some other game type...
        gameSettings.setGameType(GameSettings.GameType.SINGLE_PLAYER);

        // the other settings are ok on default
    }

    @Override
    public void loadLanguage() {
        gameLang = new TemplateLanguage(gameBox);
    }

    @Override
    public void loadGameManager() {
        gameManager = new TemplateManager(gameBox);
    }
}
