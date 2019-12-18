package me.scifi.redissonhandler.examples;

import me.scifi.redissonhandler.Example;
import me.scifi.redissonhandler.redis.packets.ExamplePacket;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private Example example = Example.getInstance();

    @EventHandler
    public void onTalk(AsyncPlayerChatEvent e){
        example.getRedisHandler().publish("redis", new ExamplePacket("test message"));
    }

}
