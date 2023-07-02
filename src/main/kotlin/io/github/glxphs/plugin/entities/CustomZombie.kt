package io.github.glxphs.plugin.entities

import net.minecraft.network.chat.Component
import net.minecraft.world.entity.monster.Zombie
import net.minecraft.world.level.Level

class CustomZombie(world: Level) : Zombie(world) {
    init {
        customName = Component.literal("\u00a7a[${health}/${maxHealth}] Custom Zombie")
        isCustomNameVisible = true
    }

    override fun tick() {
        super.tick()
        customName = Component.literal("\u00a7a[${health}/${maxHealth}] Custom Zombie")
    }
}