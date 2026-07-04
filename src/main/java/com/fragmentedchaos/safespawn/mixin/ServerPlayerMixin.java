package com.fragmentedchaos.safespawn.mixin;

import com.fragmentedchaos.safespawn.SafeSpawnConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

    @Inject(method = "restoreFrom", at = @At("HEAD"))
    private void onRestoreFromHead(ServerPlayer oldPlayer, boolean alive, CallbackInfo ci) {
        if (shouldApply(oldPlayer)) {
            ((Entity) (Object) this).invulnerableTime = SafeSpawnConfig.invulnerableTicks;
        }
    }

    @Inject(method = "restoreFrom", at = @At("RETURN"))
    private void onRestoreFromReturn(ServerPlayer oldPlayer, boolean alive, CallbackInfo ci) {
        if (shouldApply(oldPlayer)) {
            ((Entity) (Object) this).invulnerableTime = SafeSpawnConfig.invulnerableTicks;
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTickHead(CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        if (self.tickCount < 2 && SafeSpawnConfig.enableLoginImmunity) {
            if (self.invulnerableTime < SafeSpawnConfig.invulnerableTicks) {
                self.invulnerableTime = SafeSpawnConfig.invulnerableTicks;
            }
        }
    }

    private static boolean shouldApply(ServerPlayer oldPlayer) {
        return oldPlayer.isDeadOrDying()
                ? SafeSpawnConfig.enableRespawnImmunity
                : SafeSpawnConfig.enableLoginImmunity;
    }
}
