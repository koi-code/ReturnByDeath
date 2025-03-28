package net.znimator.returnbydeath;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ReturnByDeathClient implements ClientModInitializer {
	public static final String MOD_ID = "returnbydeath";

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ClientPlayNetworking.registerGlobalReceiver(PlaySoundPayload.ID, (payload, context) -> {
			context.client().execute(() -> {
				if (context.client().player != null) {
                    context.client().player.playSound(payload.soundEvent(), 1.0f, 1.0f);
                }
			});
		});
	}
}