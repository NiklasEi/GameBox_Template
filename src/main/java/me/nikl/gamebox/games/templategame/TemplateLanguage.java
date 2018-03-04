package me.nikl.gamebox.games.templategame;

import me.nikl.gamebox.game.GameLanguage;

/**
 * Created by nikl on 22.11.17.
 *
 * The actual language file is loaded in the super classes, as well as some standard messages.
 */
public class TemplateLanguage extends GameLanguage {
    public String GAME_TITLE;
    // save your messages as fields

    public TemplateLanguage(Template template) {
        super(template);
    }

    @Override
    protected void loadMessages() {
        // load all messages here, use #getString(key) and #getStringList(key)
        this.GAME_TITLE = getString("inventoryTitles.title");
    }
}
