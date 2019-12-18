package me.scifi.redissonhandler;

import lombok.Getter;
import me.scifi.redissonhandler.examples.ChatListener;
import me.scifi.redissonhandler.redis.RedisHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Example extends JavaPlugin {

   @Getter private static Example instance;

   private RedisHandler redisHandler;

    public void onEnable(){
        instance = this;
        redisHandler = new RedisHandler("127.0.0.1", "url33tpassword", "redis", 6379);
        redisHandler.connect();
        redisHandler.subscribe("redis");
        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(),this);
    }

}
