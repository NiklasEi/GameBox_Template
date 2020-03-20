package me.nikl.gamebox.games.templategame;

import me.nikl.gamebox.data.toplist.SaveType;
import me.nikl.gamebox.game.manager.EasyManager;
import me.nikl.gamebox.game.rules.GameRule;
import me.nikl.gamebox.game.rules.GameRuleRewards;
import me.nikl.gamebox.games.GameTemplateModule;
import me.nikl.gamebox.game.exceptions.GameStartException;
import me.nikl.gamebox.nms.NmsFactory;
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
public class TemplateManager extends EasyManager {
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
        UUID uuid = inventoryClickEvent.getWhoClicked().getUniqueId();
        addClick(uuid);
        NmsFactory.getNmsUtility().updateInventoryTitle((Player)inventoryClickEvent.getWhoClicked()
                ,((TemplateLanguage)template.getGameLang()).GAME_TITLE.replace("%clicks%", String.valueOf(clicks.get(uuid))));
    }

    private void addClick(UUID uniqueId) {
        clicks.put(uniqueId, clicks.get(uniqueId) + 1);
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent inventoryCloseEvent) {
        removeFromGame(inventoryCloseEvent.getPlayer().getUniqueId());
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
        players[0].openInventory(template.createInventory(54, ((TemplateLanguage)template.getGameLang()).GAME_TITLE.replace("%clicks%", "0")));
        clicks.put(players[0].getUniqueId(), 0);
    }

    @Override
    public void removeFromGame(UUID uuid) {
        if(rules.get(players.get(uuid)).isSaveStats())
            template.getGameBox().getDataBase().addStatistics(uuid, GameTemplateModule.gameID
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
}
