package com.fragmentedchaos.safespawn;

import net.fabricmc.api.ModInitializer;

public class SafeSpawn implements ModInitializer {
    @Override
    public void onInitialize() {
        // Trigger config static initialization
        System.out.println("[SafeSpawn] Init, ticks=" + SafeSpawnConfig.invulnerableTicks);
    }
}
