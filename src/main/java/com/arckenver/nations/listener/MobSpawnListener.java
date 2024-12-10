package com.yourname.nationwar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawnListener implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        // 적대적 몹, 중립적 몹, 발광 오징어, 박쥐, 팬텀의 스폰을 차단
        if (event.getEntity() instanceof org.bukkit.entity.Monster || 
            event.getEntity() instanceof org.bukkit.entity.Animal || 
            event.getEntity() instanceof org.bukkit.entity.GlowingSquid || 
            event.getEntity() instanceof org.bukkit.entity.Bat || 
            event.getEntity() instanceof org.bukkit.entity.Phantom) {
            event.setCancelled(true);
        }
    }
}
