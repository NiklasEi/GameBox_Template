package me.nikl.gamebox.games.templategame;

import me.nikl.gamebox.data.toplist.SaveType;
import me.nikl.gamebox.game.manager.GameManager;
import me.nikl.gamebox.game.rules.GameRule;
import me.nikl.gamebox.game.rules.GameRuleRewards;
import me.nikl.gamebox.games.GameTemplatePlugin;
import me.nikl.gamebox.game.exceptions.GameStartException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by nikl on 22.11.17.
 *
 * This example game simply prints a message when you click while you are in-game
 */
public class TemplateManager implements GameManager {
    private Template template;
    private HashMap<UUID, String> players = new HashMap<>();
    private HashMap<UUID, Integer> clicks = new HashMap<>();
    private HashMap<String, GameRule> rules = new HashMap<>();

    public TemplateManager(Template template){
        this.template = template;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {
        // here the real game takes place
        inventoryClickEvent.getWhoClicked().sendMessage(" You are in the template game");
        addClick(inventoryClickEvent.getWhoClicked().getUniqueId());
        return;
    }

    private void addClick(UUID uniqueId) {
        clicks.put(uniqueId, clicks.get(uniqueId) + 1);
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent inventoryCloseEvent) {
        removeFromGame(inventoryCloseEvent.getPlayer().getUniqueId());
        return;
    }

    @Override
    public boolean isInGame(UUID uuid) {
        return players.containsKey(uuid);
    }

    @Override
    public void startGame(Player[] players, boolean playSounds, String... strings) throws GameStartException {
        // first arg is the buttonID from #loadGameRules()
        if (strings == null) throw new GameStartException(GameStartException.Reason.ERROR);

        this.players.put(players[0].getUniqueId(), strings[0]);
        clicks.put(players[0].getUniqueId(), 0);
    }

    @Override
    public void removeFromGame(UUID uuid) {
        if(rules.get(players.get(uuid)).isSaveStats())
            template.getGameBox().getDataBase().addStatistics(uuid, GameTemplatePlugin.gameID
                    , rules.get(players.get(uuid)).getKey(), clicks.get(uuid)
                    , rules.get(players.get(uuid)).getSaveType());
        players.remove(uuid);
        clicks.remove(uuid);
    }

    @Override
    public void loadGameRules(ConfigurationSection buttonSec, String buttonID) {
        /*
        load all custom options/game settings from the ConfigurationSection
        and save them in a class extending GameRule.
        Then put the rule with ID as key in a Map<String, ? extends GameRule> to return in #getGameRules()

        GameRuleRewards      - has a single money and token reward
        GameRuleMultiRewards - has multiple money and token rewards depending on the final score of the player

        If your game has any extra options/settings you should create your own class extending GameRule
         */
        boolean saveStats = buttonSec.getBoolean("saveStats", false);
        double cost = buttonSec.getDouble("cost", 0.);
        int token = buttonSec.getInt("token", 0);
        int money = buttonSec.getInt("money", 0);
        rules.put(buttonID, new GameRuleRewards(buttonID, saveStats, SaveType.SCORE, cost, money, token));
    }

    @Override
    public Map<String, ? extends GameRule> getGameRules() {
        return rules;
    }

    /*
    This method can be ignored and does not have to return anything.
    It has the purpose to be able to use an InventoryHolder based gui system for the games.
     */
    @Override
    public Inventory getInventory() {
        return null;
    }
}
