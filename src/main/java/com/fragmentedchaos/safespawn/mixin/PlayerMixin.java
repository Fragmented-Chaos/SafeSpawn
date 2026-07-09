package com.fragmentedchaos.safespawn.mixin;

import com.fragmentedchaos.safespawn.SafeSpawnConfig;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class PlayerMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstructed(MinecraftServer server, ServerLevel level,
                                GameProfile gameProfile, ClientInformation clientInformation,
                                CallbackInfo ci) {
        // restoreFrom handles the actual logic per scenario
    }
}
