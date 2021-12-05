package com.xpl0s.rubymod.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModTiers {
    public static final ForgeTier RUBY = new ForgeTier(3,550,
            7f,2.5f,12, Tags.Blocks.NEEDS_GOLD_TOOL,
            () -> Ingredient.of(ModItems.RUBY.get()));
}
