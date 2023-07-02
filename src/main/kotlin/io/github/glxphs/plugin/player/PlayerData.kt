package io.github.glxphs.plugin.player

import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player

class PlayerData(val player: Player) {
    var maxMana = 100.0
    var strength = 0

    var mana = 100.0

    init {
        player.healthScale = 20.0
    }

    fun getMaxHealth(): Double {
        return player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue ?: 20.0
    }

    fun setMaxHealth(value: Double) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = value
    }

    fun getHealth(): Double {
        return player.health
    }

    fun setHealth(value: Double) {
        player.health = value
    }
}