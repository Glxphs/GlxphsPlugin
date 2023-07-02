package io.github.glxphs.plugin.features

import com.destroystokyo.paper.event.server.ServerTickEndEvent
import io.github.glxphs.plugin.GlxphsPlugin
import io.github.glxphs.plugin.player.PlayerManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.scheduler.BukkitTask

class InfoBar : Feature() {
    companion object {
        var info: Component? = null
        var removeInfoTask: BukkitTask? = null
        lateinit var instance: InfoBar

        fun showInfo(toShow: Component, duration: Long) {
            info = toShow
            removeInfoTask?.cancel()
            removeInfoTask = Bukkit.getScheduler().runTaskLater(GlxphsPlugin.instance, Runnable {
                info = null
            }, duration)
        }
    }

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
                        if (info != null) {
                            Component.text("     ").append(info!!)
                        } else {
                            Component.text(
                                "     " + player.location.blockX + " " + player.location.blockY + " " + player.location.blockZ
                            ).color(NamedTextColor.GRAY)
                        }
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
        instance = this
    }

    override fun onDisable() {

    }
}