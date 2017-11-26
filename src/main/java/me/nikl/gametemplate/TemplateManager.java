package me.nikl.gametemplate;

import me.nikl.gamebox.GameBox;
import me.nikl.gamebox.data.SaveType;
import me.nikl.gamebox.games.GameManager;
import me.nikl.gamebox.games.GameRule;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

/**
 * Created by nikl on 22.11.17.
 */
public class TemplateManager implements GameManager {

    private GameBox gameBox;

    private HashSet<UUID> players = new HashSet<>();

    private HashMap<String, GameRule> rules = new HashMap<>();

    public TemplateManager(GameBox gameBox){
        this.gameBox = gameBox;
    }

    @Override
    public boolean onInventoryClick(InventoryClickEvent inventoryClickEvent) {
        // here the real game takes place
        inventoryClickEvent.getWhoClicked().sendMessage(" You are in the template game");
        return true;
    }

    @Override
    public boolean onInventoryClose(InventoryCloseEvent inventoryCloseEvent) {
        removeFromGame(inventoryCloseEvent.getPlayer().getUniqueId());
        return true;
    }

    @Override
    public boolean isInGame(UUID uuid) {
        return players.contains(uuid);
    }

    @Override
    public int startGame(Player[] players, boolean playSounds, String... strings) {
        // first arg is the buttonID from #loadGameRules()



        return GameBox.GAME_STARTED;
    }

    @Override
    public void removeFromGame(UUID uuid) {
        players.remove(uuid);
    }

    @Override
    public void loadGameRules(ConfigurationSection configurationSection, String buttonID) {
        // load all custom options/game settings from the ConfigurationSection
        // and save them in a class extending GameRule.
        // Then put the rule with ID as key in a Map<String, ? extends GameRule> to return in #getGameRules()

        // game results will be saved and can be used in a top list
        boolean saveStats = true;

        // type of score that is saved/displayed in top list
        HashSet<SaveType> saveTypes = new HashSet<>();
        saveTypes.add(SaveType.SCORE);

        rules.put(buttonID, new GameRule(saveStats, saveTypes, buttonID));
    }

    @Override
    public Map<String, ? extends GameRule> getGameRules() {
        return null;
    }
}
