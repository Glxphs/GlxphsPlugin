package io.github.glxphs.plugin.features

import io.github.glxphs.plugin.items.CustomItem
import io.github.glxphs.plugin.items.manager.ItemManager
import io.github.glxphs.plugin.player.PlayerData
import io.github.glxphs.plugin.player.PlayerManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.ItemStack

class Damage : Feature() {
    @EventHandler
    fun onEntityDamage(event: EntityDamageByEntityEvent) {
        if (event.damager is Player) {
            val playerData = PlayerManager.getData(event.damager as Player)
            event.damage = getTotalDamage(event.damager as Player, (event.damager as Player).inventory.itemInMainHand)
        }
    }

    fun getTotalDamage(player: Player, item: ItemStack): Double {
        val data = PlayerManager.getData(player)

        if (ItemManager.isCustomItem(item)) {
            val customItem = ItemManager.getCustomItemFromItemStack(item)
            if (customItem?.meta?.getAttribute(CustomItem.CustomItemMeta.Attribute.ATTACK_DAMAGE) != null)
                return (customItem.meta.getAttribute(CustomItem.CustomItemMeta.Attribute.ATTACK_DAMAGE)!! + data.strength)
        }
        return data.strength.toDouble()
    }

    override fun onEnable() {

    }

    override fun onDisable() {

    }
}