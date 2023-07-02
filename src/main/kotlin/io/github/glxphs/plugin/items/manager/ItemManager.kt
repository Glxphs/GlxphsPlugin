package io.github.glxphs.plugin.items.manager

import io.github.glxphs.plugin.GlxphsPlugin
import io.github.glxphs.plugin.items.CustomItem
import io.github.glxphs.plugin.items.items.BeginnersSword
import io.github.glxphs.plugin.items.items.WindWand
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class ItemManager {
    companion object {
        val items = arrayOf(
            WindWand(),
            BeginnersSword()
        )

        fun giveItem(player: Player, item: ItemStack) {
            player.inventory.addItem(item)
        }

        fun isCustomItem(item: ItemStack): Boolean {
            return getItemId(item) != null
        }

        fun getCustomItemFromItemStack(item: ItemStack): CustomItem? {
            val id = getItemId(item)
            if (id != null) {
                return getItemById(id)
            }
            return null
        }

        fun getItemById(id: String): CustomItem? {
            items.find { it.id == id }?.let {
                return it
            }
            return null
        }

        fun getItemId(item: ItemStack): String? {
            return item.itemMeta?.persistentDataContainer?.get(GlxphsPlugin.itemIdKey, PersistentDataType.STRING)
        }
    }

    fun registerItems() {

    }
}