package net.znimator.returnbydeath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public class ReturnByDeath implements ModInitializer {
	public static final String MOD_ID = "return-by-death";
	public static final Identifier SoundIdentifier = Identifier.of(MOD_ID, "respawn_sound");
	public static final SoundEvent RESPAWN_SOUND = SoundEvent.of(SoundIdentifier);
	public static final Identifier PLAY_SOUND_PACKET = Identifier.of(MOD_ID, "play_respawn_sound");

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("koi is a furry!");
		Registry.register(Registries.SOUND_EVENT, Identifier.of(MOD_ID, "respawn_sound"), RESPAWN_SOUND);

		PayloadTypeRegistry.playS2C().register(PlaySoundPayload.ID, PlaySoundPayload.CODEC);

        // Play sound on respawn
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            ServerPlayNetworking.send(newPlayer, new PlaySoundPayload(RESPAWN_SOUND));
        });

        // Enable instant respawn
        ServerWorldEvents.LOAD.register((server, world) -> {
            world.getGameRules().get(GameRules.DO_IMMEDIATE_RESPAWN).set(true, server);
        });
	}
}