package net.znimator.returnbydeath;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.sound.SoundEvent;

public record PlaySoundPayload(SoundEvent soundEvent) implements CustomPayload {
    public static final CustomPayload.Id<PlaySoundPayload> ID = new CustomPayload.Id<>(ReturnByDeath.PLAY_SOUND_PACKET);
    public static final PacketCodec<RegistryByteBuf, PlaySoundPayload> CODEC = PacketCodec.tuple(SoundEvent.PACKET_CODEC, PlaySoundPayload::soundEvent, PlaySoundPayload::new);
    // should you need to send more data, add the appropriate record parameters and change your codec:
    // public static final PacketCodec<RegistryByteBuf, BlockHighlightPayload> CODEC = PacketCodec.tuple(
    //         BlockPos.PACKET_CODEC, BlockHighlightPayload::blockPos,
    //         PacketCodecs.INTEGER, BlockHighlightPayload::myInt,
    //         Uuids.PACKET_CODEC, BlockHighlightPayload::myUuid,
    //         BlockHighlightPayload::new
    // );
 
    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}