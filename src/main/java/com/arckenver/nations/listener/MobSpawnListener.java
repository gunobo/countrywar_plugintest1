package com.yourname.nationwar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawnListener implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        // 적대적 몹과 중립적 몹의 스폰을 차단
        if (event.getEntity() instanceof org.bukkit.entity.Monster || 
            event.getEntity() instanceof org.bukkit.entity.Animal) {
            event.setCancelled(true);
        }
    }
}
