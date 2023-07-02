package io.github.glxphs.plugin.player

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerManager : Listener {
    companion object {
        private val players = mutableMapOf<Player, PlayerData>()

        private fun addPlayer(player: Player) {
            players[player] = PlayerData(player)
        }

        private fun removePlayer(player: Player) {
            players.remove(player)
        }

        fun getData(player: Player): PlayerData {
            return players[player] ?: throw Exception("Player not found")
        }
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        addPlayer(event.player)
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        removePlayer(event.player)
    }

    fun addAllPlayers() {
        for (player in Bukkit.getOnlinePlayers()) {
            addPlayer(player)
        }
    }
}