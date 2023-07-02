package io.github.glxphs.plugin.items

import org.bukkit.entity.Player
import java.util.*

abstract class Ability(
    val name: String,
    val description: String,
    val type: Type,

    val cooldown: Int = 0,
) {
    abstract fun onActivate(player: Player)

    enum class Type {
        LEFT_CLICK,
        RIGHT_CLICK,
        PASSIVE; // TODO: Implement passive abilities

        fun getDisplayName(): String {
            // e.g. LEFT_CLICK -> Left Click
            return name.lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }.replace("_", " ")
        }
    }
}