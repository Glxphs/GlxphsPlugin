package io.github.glxphs.plugin.commands

import io.github.glxphs.plugin.entities.CustomZombie
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld
import org.bukkit.entity.Player
import org.bukkit.event.entity.CreatureSpawnEvent

class EntityCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            val customZombie = CustomZombie((sender.world as CraftWorld).handle)
            customZombie.setPos(sender.location.x, sender.location.y, sender.location.z)
            (sender.world as CraftWorld).addEntityToWorld(customZombie, CreatureSpawnEvent.SpawnReason.CUSTOM)
            return true
        }
        return false
    }
}