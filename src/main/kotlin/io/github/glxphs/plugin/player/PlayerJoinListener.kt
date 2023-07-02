package io.github.glxphs.plugin.player

import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player: PlayerData = PlayerManager.getData(event.player)
        player.player.sendMessage(Component.text("Hello, " + player.player.name + "!"))
    }
}