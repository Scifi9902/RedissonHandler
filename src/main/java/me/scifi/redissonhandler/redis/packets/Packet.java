package me.scifi.redissonhandler.redis.packets;

public abstract class Packet {

    public abstract void onSend();

    public abstract void onReceive();


}
