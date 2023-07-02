package io.github.glxphs.plugin.features

import com.destroystokyo.paper.event.server.ServerTickEndEvent
import io.github.glxphs.plugin.player.PlayerManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler

class InfoBar : Feature() {
    @EventHandler
    fun serverTickEndEvent(event: ServerTickEndEvent) {
        val players = Bukkit.getOnlinePlayers()
        for (player in players) {
            val data = PlayerManager.getData(player)
            player.sendActionBar(
                Component.text(
                    "❤ " + data.getHealth().toInt() + " / " + data.getMaxHealth().toInt()
                ).color(NamedTextColor.RED)
                    .append(
                        // Coordinates of the player
                        Component.text(
                            "     " + player.location.blockX + " " + player.location.blockY + " " + player.location.blockZ
                        ).color(NamedTextColor.GRAY)
                    )
                    .append(
                        Component.text(
                            "     ✼ " + data.mana.toInt() + " / " + data.maxMana.toInt()
                        ).color(NamedTextColor.DARK_PURPLE)
                    )
            )
        }
    }

    override fun onEnable() {
//        TODO("Not yet implemented")
    }

    override fun onDisable() {
//        TODO("Not yet implemented")
    }
}