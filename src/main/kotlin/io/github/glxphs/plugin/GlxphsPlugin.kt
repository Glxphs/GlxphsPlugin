package io.github.glxphs.plugin

import io.github.glxphs.plugin.commands.EntityCommand
import io.github.glxphs.plugin.commands.ItemCommand
import io.github.glxphs.plugin.features.FeatureManager
import io.github.glxphs.plugin.items.AbilityListener
import io.github.glxphs.plugin.items.manager.ItemManager
import io.github.glxphs.plugin.player.PlayerJoinListener
import io.github.glxphs.plugin.player.PlayerManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin


class GlxphsPlugin : JavaPlugin(), Listener {
    companion object {
        lateinit var instance: GlxphsPlugin
        val itemIdKey: NamespacedKey = NamespacedKey("glxphs", "item-id")
    }

    val itemManager = ItemManager()
    val featureManager = FeatureManager()
    val playerManager = PlayerManager()

    val abilityListener = AbilityListener()

    override fun onEnable() {
        instance = this

        Bukkit.getPluginManager().registerEvents(this, this)
        Bukkit.getPluginManager().registerEvents(abilityListener, this)
        Bukkit.getPluginManager().registerEvents(PlayerJoinListener(), this)
        Bukkit.getPluginManager().registerEvents(playerManager, this)

        getCommand("item")!!.setExecutor(ItemCommand())
        getCommand("entity")!!.setExecutor(EntityCommand())

        itemManager.registerItems()
        featureManager.enableFeatures()
        playerManager.addAllPlayers()
    }

    override fun onDisable() {
        featureManager.disableFeatures()
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage(Component.text("Hello, " + event.player.name + "!"))
    }
}