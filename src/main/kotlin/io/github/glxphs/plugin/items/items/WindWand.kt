package io.github.glxphs.plugin.items.items

import io.github.glxphs.plugin.GlxphsPlugin
import io.github.glxphs.plugin.items.Ability
import io.github.glxphs.plugin.items.CustomItem
import io.github.glxphs.plugin.items.Rarity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable

class WindWand : CustomItem(
    Material.STICK,
    "wind_wand",
    "Wind Wand",
    Rarity.RARE,
    "A wand that can be used to summon wind.",
    WindWandAbility()
) {
    class WindWandAbility : Ability("Summon Wind", "Launches the player.", Type.RIGHT_CLICK, 40) {
        override fun onActivate(player: Player) {
            object : BukkitRunnable() {
                var ticks = 0
                override fun run() {
                    if (ticks > 20) {
                        cancel()
                    }
                    player.velocity = player.velocity.add(player.location.direction.multiply(0.2))
                    player.world.spawnParticle(Particle.CLOUD, player.location, 10, 1.0, 1.0, 1.0, 0.1)
                    ticks++
                }
            }.runTaskTimer(GlxphsPlugin.instance, 0, 1)
        }
    }
}