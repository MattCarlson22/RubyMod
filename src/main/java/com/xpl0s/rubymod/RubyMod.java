package com.xpl0s.rubymod;

import com.xpl0s.rubymod.block.ModBlocks;
import com.xpl0s.rubymod.item.ModItems;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(RubyMod.MOD_ID)
public class RubyMod
{
    public static final String MOD_ID = "rubymod";
    private static final Logger LOGGER = LogManager.getLogger();

    public RubyMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.addListener((BiomeLoadingEvent event) ->
                event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, initGeneration.RUBY_ORE));

    }

    public static ResourceLocation locate(String name)
    {
        return new ResourceLocation(RubyMod.MOD_ID, name);
    }

    public static String find(String name)
    {
        return RubyMod.MOD_ID + ":" + name;
    }

    public static class initGeneration
    {
        private static initGeneration RubyRegistry;

        public static final List<OreConfiguration.TargetBlockState> RUBY_ORE_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                ModBlocks.RUBY_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()));

        public static final ConfiguredFeature<?, ?> CONFIGURED_RUBY_ORE = FeatureUtils.register(find("ruby_ore"),
                Feature.ORE.configured(new OreConfiguration(RUBY_ORE_TARGET_LIST, 4)));

        public static final PlacedFeature RUBY_ORE = PlacementUtils.register(find("ruby_ore"), CONFIGURED_RUBY_ORE.placed(List.of(CountPlacement.of(3), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-63), VerticalAnchor.absolute(20)), BiomeFilter.biome())));
    }

    private void setup(final FMLCommonSetupEvent event) {

        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
