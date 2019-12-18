package me.scifi.redissonhandler.redis;

import lombok.Getter;
import lombok.Setter;
import me.scifi.redissonhandler.redis.packets.Packet;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

@Getter @Setter
public class RedisHandler {

    private org.redisson.config.Config config;

    private RedissonClient client;

    private String host,channel,password;

    private int port;

    public RedisHandler(String host, String password, String channel, int port){
        this.host = host;
        this.password = password;
        this.channel = channel;
        this.port = port;
    }

    public RedisHandler connect(){
        config = new org.redisson.config.Config();
        config.useSingleServer().setAddress(host + ":" + port);
        if(!password.isEmpty())
            config.useSingleServer().setPassword(password);
        this.client = Redisson.create(config);
        
        return this;
    }

    public RedisHandler subscribe(String channel){
        ((RTopic<Packet>) client.getTopic(channel)).addListener((c, packet) -> packet.onReceive());
        return this;
    }

    public void publish(String channel, Packet packet){
        ((RTopic<Packet>) client.getTopic(channel)).publish(packet);
        packet.onSend();
    }

}
