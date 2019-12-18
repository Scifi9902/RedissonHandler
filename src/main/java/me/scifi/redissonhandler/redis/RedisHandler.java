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
        setHost(host);
        setPassword(password);
        setChannel(channel);
        setPort(port);
    }

    public void connect(){
        config = new org.redisson.config.Config();
        config.useSingleServer().setAddress(host + ":" + port);
        if(!password.isEmpty()) config.useSingleServer().setPassword(password);
        setClient(Redisson.create(config));
    }

    public void subscribe(String channel){
        RTopic<Packet> topic = client.getTopic(channel);
        topic.addListener((c, packet) -> {
            packet.onReceive();
        });
    }

    public void publish(String channel, Packet packet){
        RTopic<Packet> topic = client.getTopic(channel);
        topic.publish(packet);
        packet.onSend();
    }

}
