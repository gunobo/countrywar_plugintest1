package com.yourname.customgen;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class EmeraldOreGenerator extends ChunkGenerator implements Listener {

    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
        World world = event.getWorld();
        // 에메랄드 광물 분포를 높이는 설정
        world.getPopulators().add(new EmeraldPopulator());
    }
    
    // 에메랄드 광물 생성 로직
    class EmeraldPopulator extends org.bukkit.generator.BlockPopulator {
        @Override
        public void populate(World world, Random random, org.bukkit.Chunk source) {
            int x = source.getX() * 16 + random.nextInt(16);
            int z = source.getZ() * 16 + random.nextInt(16);
            int y = random.nextInt(16) + 4; // Y축 위치 조정

            // 에메랄드 광물 생성
            if (random.nextInt(100) < 20) { // 20% 확률로 에메랄드 생성
                world.getBlockAt(x, y, z).setType(Material.EMERALD_ORE);
            }
        }
    }
}
