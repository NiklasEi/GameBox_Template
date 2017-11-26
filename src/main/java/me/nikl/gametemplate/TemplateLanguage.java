package me.nikl.gametemplate;

import me.nikl.gamebox.GameBox;
import me.nikl.gamebox.games.GameLanguage;

/**
 * Created by nikl on 22.11.17.
 *
 * The actual language file is loaded in the super classes, as well as some standard messages.
 */
public class TemplateLanguage extends GameLanguage {

    // save your messages as fields

    public TemplateLanguage(GameBox plugin) {
        super(plugin, Main.gameID);
    }

    @Override
    protected void loadMessages() {
        // load all messages here, use #getString(key) and #getStringList(key)
    }
}
