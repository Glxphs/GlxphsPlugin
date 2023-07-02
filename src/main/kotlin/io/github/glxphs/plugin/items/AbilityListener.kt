package io.github.glxphs.plugin.items

import io.github.glxphs.plugin.GlxphsPlugin
import io.github.glxphs.plugin.items.manager.ItemManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class AbilityListener : Listener {
    val lastUsed = mutableMapOf<Pair<Player, String>, Int>()

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.item == null) return

        val itemId = ItemManager.getItemId(event.item!!)
            ?: return

        val item = ItemManager.getItemById(itemId)
            ?: return

        val ability = item.ability
            ?: return

        val playerItemKey = Pair(event.player, itemId)

        if (lastUsed.containsKey(playerItemKey)) {
            val lastUsageTime = lastUsed[playerItemKey]!!
            val cooldownTime = ability.cooldown
            val currentTime = Bukkit.getCurrentTick()

            if (currentTime - lastUsageTime < cooldownTime) {
//                event.player.sendMessage(Component.text("This item is on cooldown! Last used: ${currentTime - lastUsageTime}").color(NamedTextColor.RED))
                return
            }
        }

        when (event.action) {
            Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK -> {
                if (ability.type == Ability.Type.RIGHT_CLICK) {
                    ability.onActivate(event.player)
                    lastUsed[playerItemKey] = Bukkit.getCurrentTick()
                }
            }
            Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK -> {
                if (ability.type == Ability.Type.LEFT_CLICK) {
                    ability.onActivate(event.player)
                    lastUsed[playerItemKey] = Bukkit.getCurrentTick()
                }
            }
            else -> {
                // Do nothing for other actions
            }
        }
    }
}