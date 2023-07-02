package io.github.glxphs.plugin.commands

import io.github.glxphs.plugin.GlxphsPlugin
import io.github.glxphs.plugin.items.manager.ItemManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ItemCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            // Open a inventory with all items, defined in GlxphsPlugin.instance.itemManager.items (an array of ItemStacks)
            Bukkit.createInventory(null, 9, Component.text("Custom Items")).apply {
                for (item in ItemManager.items) {
                    addItem(item)
                }
            }.let(sender::openInventory)
            return true
        }
        return false
    }
}