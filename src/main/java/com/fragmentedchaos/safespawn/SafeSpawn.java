package com.fragmentedchaos.safespawn;

import net.neoforged.fml.common.Mod;

@Mod("safespawn")
public class SafeSpawn {

    public SafeSpawn() {
        // Trigger config static initialization
        System.out.println("[SafeSpawn] Init, ticks=" + SafeSpawnConfig.invulnerableTicks);
    }
}
