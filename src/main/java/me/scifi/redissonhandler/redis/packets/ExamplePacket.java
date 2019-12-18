package me.scifi.redissonhandler.redis.packets;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.scifi.redissonhandler.redis.packets.Packet;
import org.bukkit.Bukkit;

@AllArgsConstructor @NoArgsConstructor
public class ExamplePacket extends Packet {

    private String string;

    public void onSend() {
       System.out.println("[Redisson] Packet sent.");
    }

    public void onReceive() {
       System.out.println("[Redisson] " + string);
    }
}
